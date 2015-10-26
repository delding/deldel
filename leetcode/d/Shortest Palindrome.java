/**
 * Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.
 * <p>
 * For example:
 * <p>
 * Given "aacecaaa", return "aaacecaaa".
 * <p>
 * Given "abcd", return "dcbabcd".
 */

public class Solution {
  // reverse(s) + s is the longest palindrome, and the longest overlap of suffix(reverse(s)) and prefix(s) is the part that can be saved
  // so need to compute longest common part of prefix(s) and suffix(reverse(s))
  public String shortestPalindrome(String s) {
    String reverse = new StringBuilder(s).reverse().toString();
    String concat = s + "#" + reverse; // # makes the longest commone part is at most s it self
    int[] lps = lps(concat);
    int extra = s.length() - lps[lps.length - 1];
    return reverse.substring(0, extra) + s;
  }


  private int[] lps(String pattern) {
    // use prefix of pattern to match suffix of pattern, so as to compute the longest prefix which is also suffix
    // i denote the next character to be matched in the matching suffix, and j denote the next character to be matched in the matching prefix
    // when mismatch happens, move j backward to locate the possible position for matching suffix by recursively query the lps of the left part of j
    // algorithm starts with j = 0, i = 1, i.e., match the first character (which is prefix of length 2 string) with the second character (which is suffix of length 2 string) to compute the lps of length 2 string of pattern, the lps of length 1 string of pattern is always 0
    // since j is the next character to be matched in the matching prefix, 0 to j - 1, is the matching prefix, and j is also the length of matching prefix
    int len = pattern.length();
    int lps[] = new int[len]; // lps[j] is the length of lps for pattern.substring(0, j+1)
    lps[0] = 0;
    int i = 1, j = 0;
    while (i < len) {
      if (pattern.charAt(i) == pattern.charAt(j)) {
        lps[i++] = ++j;
      } else {
        // if not match and j = 0, means i-th lps = 0
        // otherwise check lps[j-1], to make matching continue, this is matching prefix's lps, i.e. the length of next possible matching prefix, and after set j = lps[j-1], j is the index of next character in the matching prefix string that needs to be matched with i
        if (j == 0) lps[i++] = 0;
        else {
          j = lps[j - 1];
        }
      }
    }
    return lps;
  }

  private int kmp(String txt, String pattern) {
    int len = pattern.length();
    int[] lps = lps(pattern);
    int i = 0;
    int j = 0;
    while (i <= txt.length() - len) {
      if (txt.charAt(i) == pattern.charAt(j)) {
        i++;
        j++;
        if (j == len)
          return i - j; // to find next match, set j = lps[len - 1] i.e. the lps of entire pattern
      } else {
        if (j == 0) i++;
        else
          j = lps[j - 1]; // length of lps of left matching part is also the next j position, because the part before lps[j-1] is the matching prefix of left part's suffix, lps[j-1] is the next position that needs to be matched
      }
    }
    return -1;
  }
}
