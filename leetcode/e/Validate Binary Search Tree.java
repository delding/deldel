/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
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
    public boolean isValidBST(TreeNode root) {
         return inorder(root);
    }
    
    // min-max
    private boolean isValid(TreeNode node, long lower, long upper) {
        if (node == null) return true;
        if (node.val <= lower || node.val >= upper) return false;
        return isValid(node.left, lower, node.val) && isValid(node.right, node.val, upper);
    }
    
    // inorder
    TreeNode prev = null;
    private boolean inorder(TreeNode node) {
        if (node == null) return true;
        if (!inorder(node.left)) return false;
        if (prev != null && node.val <= prev.val) return false;
        prev = node;
        if (!inorder(node.right)) return false;
        return true;
    }
}
