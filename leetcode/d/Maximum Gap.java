/**
 * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
 * <p>
 * Try to solve it in linear time/space.
 * <p>
 * Return 0 if the array contains less than 2 elements.
 * <p>
 * You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
 **/

/*
假设有N个元素A到B。

那么最大差值不会小于ceiling[(B - A) / (N - 1)]

令bucket（桶）的大小len = ceiling[(B - A) / (N - 1)]，则最多会有(B - A) / len + 1个桶

对于数组中的任意整数K，很容易通过算式loc = (K - A) / len找出其桶的位置，然后维护每一个桶的最大值和最小值

由于同一个桶内的元素之间的差值至多为len - 1，因此最终答案不会从同一个桶中选择。
*/
public class Solution {
  public int maximumGap(int[] nums) {
    if (nums.length < 2) return 0;
    int max = nums[0];
    int min = nums[0];
    for (int n : nums) {
      max = Math.max(max, n);
      min = Math.min(min, n);
    }
    if (max == min) return 0;
    int bucketSize = (int) Math.ceil((double) (max - min) / (nums.length - 1));
    int bucketNum = (max - min) / bucketSize + 1;
    int[][] buckets = new int[bucketNum][2];
    for (int[] arr : buckets) {
      Arrays.fill(arr, -1);
    }
    for (int val : nums) {
      int idx = (val - min) / bucketSize;
      buckets[idx][0] = buckets[idx][0] == -1 ? val : Math.min(buckets[idx][0], val);
      buckets[idx][1] = buckets[idx][1] == -1 ? val : Math.max(buckets[idx][1], val);
    }
    int maxGap = 0;
    //int maxGap= buckets[0][1]-buckets[0][0]; // this is wrong, each bucket only keeps min and max value in it, max-min is not the gap since the values between min and max are skipped
    int prev = buckets[0][1]; // the max value in first bucket must not be empty since if only one value in first bucket, max is same as min which is global min
    for (int i = 1; i < bucketNum; i++) {
      if (buckets[i][0] == -1)
        continue; // -1 means empty bucket, since min=max if there is only one value in bucket, so either min and max both -1 or both not -1
      maxGap = Math.max(maxGap, buckets[i][0] - prev);
      prev = buckets[i][1];
    }
    return maxGap;
  }
}
