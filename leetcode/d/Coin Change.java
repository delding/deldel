/**
 You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

 Example 1:
 coins = [1, 2, 5], amount = 11
 return 3 (11 = 5 + 5 + 1)

 Example 2:
 coins = [2], amount = 3
 return -1.

 Note:
 You may assume that you have an infinite number of each kind of coin.
 **/

public class Solution {
	public int coinChange(int[] coins, int amount) {
		Map<Integer, Integer> memo = new HashMap<>();
		return dfs(coins, amount, memo);
	}

	int dfs(int[] coins, int amount, Map<Integer, Integer> memo) {
		if (amount == 0) return 0;
		if (amount < 0) return -1;
		if (memo.containsKey(amount)) return memo.get(amount);
		int min = Integer.MAX_VALUE;
		for (int val : coins) {
			int count = dfs(coins, amount - val, memo);
			if (count >= 0) {
				min = Math.min(min, 1 + count);
			}
		}
		if (min == Integer.MAX_VALUE) {
			memo.put(amount, -1);
			return -1;
		} else {
			memo.put(amount, min);
			return min;
		}
	}
}