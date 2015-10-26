/**
 * Given inorder and postorder traversal of a tree, construct the binary tree.
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
  public TreeNode buildTree(int[] inorder, int[] postorder) {
    if (inorder.length == 0) return null;
    return build(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
  }

  private TreeNode build(int[] inorder, int inL, int inR, int[] postorder, int postL, int postR) {
    if (inL > inR) return null;
    int rootVal = postorder[postR];
    int mid = inL;
    for (int i = 0; i <= inR; i++) {
      if (inorder[i] == rootVal) {
        mid = i;
        break;
      }
    }
    int leftSize = mid - inL;
    TreeNode root = new TreeNode(rootVal);
    root.left = build(inorder, inL, mid - 1, postorder, postL, postL + leftSize - 1);
    root.right = build(inorder, mid + 1, inR, postorder, postL + leftSize, postR - 1);
    return root;
  }
}
