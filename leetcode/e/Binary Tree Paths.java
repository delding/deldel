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
public class Solution {
  public List<String> binaryTreePaths(TreeNode root) {
    List<String> paths = new ArrayList<String>();
    if (root == null) return paths;
    dfs(root, paths, "");
    return paths;
  }

  private void dfs(TreeNode root, List<String> paths, String path) {
    if (path.isEmpty()) path += root.val;
    else path += "->" + root.val;
    if (root.left == null && root.right == null) {
      paths.add(path);
      return;
    } else {
      if (root.left != null) dfs(root.left, paths, path);
      if (root.right != null) dfs(root.right, paths, path);
    }
  }
}
