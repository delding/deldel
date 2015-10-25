/**
 Given a string S, find the longest palindromic substring in S. You may assume that the maximum
 length of S is 1000, and there exists one unique longest palindromic substring.
 **/

public class Solution {
  // dp
  public String longestPalindrome(String s) {
    String ret = "";
    boolean[][] lengths = new boolean[s.length()][s.length()];
    for (int len = 1; len <= s.length(); len++) {
      for (int i = 0; i < s.length() - len + 1; i++) {
        int j = len - 1 + i;
        if (len == 1) lengths[i][j] = true;
        else if (len == 2) lengths[i][j] = s.charAt(i) == s.charAt(j) ? true : false;
        else {
          lengths[i][j] = lengths[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
        }
        if (lengths[i][j] && (j - i + 1) > ret.length()) {
          ret = s.substring(i, j + 1);
        }
      }
    }
    return ret;
  }

  //another solution
  public String longestPalindrome(String s) {
    int length = s.length();
    String result = "";
    for (int i = 0; i < length; i++) {
      String ps = getPalindrome(s, i, i);
      if (ps.length() > result.length()) {
        result = ps;
      }
      ps = getPalindrome(s, i, i + 1);
      if (ps.length() > result.length()) {
        result = ps;
      }
    }
    return result;
  }

  private String getPalindrome(String s, int l, int r) {
    while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
      l--;
      r++;
    }
    return s.substring(l + 1, r);
  }
}
