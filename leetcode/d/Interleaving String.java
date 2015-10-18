/**
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

For example,
Given:
s1 = "aabcc",
s2 = "dbbca",

When s3 = "aadbbcbcac", return true.
When s3 = "aadbbbaccc", return false.
* */

public class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 + len2 != s3.length()) return false;
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        dp[0][0] = true;
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }
        for (int i = 1; i <= len2; i++) {
            dp[0][i] = dp[0][i - 1] && s2.charAt(i - 1) == s3.charAt(i - 1);
        }
        for (int len = 2; len <= len1 + len2; len++) {
            for (int i = 1; i <= len1; i++) {
                int j = len - i;
                if (j > len2 || j < 1) continue;
                if (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(len - 1)) dp[i][j] = true;
                else if (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(len - 1)) dp[i][j] = true;
                else dp[i][j] = false;
            }
        }
        return dp[len1][len2];
    }
}
