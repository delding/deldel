/**
 * Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

For example:
Given binary tree,
              5
             / \
            1   5
           / \   \
          5   5   5
return 4.
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
    // post order, when leaving the node bubble up the number of uni-value subtrees of this node
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) return 0;
        return helper(root).count;

    }
    
    private Count helper(TreeNode root) {
        if (root.left == null && root.right == null) return new Count(1, true);
        int count = 0;
        Count leftNo = null, rightNo = null;
        boolean wholeUni = true;
        if (root.left != null) {
            leftNo = helper(root.left);
            count += leftNo.count;
            // ERROR: just check root.val = left.val = right.val is not enough to determine if the whole tree is uni-val
            // left and right subtree must be uni-val 
            if (!leftNo.wholeUni || root.left.val != root.val) wholeUni = false; 
        }
        if (root.right != null) {
            rightNo = helper(root.right);
            count += rightNo.count;
            if (!rightNo.wholeUni || root.right.val != root.val) wholeUni = false;
        }
        if (wholeUni) count++;
        return new Count(count, wholeUni);
    }
    
    private class Count {
        int count;
        boolean wholeUni; // denote if the whole tree is uni-value
        
        public Count(int c, boolean b) {count =c; wholeUni = b;}
    }
}
