/**
 Given an integer n, return 1 - n in lexicographical order.

 For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].

 Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.
 **/

public class Solution {
	public List<Integer> lexicalOrder(int n) {
		List<Integer> res = new ArrayList<>();
		for (int i = 1; i <= 9; i++) dfs(i, n, res);
		return res;
	}

	void dfs(int val, int n, List<Integer> res) {
		if (val <= n) {
			res.add(val);
		} else return;
		for (int i = 0; i <= 9; i++) {
			dfs(10 * val + i, n, res);
		}
	}
}