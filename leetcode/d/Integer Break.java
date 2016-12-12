/**
 Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.

 For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).

 Note: You may assume that n is not less than 2 and not larger than 58.

 Hint:

 There is a simple O(n) solution to this problem.
 You may check the breaking results of n ranging from 7 to 10 to discover the regularities.
**/

public class Solution {
	public int integerBreak(int n) {
		int[] dp = new int[n];
		dp[0] = 1;
		for (int i = 1; i < n; i++) {
			int max = 1;
			for (int j = 0; j < i; j++) { // j <= i / 2 is enough
				max = Math.max(max, Math.max((j + 1), dp[j]) * Math.max((i - j), dp[i - j - 1])); // a number can break or not break
			}
			dp[i] = max;
		}
		return dp[n - 1];
	}
}