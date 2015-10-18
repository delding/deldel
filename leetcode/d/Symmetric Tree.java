/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following is not:
    1
   / \
  2   2
   \   \
   3    3
Note:
Bonus points if you could solve it both recursively and iteratively.
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
    
    // recursive: each recursion compares two subtrees
    public boolean isSymmetric(TreeNode root) {
        if (root == null)   return true;
        return isSymmetric(root.left, root.right);
    }
    
    private boolean isSymmetric(TreeNode l, TreeNode r){
        if (l==null || r==null) return l==null && r==null;
        if (l.val != r.val) return false;
        return isSymmetric(l.left, r.right) && isSymmetric(l.right, r.left);
    }
    
    
    // iterative: level order traversal
    public boolean isSymmetric1(TreeNode root) {
        if (root == null) return true;
        if (root.left == null || root.right == null) return (root.right == null) && (root.left == null);
        Queue<TreeNode> left = new LinkedList<TreeNode>();
        Queue<TreeNode> right = new LinkedList<TreeNode>();
        left.offer(root.left);
        right.offer(root.right);
        while (!left.isEmpty()) {
            int size = left.size();
            while (size-- > 0) {
                TreeNode lnode = left.poll();
                TreeNode rnode = right.poll();
                if (lnode.val != rnode.val) return false;
                if (lnode.left != null) {
                    if (rnode.right == null) return false;
                    else {
                        left.offer(lnode.left);
                        right.offer(rnode.right);
                    }
                } else {
                    if (rnode.right != null) return false;
                }
                if (lnode.right != null) {
                    if (rnode.left == null) return false;
                    else {
                        left.offer(lnode.right);
                        right.offer(rnode.left);
                    }
                } else {
                    if (rnode.left != null) return false;
                }
            }
        }
        return true;
    }
    
    // more concise implementation
    public boolean isSymmetric2(TreeNode root) {
        if (root==null) return true;
        Queue<TreeNode> qu = new LinkedList<TreeNode>();
        qu.add(root.left);
        qu.add(root.right);
        while (!qu.isEmpty()){      // compare one pair each time
            TreeNode t1 = qu.poll();
            TreeNode t2 = qu.poll();
            if (t1==null && t2==null)  continue;
            if (t1==null || t2==null || t1.val != t2.val)   return false;
            qu.add(t1.left);
            qu.add(t2.right);
            qu.add(t1.right);
            qu.add(t2.left);
        }
        return true;
    }
}
