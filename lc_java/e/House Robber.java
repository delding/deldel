/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * <p>
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 */

public class Solution {
  public int rob(int[] nums) {
    if (nums.length == 0) return 0;
    int prepre = nums[0];
    if (nums.length == 1) return prepre;
    int pre = Math.max(nums[0], nums[1]);
    if (nums.length == 2) return pre;
    int curr = pre;
    for (int i = 2; i < nums.length; i++) {
      curr = Math.max(pre, prepre + nums[i]);
      prepre = pre;
      pre = curr;
    }
    return curr;
  }
}
