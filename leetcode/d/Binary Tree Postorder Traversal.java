/**
 * Given a binary tree, return the postorder traversal of its nodes' values.
 * <p>
 * For example:
 * Given binary tree {1,#,2,3},
 * 1
 * \
 * 2
 * /
 * 3
 * return [3,2,1].
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
  public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> ret = new ArrayList<Integer>();
    if (root == null) return ret;
    Stack<TreeNode> st = new Stack();
    TreeNode lastAdded = null;
    while (root != null || !st.isEmpty()) {
      if (root == null) {
        root = st.peek();
        if (root.right != null && root.right != lastAdded) root = root.right;
        else {
          ret.add(root.val);
          lastAdded = root;
          st.pop();
          root = null;
        }
      } else {
        st.push(root);
        root = root.left;
      }
    }
    return ret;
  }
}
