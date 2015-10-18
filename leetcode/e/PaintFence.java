/**
 * There is a fence with n posts, each post can be painted with one of the k colors.

You have to paint all the posts such that no more than two adjacent fence posts have the same color.

Return the total number of ways you can paint the fence.

Note:
n and k are non-negative integers.
* */

public class Solution {
    // last two different, diff(n) = (k - 1) * (diff(n-1) + same(n-1))
    // last two same, same(n) = diff(n-1)
    public int numWays(int n, int k) {
        if (n==0) return 0;
        if (n ==1) return k; // ERROR: I count from n=2, so don't forget n=0 and n=1 as edge cases
        int same = k; // n = 2
        int diff = k * (k-1); // n =2
        for (int i =3; i <=n; i++) {
            int nSame = diff;
            int nDiff = (k-1) * (diff + same);
            same = nSame;
            diff = nDiff;
        }
        return same+diff;
    }
}
