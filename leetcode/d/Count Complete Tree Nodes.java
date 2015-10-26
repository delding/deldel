/**
 * Given a complete binary tree, count the number of nodes.
 * <p>
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
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

  // if left most leaf and right most leaf are at the same level, it's a perfect complete tree, return number of nodes with out counting
  // else recurse left and right subtree
  public int countNodes(TreeNode root) {
    if (root == null) return 0;

    int ldepth = 0;
    int rdepth = 0;
    TreeNode left = root; // ERROR: root is used multiple times, must make a copy
    while (left.left != null) {
      left = left.left;
      ldepth++;
    }
    TreeNode right = root; // ERROR: still need to copy, recursive needs root too
    while (right.right != null) {
      right = right.right;
      rdepth++;
    }
    if (ldepth == rdepth) return (1 << (ldepth + 1)) - 1;
    else {
      int lnum = countNodes(root.left);
      int rnum = countNodes(root.right);
      return 1 + lnum + rnum;
    }
  }
    
    /*
    // TLE
    // dfs go left first, maintain level and last leaf node number, whenever reach a leaf with level -1, return that last number number
    int depth = 0;
    int number = 0;
    boolean isFirstLeaf = true;
    public int countNodes2(TreeNode root) {
        if(root == null) return 0;
        dfs(root, 0, 1);
        return number;
    }
    
    private boolean dfs(TreeNode root, int level, int num) {
        if (root.left == null && root.right == null) {
            number = num;
            if(isFirstLeaf) {
                depth = level;
                isFirstLeaf = false;
                return false;
            } else {
                if (level == depth) {
                    return false;
                } else return true;
            }
        }
        if (root.left != null) if(dfs(root.left, level +1, 2*num)) return true;
        if (root.right != null) if(dfs(root.right, level + 1, 2*num +1)) return true;
        return false;
    }
    */
}
