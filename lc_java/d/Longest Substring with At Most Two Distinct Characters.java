/**
 * Given a string, find the length of the longest substring T that contains at most 2 distinct characters.
 * <p>
 * For example, Given s = “eceba”,
 * <p>
 * T is "ece" which its length is 3.
 */

public class Solution {
  public int lengthOfLongestSubstringTwoDistinct(String s) {
    int maxlen = 0;
    int numchar1 = 0;
    int numchar2 = 0;
    char c1 = ' ';
    char c2 = ' ';
    int i = 0;
    int j = 0;
    while (j < s.length()) {
      char c = s.charAt(j);
      if (c1 == ' ' || c2 == ' ') {
        if (c1 == ' ' && c != c2) {
          c1 = c;
          numchar1++;
        } else if (c2 == ' ' && c != c1) {
          c2 = c;
          numchar2++;
        } else if (c == c1) {
          numchar1++;
        } else numchar2++;
      } else {
        if (c == c1) {
          numchar1++;
        } else if (c == c2) numchar2++;
        else {
          maxlen = Math.max(maxlen, numchar1 + numchar2);
          while (numchar1 != 0 && numchar2 != 0) {
            if (s.charAt(i) == c1) numchar1--;
            else numchar2--;
            i++;
          }
          if (numchar1 == 0) {
            c1 = s.charAt(j);
            numchar1++;
          } else {
            c2 = s.charAt(j);
            numchar2++;
          }
        }
      }
      j++;
    }
    maxlen = Math.max(maxlen, numchar1 + numchar2); // ERROR: update for last string when j moves out of bound legally
    return maxlen;
  }
}
