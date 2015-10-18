/**
 * Note: This is an extension of House Robber.

After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not get too much attention. This time, all houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, the security system for these houses remain the same as for those in the previous street.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
* */

public class Solution {
    // each house has two choices, rob or not rob
    public int rob(int[] nums) {
        if (nums.length ==0) return 0;
        if (nums.length ==1) return nums[0];
        if (nums.length ==2) return Math.max(nums[0], nums[1]);
        
        int[] rob0 = Arrays.copyOfRange(nums, 2, nums.length - 1);
        int[] notRob0 = Arrays.copyOfRange(nums, 1, nums.length);
        return Math.max(dp(rob0) + nums[0], dp(notRob0));
    }
    
    private int dp(int[] nums) {
        if (nums.length ==0) return 0;
        if (nums.length ==1) return nums[0];
        if (nums.length ==2) return Math.max(nums[0], nums[1]);
        
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i<nums.length; i++) {
            dp[i] = Math.max(dp[i-1], nums[i] + dp[i-2]);
        }
        return dp[nums.length -1];
    }
}
