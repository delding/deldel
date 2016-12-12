/**
 Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

 Example:
 Given binary tree
 1
 / \
 2   3
 / \
 4   5
 Returns [4, 5, 3], [2], [1].

 Explanation:
 1. Removing the leaves [4, 5, 3] would result in this tree:

 1
 /
 2
 2. Now removing the leaf [2] would result in this tree:

 1
 3. Now removing the leaf [1] would result in the empty tree:

 []
 Returns [4, 5, 3], [2], [1].
 **/

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
	public List<List<Integer>> findLeaves(TreeNode root) {
		List<List<Integer>> leefs = new ArrayList<>();
		dfs(root, leefs);
		return leefs;
	}

	int dfs(TreeNode node, List<List<Integer>> leefs) {
		if (node == null) return 0;
		int level = Math.max(dfs(node.left, leefs), dfs(node.right, leefs));
		if (leefs.size() < level + 1) leefs.add(new ArrayList<>());
		leefs.get(level).add(node.val);
		return level + 1;
	}
}