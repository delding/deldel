/**
 * Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

For example:
Given a binary tree {1,2,3,4,5},
    1
   / \
  2   3
 / \
4   5
return the root of the binary tree [4,5,2,#,#,3,1].
   4
  / \
 5   2
    / \
   3   1  
**/

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
    // praent -> right, right -> left, left -> parent, recurse left subtree bottom up
    public TreeNode upsideDownBinaryTree(TreeNode root) {
    return dfsBottomUp(root, null);
  }

  private TreeNode dfsBottomUp(TreeNode p, TreeNode parent) {
    if (p == null) return parent;
    TreeNode root = dfsBottomUp(p.left, p); // no modification of return value, every recursion call return the same value
    p.left = (parent == null) ? parent : parent.right; // NOTE!!!!!! edge case for root node who has no parent
    p.right = parent;
    return root;
  }
    
    private TreeNode helper(TreeNode node) {
        if (node == null || node.left == null) return node; // ERROR: node can be null, when go right
        
        TreeNode left = helper(node.left);
        TreeNode right = helper(node.right);
        TreeNode root = left;
        while(left.right != null) left = left.right;
        left.right = node;
        left.left = right;
        return root;
    }
}
