/**
 * Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.
 * Note:
 * Given target value is a floating point.
 * You may assume k is always valid, that is: k ≤ total nodes.
 * You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
 * Follow up:
 * Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?
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
  public List<Integer> closestKValues(TreeNode root, double target, int k) {
    List<Integer> rst = new ArrayList<Integer>();
    if (root == null || k == 0) return rst;
    TreeNode closest = root;
    double minDiff = Math.abs(root.val - target);
    TreeNode curr = root;
    while (curr != null) {
      double diff = Math.abs(curr.val - target);
      if (diff < minDiff) {
        minDiff = diff;
        closest = curr;
      }
      if (target <= curr.val) curr = curr.left;
      else curr = curr.right;
    }
    TreeNode pre = getPredecessor(root, closest);
    TreeNode suc = getSuccessor(root, closest);
    while (k-- > 0) {
      rst.add(closest.val);
      if (pre != null && suc != null) {
        if (Math.abs(pre.val - target) < Math.abs(suc.val - target)) {
          closest = pre;
          pre = getPredecessor(root, pre);
        } else {
          closest = suc;
          suc = getSuccessor(root, suc);
        }
      } else {
        closest = pre != null ? pre : suc;
        pre = getPredecessor(root, pre); // return null if pre is null
        suc = getSuccessor(root, suc); // return null if suc is null
      }
    }
    return rst;
  }

  private TreeNode getPredecessor(TreeNode root, TreeNode curr) {
    if (curr == null) return null;
    TreeNode pre = null;
    if (curr.left != null) {
      pre = curr.left;
      while (pre.right != null) pre = pre.right;
    } else {
      // first left turn from curr to root, i.e., last node branching right child from root to curr
      while (root != curr) {
        if (curr.val < root.val) root = root.left;
        else {
          pre = root;
          root = root.right;
        }
      }
    }
    return pre; // could be null if curr is smallest value
  }

  private TreeNode getSuccessor(TreeNode root, TreeNode curr) {
    if (curr == null) return null;
    TreeNode suc = null;
    if (curr.right != null) {
      suc = curr.right;
      while (suc.left != null) suc = suc.left;
    } else {
      while (root != curr) {
        // first right turn from curr to root
        if (curr.val > root.val) root = root.right;
        else {
          suc = root;
          root = root.left;
        }
      }
    }
    return suc;
  }
}
