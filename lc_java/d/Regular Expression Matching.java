/**
 * Implement regular expression matching with support for '.' and '*'.
 * <p>
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * <p>
 * The matching should cover the entire input string (not partial).
 * <p>
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 * <p>
 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "a*") → true
 * isMatch("aa", ".*") → true
 * isMatch("ab", ".*") → true
 * isMatch("aab", "c*a*b") → true
 **/

public class Solution {
  // dp
  public boolean isMatch(String s, String p) {
    boolean[][] match = new boolean[s.length() + 1][p.length() + 1];
    match[0][0] = true;
    for (int lp = 1; lp <= p.length(); lp++) { // "" could match "a*b*c*"
      if (p.charAt(lp - 1) == '*' && match[0][lp - 2]) match[0][lp] = true;
    }
    for (int ls = 1; ls <= s.length(); ls++) {
      for (int lp = 1; lp <= p.length(); lp++) {
        if (s.charAt(ls - 1) == p.charAt(lp - 1) || p.charAt(lp - 1) == '.') match[ls][lp] = match[ls - 1][lp - 1];
        else if (p.charAt(lp - 1) == '*') {
          if (p.charAt(lp - 2) == '.' || p.charAt(lp - 2) == s.charAt(ls - 1)) {
            match[ls][lp] = match[ls - 1][lp] // use * multiple time
                    || match[ls][lp - 2]; // use * as 0
          } else {
            match[ls][lp] = match[ls][lp - 2]; // use * as 0
          }
        } else match[ls][lp] = false;
      }
    }
    return match[s.length()][p.length()];
  }

  // memoization
  public boolean isMatch1(String s, String p) {
    int[][] memo = new int[s.length() + 1][p.length() + 1]; // 0 not visited, 1 true, 2 false
    return isMatch(s, 0, p, 0, memo);
  }

  boolean isMatch(String s, int is, String p, int ip, int[][] memo) {
    if (memo[is][ip] != 0) return memo[is][ip] == 1 ? true : false;
    boolean ret;
    if (is == s.length() || ip == p.length()) {
      if (is == s.length() && ip == p.length()) ret = true;
      else ret = ip + 1 < p.length() && p.charAt(ip + 1) == '*' && isMatch(s, is, p, ip + 2, memo);
    } else if (ip + 1 < p.length() && p.charAt(ip + 1) == '*') {
      if (p.charAt(ip) == '.' || p.charAt(ip) == s.charAt(is)) ret = isMatch(s, is, p, ip + 2, memo) || isMatch(s, is + 1, p, ip, memo);
      else ret = isMatch(s, is, p, ip + 2, memo);
    } else {
      if (p.charAt(ip) == '.' || p.charAt(ip) == s.charAt(is)) ret = isMatch(s, is + 1, p, ip + 1, memo);
      else ret = false;
    }
    memo[is][ip] = ret ? 1 : 2;
    return ret;
  }
}