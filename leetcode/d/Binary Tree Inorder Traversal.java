/**
 * Given a binary tree, return the inorder traversal of its nodes' values.
 * <p>
 * For example:
 * Given binary tree {1,#,2,3},
 * 1
 * \
 * 2
 * /
 * 3
 * return [1,3,2].
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
  public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> ret = new ArrayList();
    Stack<TreeNode> st = new Stack();
    while (root != null) {
      st.push(root);
      root = root.left;
    }
    while (!st.isEmpty()) {
      TreeNode node = st.pop();
      ret.add(node.val);
      if (node.right != null) {
        node = node.right;
        while (node != null) {
          st.push(node);
          node = node.left;
        }
      }
    }
    return ret;
  }
}
