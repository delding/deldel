/**
 * Given a binary tree, flatten it to a linked list in-place.
 * <p>
 * For example,
 * Given
 * <p>
 * 1
 * / \
 * 2   5
 * / \   \
 * 3   4   6
 * The flattened tree should look like:
 * 1
 * \
 * 2
 * \
 * 3
 * \
 * 4
 * \
 * 5
 * \
 * 6
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
  public void flatten(TreeNode root) {
    helper(root);
  }

  private TreeNode helper(TreeNode root) {
    if (root == null) return null;
    TreeNode left = helper(root.left);
    TreeNode right = helper(root.right);
    if (left == null) root.right = right;
    else {
      root.right = left;
      TreeNode tail = left;
      while (tail.right != null) tail = tail.right;
      tail.right = right;
    }
    root.left = null;
    return root;
  }
}
