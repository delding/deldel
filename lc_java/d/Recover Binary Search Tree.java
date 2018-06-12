/**
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * <p>
 * Recover the tree without changing its structure.
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
  // trival solution is to run an inorder traversal and put node in a list, iterate list find the swpped pair

  // todo:  Morris traversal
  // maintain a prev pointer
  // write the morris traversal first, then add code to find swapped nodes
  // time: O(n); space: O(1)
  public void recoverTree1(TreeNode root) {

  }


  public void recoverTree(TreeNode root) {
    inorder(root);
    int tmp = first.val;
    first.val = second.val;
    second.val = tmp;

  }

  // this is O(log(n)) if tree is balanced, but still not constant space
  TreeNode first = null;
  TreeNode second = null;
  TreeNode prev = null;

  private void inorder(TreeNode root) {
    if (root == null) return;
    inorder(root.left);
    if (prev != null && root.val <= prev.val) {
      if (first == null) {
        first = prev;
        second = root; // ERROR: MUST update second too, two elements can be next to each other
      } else
        second = root; // If they are not next to each other, will have another pair curr.val <= prev.val, and this curr is second
    }
    prev = root;
    inorder(root.right);
  }
}
