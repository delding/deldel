/**
 You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.

 What is the maximum number of envelopes can you Russian doll? (put one inside other)

 Example:
 Given envelopes = [[5,4],[6,4],[6,7],[2,3]], the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 **/

public class Solution {
	public int maxEnvelopes(int[][] envelopes) {
		if (envelopes.length == 0) return 0;
		Arrays.sort(envelopes, (e1, e2) -> {
			if (e1[0] == e2[0]) return e1[1] - e2[1];
			else return e1[0] - e2[0];
		});
		int[] dp = new int[envelopes.length];
		dp[0] = 1;
		int max = 1;
		for (int i = 1; i < dp.length; i++) {
			int num = 1; // if none of previous doll can be put in, current doll itself count 1
			for (int j = 0; j < i; j++) {
				if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
					num = Math.max(num, 1 + dp[j]);
				}
			}
			dp[i] = num;
			max = Math.max(max, num);
		}
		return max;
	}

	// nlogn solution
	// Sort the array. Ascend on width and descend on height if width are same.
	// Find the longest increasing subsequence based on height using binary search

}
