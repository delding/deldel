/**
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 * Follow up:
 * What if the BST is modified (insert/delete operations) often and you need to find the kth smallest
 * frequently? How would you optimize the kthSmallest routine?
 * The optimal runtime complexity is O(height of BST).
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
  public int kthSmallest(TreeNode root, int k) {
    int[] count = new int[1];
    count[0] = k;
    int[] val = new int[1];
    inorder(root, count, val);
    return val[0];
  }

  void inorder(TreeNode root, int[] count, int[] val) {
    if (root == null) return;
    inorder(root.left, count, val);
    if (--count[0] == 0) val[0] = root.val;
    inorder(root.right, count, val);
  }

  // O(log(n)), tree node stores node rank
}
