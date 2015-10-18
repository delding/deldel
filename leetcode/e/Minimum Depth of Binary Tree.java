/**
 * Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
* */

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
    int minDep = Integer.MAX_VALUE; // use a global value to check with each depth
    
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        dfs(root, 1);
        return minDep;
    }
    
    private void dfs(TreeNode root, int dep) {
        if (root.left == null && root.right == null) { 
            minDep = Math.min(minDep, dep);
            return;
        }
        if (root.left != null) dfs(root.left, dep + 1);
        if (root.right != null) dfs(root.right, dep + 1);
    }
}
