import java.util.HashMap;

/**
 * Implement wildcard pattern matching with support for '?' and '*'.
 * <p>
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
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
 * isMatch("aa", "*") → true
 * isMatch("aa", "a*") → true
 * isMatch("ab", "?*") → true
 * isMatch("aab", "c*a*b") → false
 **/

// TLE even if use memoization, todo
public class Solution {
  public boolean isMatch(String s, String p) {
    if (s.isEmpty() || p.isEmpty()) return s.isEmpty() && p.isEmpty();
    char c = p.charAt(0);
    if (c == '?') {
      return isMatch(s.substring(1), p.substring((1)));
    } else if (c == '*') {
      for (int i = 0; i <= s.length(); i++) {
        if (isMatch(s.substring(i), p.substring(1)))
          return true; // from * replace empty string to * replace entire string
      }
      return false;
    } else {
      if (s.charAt(0) != c) return false;
      else return isMatch(s.substring(1), p.substring(1));
    }
  }


  // The trick is to maintain two pointers to hold the backup positions for pointers i,j in s,p
  // Once we find that s.substring(i, j) can not be replaced by '*', we try s.subtring(i+1,j).
  public boolean isMatch2(String s, String p) {
    int i = 0;
    int j = 0;
    int star = -1;
    int mark = -1;
    while (i < s.length()) {
      if (j < p.length()
          && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {
        ++i;
        ++j;
      } else if (j < p.length() && p.charAt(j) == '*') { // only need to mark most recent '*', ab*cd* is better than ab*
        star = j++;
        mark = i; // first time meet '*', match nothing in s
      } else if (star != -1) {
        j = star + 1; // if not match og back to '*'
        i = ++mark; // match out one more char in s
      } else {
        return false;
      }
    }
    while (j < p.length() && p.charAt(j) == '*') {
      ++j;
    }
    return j == p.length();
  }
}