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
    TreeNode successor = null;
    if (p.right != null) { // if p has right subtree, successor is the min value in right subtree
      successor = p.right;
      while (successor.left != null) {
        successor = successor.left;
      }
      return successor;
    } else {
      while (true) { // if p has no right subtree, successor is the first node on the path from p to root when making a right turn, i.e. the last vale bigger than p on the path root to p
        if (p.val < root.val) {
          successor = root;
          root = root.left;
        } else if (p.val > root.val) {
          root = root.right;
        } else { // root == p
          return successor;
        }
      }
    }
  }
}
