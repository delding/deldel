/**
 * Given a binary tree, return all root-to-leaf paths.
 * <p>
 * For example, given the following binary tree:
 * <p>
 * 1
 * /   \
 * 2     3
 * \
 * 5
 * All root-to-leaf paths are:
 * <p>
 * ["1->2->5", "1->3"]
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
  public List<String> binaryTreePaths(TreeNode root) {
    List<String> ret = new ArrayList<>();
    if (root == null) return ret;
    dfs(root, ret, "");
    return ret;
  }

  void dfs(TreeNode cur, List<String> paths, String p) {
    p = p.isEmpty() ? String.valueOf(cur.val) : p + "->" + cur.val;
    if (cur.left == null && cur.right == null) {
      paths.add(p);
    } else {
      if (cur.left != null) dfs(cur.left, paths, p);
      if (cur.right != null) dfs(cur.right, paths, p);
    }
  }
}