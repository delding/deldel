/**
 Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

 Find the maximum coins you can collect by bursting the balloons wisely.

 Note:
 (1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 (2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100

 Example:

 Given [3, 1, 5, 8]

 Return 167

 nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 **/

public class Solution {
	public int maxCoins(int[] nums) {
		int n = nums.length;
		if (n == 0) return 0;
		int[][] maxCoinsDp = new int[n][n];
		for (int len = 1; len <= n; len++) {
			for (int start = 0, end = start + len - 1; end < n; start++, end++) {
				int max = 0;
				for (int lastBurst = start; lastBurst <= end ; lastBurst++) {
					// must multiply coins left to start and coins right to end
					int coins = nums[lastBurst] * (start - 1 >= 0 ? nums[start - 1] : 1) * (end + 1 < n ? nums[end + 1] : 1);
					if (lastBurst - 1 >= start) {
						coins += maxCoinsDp[start][lastBurst - 1];
					}
					if (lastBurst + 1 <= end) {
						coins += maxCoinsDp[lastBurst + 1][end];
					}
					max = Math.max(max, coins);
				}
				maxCoinsDp[start][end] = max;
			}
		}
		return maxCoinsDp[0][n - 1];
	}
}

// TLE
public class Solution {
	public int maxCoins(int[] nums) {
		Map<ArrayWrapper, Integer> memo = new HashMap<>();
		return maxCoins(nums, memo);
	}

	public int maxCoins(int[] nums, Map<ArrayWrapper, Integer> memo) {
		ArrayWrapper numsWrapper = new ArrayWrapper(nums);
		if (memo.containsKey(numsWrapper)) return memo.get(numsWrapper);
		if (nums.length == 1) return nums[0];
		int maxCoin = 0;
		for (int i = 0; i < nums.length; i++) {
			int coin = nums[i];
			if (i - 1 >= 0) coin *= nums[i - 1];
			if (i + 1 < nums.length) coin *= nums[i + 1];
			int[] newNums = new int[nums.length - 1];
			for (int k = 0; k < newNums.length; k++) {
				if (k < i) newNums[k] = nums[k];
				if (k >= i) newNums[k] = nums[k + 1];
			}
			maxCoin = Math.max(maxCoin, coin + maxCoins(newNums, memo));
		}
		memo.put(numsWrapper, maxCoin);
		return maxCoin;
	}

	static class ArrayWrapper {
		int[] nums;

		public ArrayWrapper(int[] nums) {
			this.nums = nums;
		}

		@Override
		public boolean equals(Object that) {
			ArrayWrapper arr = (ArrayWrapper) that;
			if (this.nums.length != arr.nums.length) return false;
			for (int i = 0; i < this.nums.length; i++) {
				if (this.nums[i] != arr.nums[i]) return false;
			}
			return true;
		}

		@Override
		public int hashCode() {
			int ret = 0;
			for (int num : this.nums) {
				ret += 31 * ret + num;
			}
			return ret;
		}
	}
}