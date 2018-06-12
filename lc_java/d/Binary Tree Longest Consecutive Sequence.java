/**
 * Given a binary tree, find the length of the longest consecutive sequence path.
 The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
 The longest consecutive path need to be from parent to child (cannot be the reverse).
 For example,
 1
 \
 3
 / \
 2   4
      \
       5
 Longest consecutive sequence path is 3-4-5, so return 3.
 2
  \
   3
   /
 2
 /
 1
 Longest consecutive sequence path is 2-3,not3-2-1, so return 2.
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

  int max = 0;

  public int longestConsecutive(TreeNode root) {
    localLen(root);
    return max;
  }

  public int localLen(TreeNode root) {
    if (root == null) return 0;
    int curlen = 1;
    int leftLen = localLen(root.left);
    int rightLen = localLen(root.right);
    if (root.left != null && root.val + 1 == root.left.val) curlen += leftLen;
    if (root.right != null && root.val + 1 == root.right.val) {
      curlen = Math.max(curlen, 1 + rightLen);
    }
    max = Math.max(max, curlen);
    return curlen;
  }
}