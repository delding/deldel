/**
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList();
        if (root == null) return ret;
        Queue<TreeNode> q = new LinkedList();
        q.offer(root);
        while (!q.isEmpty()) {
            List<Integer> values = new ArrayList();
            int size = q.size();
            while (size-- > 0) {
                TreeNode node = q.poll();
                values.add(node.val);
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
            if (ret.size() % 2 == 1) Collections.reverse(values);
            ret.add(values);
        }
        return ret;
    }
}

// use two stacks (one called currentLevel and the other one called nextLevel)
// pop from stack currentLevel and print the node’s value. Whenever the current 
// level’s order is from left->right, you push the node’s left child, then its right child to stack nextLevel, the next time when nodes are popped off nextLevel, it will be in the reverse order
