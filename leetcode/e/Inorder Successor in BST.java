/**
 * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
 * <p>
 * Note: If the given node has no in-order successor in the tree, return null.
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
  public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
    if (p.right != null) {
      p = p.right;
      while (p.left != null) p = p.left;
      return p;
    } else {
      TreeNode lastRightParent = null;
      while (root != p) {
        if (root.val > p.val) {
          lastRightParent = root;
          root = root.left;
        } else {
          root = root.right;
        }
      }
      return lastRightParent;
    }
  }
}