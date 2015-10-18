/**
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 **/

public class Solution {
    // BST guarantees that in two nodes case, 1, 2 if pick 1 as root 2 has only one place to go, i.e. whenever there is only left with one node, 
    // there is only one choice to place the node
    public int numTrees(int n) {
        Map<Integer, Integer> memo = new HashMap();
        //return numTrees(1, n, memo);
        return numTrees1(n);
    }
    
    // top down recursive
    private int numTrees(int lo, int hi, Map<Integer, Integer> memo) { // Error: must use memoization, only length of interval determine the number of trees
        if (lo >= hi) return 1;
        if (memo.containsKey(hi - lo + 1)) return memo.get(hi - lo + 1);
        int num = 0;
        for (int mid = lo; mid <= hi; mid++) {
            int left = numTrees(lo, mid - 1, memo);
            int right = numTrees(mid + 1, hi, memo);
            num += left * right;
        }
        memo.put(hi - lo + 1, num);
        return num;
    }
    
    // botton-up dp
    private int numTrees1(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) { // i is total number of nodes
            for (int j = 1; j <= i; j++) { // j is the node chosen to be root
                dp[i] += dp[j - 1] * dp[i - j]; // left node number j - 1, right node number i - j
            }
        }
        return dp[n];
    }
}
