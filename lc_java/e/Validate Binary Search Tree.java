/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * <p>
 * Assume a BST is defined as follows:
 * <p>
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
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
  public boolean isValidBST(TreeNode root) {
    // return valid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    return valid(root);
  }

  // min-max
  boolean valid(TreeNode n, long lower, long higher) {
    if (n == null) return true;
    if (lower >= n.val || higher <= n.val) return false;
    return valid(n.left, lower, n.val) && valid(n.right, n.val, higher);
  }

  // inorder
  long pre = Long.MIN_VALUE;
  boolean valid(TreeNode cur) {
    if (cur == null) return true;
    if (!valid(cur.left)) return false;
    if (cur.val <= pre) return false;
    pre = cur.val;
    if (!valid(cur.right)) return false;
    return true;
  }
}
