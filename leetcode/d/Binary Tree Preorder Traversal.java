/**
 * Given a binary tree, return the preorder traversal of its nodes' values.
 * <p>
 * For example:
 * Given binary tree {1,#,2,3},
 * 1
 * \
 * 2
 * /
 * 3
 * return [1,2,3].
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
  public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> ret = new ArrayList<Integer>();
    if (root == null) return ret;
    Stack<TreeNode> st = new Stack<TreeNode>();
    TreeNode curr = root;
    while (curr != null || !st.isEmpty()) {
      if (curr == null) {
        curr = st.pop();
      }
      ret.add(curr.val);
      if (curr.right != null) st.push(curr.right);
      curr = curr.left;
    }
    return ret;
  }
}
