/**
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
* */
public class Solution {
    
    private Map<Integer, Integer> memo = new HashMap();
    
    public int numSquares(int n) {
        if (memo.containsKey(n)) return memo.get(n);
        for (int i = 1; i*i <= n; i++) {
            if (i * i == n) return 1;
        }
        int minSize = Integer.MAX_VALUE;
        for (int i = 1; i*i < n; i++) {
            int size = numSquares(n - i * i);
            if (size < minSize) minSize = size;
        }
        memo.put(n, minSize + 1);
        return minSize + 1;
    }
}
