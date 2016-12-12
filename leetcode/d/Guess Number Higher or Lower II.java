/**
 We are playing the Guess Game. The game is as follows:

 I pick a number from 1 to n. You have to guess which number I picked.

 Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.

 However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the number I picked.

 Example:

 n = 10, I pick 8.

 First round:  You guess 5, I tell you that it's higher. You pay $5.
 Second round: You guess 7, I tell you that it's higher. You pay $7.
 Third round:  You guess 9, I tell you that it's lower. You pay $9.

 Game over. 8 is the number I picked.

 You end up paying $5 + $7 + $9 = $21.
 Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.

 Hint:

 The best strategy to play the game is to minimize the maximum loss you could possibly face. Another strategy is to minimize the expected loss. Here, we are interested in the first scenario.
 Take a small example (n = 3). What do you end up paying in the worst case?
 Check out this article if you're still stuck.
 The purely recursive implementation of minimax would be worthless for even a small n. You MUST use dynamic programming.
 **/

public class Solution {
	// min-max, minimize the maximum loss, minimize: use optimal strategy, max loss: correct at last guess
	public int getMoneyAmount(int n) {
		int[][] dp = new int[n][n]; // dp[i][j] is the guarantee cost for guess range [i + 1, j + 1]
		for (int len = 0; len < n; len++) {
			for (int i = 0, j = i + len; j < n; i++, j++) {
				if (i == j) dp[i][j] = 0;
				else {
					int amount = Integer.MAX_VALUE;
					for (int k = i; k <= j; k++) {
						if (k == i) amount = Math.min(amount, (k + 1) + dp[k + 1][j]);
						else if (k == j) amount = Math.min(amount, (k + 1) + dp[i][k - 1]);
						else amount = Math.min(amount, (k + 1) + Math.max(dp[k + 1][j], dp[i][k - 1]));
					}
					dp[i][j] = amount;
				}
			}
		}
		return dp[0][n-1];
	}
}