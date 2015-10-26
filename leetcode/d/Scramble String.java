import java.lang.Boolean;
import java.lang.String;

/**
 * Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.
 * <p>
 * Below is one possible representation of s1 = "great":
 * <p>
 * great
 * /    \
 * gr    eat
 * / \    /  \
 * g   r  e   at
 * / \
 * a   t
 * To scramble the string, we may choose any non-leaf node and swap its two children.
 * <p>
 * For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".
 * <p>
 * rgeat
 * /    \
 * rg    eat
 * / \    /  \
 * r   g  e   at
 * / \
 * a   t
 * We say that "rgeat" is a scrambled string of "great".
 * <p>
 * Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".
 * <p>
 * rgtae
 * /    \
 * rg    tae
 * / \    /  \
 * r   g  ta  e
 * / \
 * t   a
 * We say that "rgtae" is a scrambled string of "great".
 * <p>
 * Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
 **/

public class Solution {
  public boolean isScramble(String s1, String s2) {
    int len = s1.length();
    if (len == 0) return true;
    boolean[][][] dp = new boolean[len + 1][len][len]; // s1 substring start at i, s2 substring start at j, and length = len, scramble or not
    for (int l = 1; l <= len; l++) {
      for (int i = 0; i < len; i++) {
        for (int j = 0; j < len; j++) {
          if (l == 1) {
            dp[l][i][j] = s1.charAt(i) == s2.charAt(j);
            continue;
          }
          if (i + l <= len && j + l <= len) {
            for (int k = 1; k <= l - 1; k++) {
              dp[l][i][j] = dp[k][i][j] && dp[l - k][i + k][j + k] || dp[k][i + l - k][j] && dp[l - k][i][j + k];
              if (dp[l][i][j]) break;
            }
          }
        }
      }
    }
    return dp[len][0][0];
  }

  // TLE
  private boolean isScramble(String s1, String s2, Map<String, Boolean> memo) {
    String k1 = s1 + "+" + s2;
    String k2 = s2 + "+" + s1;
    if (memo.containsKey(k1)) return memo.get(k1);
    if (memo.containsKey(k2)) return memo.get(k2);
    if (s1.equals(s2)) return true;
    for (int i = 0; i < s1.length() - 1; i++) {
      String ss1 = s1.substring(i + 1) + s1.substring(0, i + 1);
      if (isScramble(s1.substring(i + 1), s2.substring(i + 1), memo) && isScramble(s1.substring(0, i + 1), s2.substring(0, i + 1), memo)))
      {
        memo.put(k1, true);
        memo.put(k1, true);
        return true;
      }
      if (isScramble(ss1.substring(i + 1), s2.substring(i + 1), memo) && isScramble(ss1.substring(0, i + 1), s2.substring(0, i + 1), memo)) {
        memo.put(k1, true);
        memo.put(k2, true);
        return true;
      }
    }
    memo.put(k1, false);
    memo.put(k2, false);
    return false;
  }
}