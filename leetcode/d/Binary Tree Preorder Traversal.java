/**
 * Given a binary tree, return the preorder traversal of its nodes' values.
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
    List<Integer> preorder = new ArrayList<>();
    Deque<TreeNode> stack = new ArrayDeque<>();
    while (root != null || !stack.isEmpty()) {
      if (root != null) {
        preorder.add(root.val);
        if (root.right != null) stack.push(root.right);
        root = root.left;
      } else {
        root = stack.pop();
      }
    }
    return preorder;
  }
}