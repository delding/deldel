/**
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
 * <p>
 * For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
 * the contiguous subarray [4,−1,2,1] has the largest sum = 6.
 **/

public class Solution {
  // finding every largest suffix sum ending at index i
  public int maxSubArray1(int[] nums) {
    int max = nums[0];
    int sufSum = nums[0];
    for (int i = 1; i < nums.length; i++) {
      sufSum = Math.max(sufSum + nums[i], nums[i]);
      max = Math.max(sufSum, max);
    }
    return max;
  }

  // divide and conquer
  public int maxSubArray(int[] nums) {
    return maxSubArray(nums, 0, nums.length - 1);
  }
  public int maxSubArray(int[] nums, int l, int r) {
    if (l == r) return nums[l];
    int m = l + (r - l) / 2;
    int midMax = nums[m];
    int sum = midMax;
    for (int i = m + 1; i <= r; i++) {
      sum += nums[i];
      midMax = Math.max(midMax, sum);
    }
    sum = midMax;
    for (int i = m - 1; i >= l; i--) {
      sum += nums[i];
      midMax = Math.max(midMax, sum);
    }
    if (m - 1 >= l) midMax = Math.max(maxSubArray(nums, l, m - 1), midMax);
    if (m + 1 <= r) midMax = Math.max(maxSubArray(nums, m + 1, r), midMax);
    return midMax;
  }
}