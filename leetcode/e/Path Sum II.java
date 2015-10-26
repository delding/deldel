/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 * <p>
 * For example:
 * Given the below binary tree and sum = 22,
 * 5
 * / \
 * 4   8
 * /   / \
 * 11  13  4
 * /  \    / \
 * 7    2  5   1
 * return
 * [
 * [5,4,11,2],
 * [5,8,4,5]
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
public class Solution {
  public List<List<Integer>> pathSum(TreeNode root, int sum) {
    List<List<Integer>> paths = new ArrayList();
    if (root == null) return paths;
    List<Integer> path = new ArrayList();
    dfs(root, sum, paths, path);
    return paths;
  }

  private void dfs(TreeNode root, int sum, List<List<Integer>> paths, List<Integer> path) {
    path.add(root.val);
    if (root.left == null && root.right == null) {
      if (sum == root.val) paths.add(new ArrayList<Integer>(path));
    } else {
      if (root.left != null) dfs(root.left, sum - root.val, paths, path);
      if (root.right != null) dfs(root.right, sum - root.val, paths, path);
    }
    path.remove(path.size() - 1);
  }
}
