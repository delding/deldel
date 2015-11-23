/**
 * Given a binary tree, find the maximum path sum.
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
 * The path does not need to go through the root.
 * For example:
 * Given the below binary tree,
 * 1
 * / \
 * 2   3
 * Return 6.
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
  // consider a funtion that returns max path sum containing that node as an end vertex
  // maxpath can be max(node.val, node.val + left, node.val + right, node.val + left + right), because val can be minus
  // compare maxpath on all nodes and use a global variable store the global max
  int globalmax = Integer.MIN_VALUE;

  public int maxPathSum(TreeNode root) {
    localmax(root);
    return globalmax;
  }

  private int localmax(TreeNode root) {
    if (root == null) return 0;
    int left = localmax(root.left);
    int right = localmax(root.right);
    int mmax = Math.max(Math.max(left + root.val, right + root.val), root.val); // ERROR: Tricky, MUST return this value, current node must be the end vertex
    int max = Math.max(mmax, left + right + root.val);
    globalmax = Math.max(globalmax, max);
    return mmax;
  }
}
