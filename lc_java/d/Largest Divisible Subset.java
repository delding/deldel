/**
 Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.

 If there are multiple solutions, return any subset is fine.

 Example 1:

 nums: [1,2,3]

 Result: [1,2] (of course, [1,3] will also be ok)
 Example 2:

 nums: [1,2,4,8]

 Result: [1,2,4,8]
 **/

public class Solution {
	// for a new value to be added to the set, value needs to divide the smallest value if it is smaller than smallest,
	// or divisible by largest value if it is larger than largest
	public List<Integer> largestDivisibleSubset(int[] nums) {
		List<Integer> res = new ArrayList<>();
		if (nums.length == 0) return res;
		Arrays.sort(nums);
		int[] dp = new int[nums.length]; // dp[i] means length of largest divisible subset whose largest element is nums[i]
		int[] backtrace = new int[nums.length];
		Arrays.fill(backtrace, -1);
		dp[0] = 1;
		int max = 1;
		int maxIdx = 0;
		for (int i = 1; i < nums.length; i++) {
			int len = 1;
			for (int j = 0; j < i; j++) {
				if (nums[i] % nums[j] == 0 && dp[j] + 1 > len) {
					len = dp[j] + 1;
					backtrace[i] = j;
				}
			}
			dp[i] = len;
			if (len > max) {
				max = len;
				maxIdx = i;
			}
		}
		do {
			res.add(nums[maxIdx]);
			maxIdx = backtrace[maxIdx];
		} while (maxIdx != -1);
		Collections.reverse(res);
		return res;
	}
}