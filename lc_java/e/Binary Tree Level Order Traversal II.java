/**
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
 * <p>
 * For example:
 * Given binary tree {3,9,20,#,#,15,7},
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its bottom-up level order traversal as:
 * [
 * [15,7],
 * [9,20],
 * [3]
 * ]
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

// or use a stack store nodes, level order traversal by go right child first then left child, push node onto stack upon traversal
public class Solution {
  public List<List<Integer>> levelOrderBottom(TreeNode root) {
    List<List<Integer>> ret = new ArrayList();
    if (root == null) return ret;
    Queue<TreeNode> q = new LinkedList();
    q.offer(root);
    while (!q.isEmpty()) {
      List<Integer> values = new ArrayList<Integer>();
      int size = q.size();
      while (size-- > 0) {
        TreeNode node = q.poll();
        values.add(node.val);
        if (node.left != null) q.offer(node.left);
        if (node.right != null) q.offer(node.right);
      }
      ret.add(values);
    }
    Collections.reverse(ret);
    return ret;
  }
}
