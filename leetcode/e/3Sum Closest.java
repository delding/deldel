/**
 Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
 Return the sum of the three integers. You may assume that each input would have exactly one solution.

 For example, given array S = {-1 2 1 -4}, and target = 1.

 The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 **/

public class Solution {
  public int threeSumClosest(int[] nums, int target) {
    Arrays.sort(nums);
    int close = nums[0] + nums[1] + nums[2];
    for (int i = 0; i < nums.length; i++) {
      int sum = target - nums[i];
      int lo = i + 1;
      int hi = nums.length - 1;
      while (lo < hi) {
        int tmp = nums[i] + nums[lo] + nums[hi];
        if (Math.abs(tmp - target) < Math.abs(close - target)) close = tmp;
        if (nums[lo] + nums[hi] > sum) {
          hi--;
        } else if (nums[lo] + nums[hi] < sum) {
          lo++;
        } else return target;
      }
    }
    return close;
  }
}

