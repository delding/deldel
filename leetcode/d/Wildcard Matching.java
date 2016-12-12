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


public class Solution {
	// dp
	public boolean isMatch(String s, String p) {
		boolean[][] match = new boolean[s.length() + 1][p.length() + 1];
		for (int i = 0; i <= p.length(); i++) {
			match[0][i] = i == 0 || p.charAt(i - 1) == '*';
			if (!match[0][i]) break;
		}
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < p.length(); j++) {
				if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
					match[i + 1][j + 1] = match[i][j];
				} else if (p.charAt(j) == '*') {
					match[i + 1][j + 1] = match[i + 1][j] || match[i][j + 1]; // * match 0 char or * match 1 char
				} else {
					match[i + 1][j + 1] = false;
				}
			}
		}
		return match[s.length()][p.length()];
	}

	// two pointers to hold the backup positions for pointers is,ip in s,p
	public boolean isMatch1(String s, String p) {
		for (int is = 0, ip = 0, mark = -1, star = -1; is <= s.length() && ip <= p.length();) {
			if (is == s.length() || ip == p.length()) {
				if (is == s.length() && ip == p.length()) return true;
				else if (is == s.length()) {
					if (p.charAt(ip) != '*') return false;
					else ip++;
				} else {
					if (star != -1) {
						ip = star + 1;
						is = mark++;
					} else {
						return false;
					}
				}
			} else {
				if (s.charAt(is) == p.charAt(ip) || p.charAt(ip) == '?') {
					is++;
					ip++;
				} else if (p.charAt(ip) == '*') {
					star = ip++;
					mark = is + 1;
				} else {
					if (star != -1) {
						ip = star + 1;
						is = mark++;
					} else {
						return false;
					}
				}
			}
		}
	}

  // TLE even if use memoization, todo
  public boolean isMatch2(String s, String p) {
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
}