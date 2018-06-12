/**
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

 Determine the maximum amount of money the thief can rob tonight without alerting the police.

 Example 1:
 3
 / \
 2   3
 \   \
 3   1
 Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 Example 2:
 3
 / \
 4   5
 / \   \
 1   3   1
 Maximum amount of money the thief can rob = 4 + 5 = 9.
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
	Map<TreeNode, Integer> memo = new HashMap<>();
	public int rob(TreeNode root) {
		if (root == null) return 0;
		if (memo.containsKey(root)) return memo.get(root);
		TreeNode left = root.left;
		TreeNode right = root.right;
		int max = Math.max(rob(left) + rob(right),
				root.val + (left == null ? 0 : (rob(left.left) + rob(left.right))) + (right == null ? 0 : (rob(right.left) + rob(right.right))));
		memo.put(root, max);
		return max;
	}
}