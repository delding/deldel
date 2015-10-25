/**
 * Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:
Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.
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
   
    public int closestValue(TreeNode root, double target) {
        int big = root.val; // record next small and next big value of current node
        int small = root.val;
        while (true) {
            if(target < root.val) {
                if (root.left != null) {
                    big = root.val; // go left update big
                    root = root.left;
                } else { // current node is big, so closest value between current node and next small
                    if (Math.abs(target - root.val) < Math.abs(target - small)) return root.val;
                    else return small;
                }
            } else {
                if (root.right != null) {
                    small = root.val; // go right update small
                    root = root.right;
                } else { // curr is small, so closeset between curr and next big
                    if (Math.abs(target - root.val) < Math.abs(target - big)) return root.val;
                    else return big;
                }
            }
        }
    }
    /* 
    // update closest value for every node on the path
    public int closestValue(TreeNode root, double target) {
        int closestVal = root.val; 
        while(root != null){ 
            //update closestVal if the current value is closer to target
            closestVal = (Math.abs(target - root.val) < Math.abs(target - closestVal))? root.val : closestVal;
            if(closestVal == target){   //already find the best result
                return closestVal;
            }
            root = (root.val > target)? root.left: root.right;   //binary search
        }
        return closestVal;
    }
    */
        
}
