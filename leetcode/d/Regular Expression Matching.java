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
  public boolean isMatch(String s, String p) {
    return match(s, p, 0, 0);
  }

  // notice: * and its pre char are binded together
  boolean match(String s, String p, int si, int pi) {
    if (si == s.length() && pi == p.length()) {
      return true;
    }
    if (pi == p.length()) return false;
    if (si == s.length()) { // bug: edge case
      if (pi + 1 < p.length() && p.charAt(pi + 1) == '*') return match(s, p, si, pi + 2);
      else return false;
    }
    if (pi + 1 == p.length() || p.charAt(pi + 1) != '*') {
      if (p.charAt(pi) == '.' || p.charAt(pi) == s.charAt(si)) {
        return match(s, p, si + 1, pi + 1);
      } else return false;
    } else {
      if (p.charAt(pi) == '.') {
        for (int i = si; i <= s.length(); i++) { // bug, should <= not <, i = si means not use .*, i = s.length means match every thing left in s
          if (match(s, p, i, pi + 2)) return true;
        }
      } else {
        if (match(s, p, si, pi + 2)) return true; // ignore 'c*'
        for (int i = si; i < s.length(); i++) {
          if (p.charAt(pi) == s.charAt(i)) {
            if (match(s, p, i + 1, pi + 2)) return true;
          } else break;
        }
      }
    }
    return false;
  }

  // another solution
  public class Solution {
    public boolean isMatch(String s, String p) {
      if (p.length() == 0) return s.length() == 0;
      if (p.length() == 1) {
        return ((s.length() == 1) && (s.equals(p) || p.charAt(0) == '.')); // "." doesn't match ""
      }
      if (s.length() == 0) return ((p.charAt(1) == '*') && isMatch(s, p.substring(2)));
      if (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.') {
        if (p.charAt(1) == '*') {
          // '*' is bound to its preceding char, when a 'char' matches 'char*',
          // you can either move on to next char of original string and compare to pattern string
          // or ignore 'char*' move on to next char of pattern string and compare to original string
          return isMatch(s.substring(1), p) || isMatch(s, p.substring(2));
          // if second char is not '*', compare their substrings starting from next char
        } else {
          return isMatch(s.substring(1), p.substring(1));
        }
      } else {
        // if first char doesn't match, pattern string's second char must be '*'
        // the first two char of pattern string can be ignored
        return (p.charAt(1) == '*') && isMatch(s, p.substring(2));
      }
    }
  }
}

