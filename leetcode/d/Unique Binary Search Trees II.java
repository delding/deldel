/**
 * Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
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
    public List<TreeNode> generateTrees(int n) {
        Map<int[], List<TreeNode>> memo = new HashMap();
        return generate(1, n, memo);
    }
    
    // top down recursive
    private List<TreeNode> generate(int lo, int hi, Map<int[], List<TreeNode>> memo) {
        int[] interval = new int[] {lo, hi};
        if (memo.containsKey(interval)) return new ArrayList(memo.get(interval));
        List<TreeNode> roots = new ArrayList();
        if (lo > hi) roots.add(null);
        else {
            for (int mid = lo; mid <= hi; mid++) {
                List<TreeNode> lefts = generate(lo, mid - 1, memo);
                List<TreeNode> rights = generate(mid + 1, hi, memo);
                for (TreeNode left : lefts) {
                    for (TreeNode right : rights) {
                        TreeNode root = new TreeNode(mid);
                        root.left = left;
                        root.right = right;
                        roots.add(root);
                    }
                }
            }
        }
        memo.put(interval, roots);
        return new ArrayList<TreeNode>(roots);
    }
    
    // bottom-up, store List<TreeNode> in each cell of dp[]
}
