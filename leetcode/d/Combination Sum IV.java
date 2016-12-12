/**
 Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

 Example:

 nums = [1, 2, 3]
 target = 4

 The possible combination ways are:
 (1, 1, 1, 1)
 (1, 1, 2)
 (1, 2, 1)
 (1, 3)
 (2, 1, 1)
 (2, 2)
 (3, 1)

 Note that different sequences are counted as different combinations.

 Therefore the output is 7.
 Follow up:
 What if negative numbers are allowed in the given array?
 How does it change the problem?
 What limitation we need to add to the question to allow negative numbers?
 **/

// follow-up: if negative number, and if a + b +...+ = 0, i.e. cycle exists, then infinite ways
public class Solution {
	// this problem is actually asking for permutation, so no need to use an idx parameter to keep elements in an consistent order
	// top-down + memoization
	public int combinationSum4Memo(int[] nums, int target) {
		return dfs(nums, target, new HashMap<Integer, Integer>());
	}

	int dfs(int[] nums, int target, Map<Integer, Integer> memo) {
		if (memo.containsKey(target)) return memo.get(target);
		if (target == 0) return 1;
		int count = 0;
		for (int num : nums) {
			if (num <= target) {
				count += dfs(nums, target - num, memo);
			}
		}
		memo.put(target, count);
		return count;
	}
	// dp
	public int combinationSum4(int[] nums, int target) {
		int[] count = new int[target + 1];
		count[0] = 1;
		for (int i = 1; i <= target; i++) {
			for (int num : nums) {
				if (i - num >= 0) {
					count[i] += count[i - num];
				}
			}
		}
		return count[target];
	}
}