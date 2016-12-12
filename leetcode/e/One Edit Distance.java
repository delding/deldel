/**
 * Given two strings S and T, determine if they are both one edit distance apart.
 */

public class Solution {
  public boolean isOneEditDistance(String s, String t) {
    if (Math.abs(s.length() - t.length()) > 1) return false;
    int i = 0, j = 0;
    if (s.length() < t.length()) return isOneEditDistance(t, s);
    else if (s.length() >t.length()) {
      for (; i < s.length() && j < t.length(); i++, j++) {
        if (s.charAt(i) != t.charAt(j)) {
          if (i == j) j--; // skip char in s, reexamine char in t
          else return false;
        }
      }
      return true;
    } else {
      boolean edited = false;
      for (; i < s.length(); i++) {
        if (s.charAt(i) != t.charAt(i)) {
          if (edited) return false;
          else edited = true;
        }
      }
      return edited;
    }
  }
}