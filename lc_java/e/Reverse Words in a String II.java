/**
 * Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.
 * <p>
 * The input string does not contain leading or trailing spaces and the words are always separated by a single space.
 * <p>
 * For example,
 * Given s = "the sky is blue",
 * return "blue is sky the".
 **/

public class Solution {
  // swap two ends for reverse, reverse entire string, then reverse each word
  public void reverseWords(char[] s) {
    reverse(s, 0, s.length - 1);
    int lo = 0, hi = 0;
    while (hi <= s.length) {
      if (hi == s.length) reverse(s, lo, hi++ - 1); // last word
      else {
        if (s[hi] == ' ') {
          reverse(s, lo, hi - 1); // reverse each word
          lo = hi + 1;
        }
        hi++;
      }
    }
  }

  private void reverse(char[] s, int lo, int hi) {
    while (lo < hi) {
      char c = s[lo];
      s[lo] = s[hi];
      s[hi] = c;
      lo++;
      hi--;
    }
  }
}
