/**
 Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

 Note:
 Given m satisfies the following constraint: 1 ≤ m ≤ length(nums) ≤ 14,000.

 Examples:

 Input:
 nums = [7,2,5,10,8]
 m = 2

 Output:
 18

 Explanation:
 There are four ways to split nums into two subarrays.
 The best way is to split it into [7,2,5] and [10,8],
 where the largest sum among the two subarrays is only 18.
 **/

public class Solution {
	// the max possible value is sum of array, i.e. a single split
	// the min possible value is max elem of array, i.e. each elem is a split
	// binary search between max and min
	public int splitArray(int[] nums, int m) {
		int sum = 0;
		int max = 0;
		for (int n : nums) {
			sum += n;
			max = Math.max(n, max);
		}
		return bs(nums, m, max, sum);
	}

	// binary search
	int bs(int[] nums, int numSplit, int l, int h) {
		while (l <= h) {
			int m = l + (h - l) / 2;
			if (valid(nums, numSplit, m)) h = m - 1;
			else l = m + 1;
		}
		return l;
	}

	boolean valid(int[] nums, int numSplit, int min) {
		int sum = 0;
		int count = 1; // n split only cut (n - 1)
		for (int n : nums) {
			sum += n;
			if (sum > min) {
				if (++count > numSplit) return false;
				sum = n;
			}
		}
		return true;
	}

	// solu3
	// brute force: return dfs(nums, m, 0, 0); TLE
	// each leaf return max sum of the split of root to leaf path
	// internal return the min value of all its childern
	int dfs(int[] nums, int m, int max, int idx) {
		int sum = 0;
		int min = Integer.MAX_VALUE;
		if (m == 1 && idx < nums.length) {
			for (int i = idx; i < nums.length; i++) sum += nums[i];
			return Math.max(max, sum);
		}
		if (m > 1) {
			for (int i = idx; i < nums.length; i++) {
				sum += nums[i];
				min = Math.min(min, dfs(nums, m - 1, Math.max(max, sum), i + 1));
			}
		}
		return min;
	}
}