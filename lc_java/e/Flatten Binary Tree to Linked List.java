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
    flat(root);
  }

  TreeNode flat(TreeNode n) {
    if (n == null) return n;
    TreeNode l = flat(n.left);
    TreeNode r = flat(n.right);
    if (l != null) {
      n.right = l;
      while (l.right != null) l = l.right;
      l.right = r;
    }
    n.left = null;
    return n;
  }
}