/**
 * Given a binary tree, return the postorder traversal of its nodes' values.
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
    List<Integer> postorder = new ArrayList<>();
    Deque<TreeNode> stack = new ArrayDeque<>();
    TreeNode pre = null;
    while (root != null || !stack.isEmpty()) {
      if (root != null) {
        stack.push(root);
        root = root.left;
      } else {
        root = stack.peek();
        if (root.right != null && root.right != pre) root = root.right;
        else {
          stack.pop();
          pre = root;
          postorder.add(root.val);
          root = null;
        }
      }
    }
    return postorder;
  }
}
