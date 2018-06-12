/**
 Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.

 Note:
 A subtree must include all of its descendants.
 Here's an example:
 10
 / \
 5  15
 / \   \
 1   8   7
 The Largest BST Subtree in this case is the highlighted one.
 The return value is the subtree's size, which is 3.
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
	int maxSize = 0;

	public int largestBSTSubtree(TreeNode root) {
		getSize(root);
		return maxSize;
	}

	int[] getSize(TreeNode root) { // return int[] {size, min, max}
		if (root == null) {
			return new int[] {0, Integer.MAX_VALUE, Integer.MIN_VALUE};
		}
		int[] left = getSize(root.left);
		int[] right = getSize(root.right);
		int leftSize = left[0];
		int rightSize = right[0];
		int leftMin =  left[1];
		int leftMax = left[2];
		int rightMin = right[1];
		int rightMax = right[2];
		if (leftSize != -1 && rightSize != -1 && root.val >= leftMax && root.val < rightMin) {
			int size = 1 + leftSize + rightSize;
			maxSize = Math.max(maxSize, size);
			return new int[] {size, Math.min(leftMin, Math.min(rightMin, root.val)), Math.max(rightMax, Math.max(leftMax, root.val))};
		} else {
			return new int[] {-1, Integer.MIN_VALUE, Integer.MAX_VALUE};
		}
	}
}