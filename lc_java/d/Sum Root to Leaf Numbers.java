/**
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 * <p>
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * <p>
 * Find the total sum of all root-to-leaf numbers.
 * <p>
 * For example,
 * <p>
 * 1
 * / \
 * 2   3
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * <p>
 * Return the sum = 12 + 13 = 25.
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
  public int sumNumbers(TreeNode root) {
    if (root == null) return 0;
    return dfs(root, "");
  }

  // return all leaf numbers sum rooted at node, and number string begin with num
  private int dfs(TreeNode node, String num) {
    String prefix = num + node.val;
    if (node.left == null && node.right == null) {
      return Integer.parseInt(prefix);
    }
    int sum = 0;
    if (node.left != null) sum += dfs(node.left, prefix);
    if (node.right != null) sum += dfs(node.right, prefix);
    return sum;
  }
}
