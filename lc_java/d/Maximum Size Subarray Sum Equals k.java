/**
 Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

 Example 1:
 Given nums = [1, -1, 5, -2, 3], k = 3,
 return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)

 Example 2:
 Given nums = [-2, -1, 2, 1], k = 1,
 return 2. (because the subarray [-1, 2] sums to 1 and is the longest)
 **/

public class Solution {
	public int maxSubArrayLen(int[] nums, int k) {
		int[] preSum = new int[nums.length + 1];
		Map<Integer, Integer> memo = new HashMap<>();
		memo.put(0, 0);
		int maxLen = 0;
		for (int i = 1; i < preSum.length; i++) {
			preSum[i] = preSum[i - 1] + nums[i - 1];
			if (!memo.containsKey(preSum[i])) memo.put(preSum[i], i); // if prefix sum already exisit, don't override since need longest length
			Integer index = memo.get(preSum[i] - k);
			if (index != null) maxLen = Math.max(maxLen, i - index);
		}
		return maxLen;
	}
}