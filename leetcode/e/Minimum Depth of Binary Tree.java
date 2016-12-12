/**
 * Given a binary tree, find its minimum depth.
 * <p>
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
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
  public int minDepth(TreeNode root) {
    if (root == null) return 0;
    return dep(root);
  }

  int dep(TreeNode n) {
    if (n.left == null && n.right == null) return 1;
    int l = n.left == null ? Integer.MAX_VALUE : dep(n.left);
    int r = n.right == null ? Integer.MAX_VALUE : dep(n.right);
    return Math.min(l, r) + 1;
  }
}