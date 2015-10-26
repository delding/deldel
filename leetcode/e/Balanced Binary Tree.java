/**
 * Given a binary tree, determine if it is height-balanced.
 * <p>
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
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
  public boolean isBalanced(TreeNode root) {
    boolean[] balanced = new boolean[1];
    balanced[0] = true;
    height(root, balanced);
    return balanced[0];
  }

  // compute height for each node while check if tree rooted at this node is balanced
  private int height(TreeNode root, boolean[] balanced) {
    if (root == null) return 0;
    int leftH = height(root.left, balanced);
    int rightH = height(root.right, balanced);
    if (Math.abs(leftH - rightH) > 1) balanced[0] = false;
    return 1 + Math.max(leftH, rightH);
  }
}
