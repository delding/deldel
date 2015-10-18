/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) return null;
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }
    
    private TreeNode build(int[] preorder, int preL, int preR, int[] inorder, int inL, int inR) {
        if (preR < preL) return null;
        int rootVal = preorder[preL];
        int mid = inL;
        for (int i = inL; i <= inR; i++) {
            if (inorder[i] == rootVal) {
                mid = i;
                break;
            }
        }
        int leftSize = mid - inL;
        int rightSize = inR - mid;
        TreeNode root = new TreeNode(rootVal);
        root.left = build(preorder, preL + 1, preL + leftSize, inorder, inL, mid - 1);
        root.right = build(preorder, preL + leftSize + 1, preR, inorder, mid + 1, inR);
        return root;
    }
}
