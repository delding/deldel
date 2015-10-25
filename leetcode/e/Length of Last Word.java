/**
 Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

 If the last word does not exist, return 0.

 Note: A word is defined as a character sequence consists of non-space characters only.

 For example,
 Given s = "Hello World",
 return 5.
 **/

public class Solution {
  public int lengthOfLastWord(String s) {
    int hi = s.length() - 1;
    while (hi >= 0 && s.charAt(hi) == ' ') {
      hi--;
    }
    int lo = hi;
    while (lo >= 0 && s.charAt(lo) != ' ') {
      lo--;
    }
    if (hi == -1 || s.charAt(hi) == ' ') return 0;
    else return hi - lo;
  }
}