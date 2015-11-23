/**
 * Given a string S and a string T, count the number of distinct subsequences in S that is equals to T.
 * <p>
 * A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters
 * without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 * <p>
 * Here is an example:
 * S = "rabbbit", T = "rabbit"
 * <p>
 * Return 3.
 */

public class Solution {
  // number of distinct subsequences of s that is equal to t
  public int numDistinct(String s, String t) {
    int lenS = s.length();
    int lenT = t.length();
    int[][] dp = new int[lenS + 1][lenT + 1];
    // ERROR: initialization, length of s is 0, length of t not 0, distinct number is 0
    for (int i = 1; i <= lenT; i++) {
      dp[0][i] = 0;
    }
    // ERROR: initialization, length of t is 0, all length of s including 0, has empty string equals to t, so distinct number is 1
    for (int i = 0; i <= lenS; i++) {
      dp[i][0] = 1;
    }
    dp[0][1] = 0;
    for (int i = 1; i <= lenS; i++) {
      for (int j = 1; j <= lenT; j++) {
        if (s.charAt(i - 1) == t.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
        } else {
          dp[i][j] = dp[i - 1][j];
        }
      }
    }
    return dp[lenS][lenT];
  }
}
