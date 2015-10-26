/**
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * <p>
 * For example,
 * S = "ADOBECODEBANC"
 * T = "ABC"
 * Minimum window is "BANC".
 * <p>
 * Note:
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * <p>
 * If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 */

public class Solution {
  public String minWindow(String s, String t) {
    int minLen = s.length() + 1; // + 1 to makes it larger than largest possible min, i.e. s.length
    int start = -1; // ERROR: set to -1, for no such window in s
    int tlen = t.length();
    Map<Character, Integer> tmap = new HashMap();
    for (char c : t.toCharArray()) {
      Integer num = tmap.get(c);
      if (num == null) tmap.put(c, 1);
      else tmap.put(c, num + 1);
    }
    Map<Character, Integer> found = new HashMap();
    for (Character c : tmap.keySet()) found.put(c, 0);
    int i = 0;
    for (int j = 0; j < s.length(); j++) {
      char c = s.charAt(j);
      Integer numt = tmap.get(c);
      if (numt != null) {
        Integer numf = found.get(c);
        if (numf < numt) {
          tlen--;
        }
        found.put(c, numf + 1);
        while (tlen == 0) {
          if (j - i + 1 < minLen) {
            start = i;
            minLen = j - i + 1;
          }
          char cc = s.charAt(i);
          Integer numff = found.get(cc);
          if (numff != null) {
            if (numff <= tmap.get(cc)) {
              tlen++;
            }
            found.put(cc, numff - 1);
          }
          i++;
        }
      }
    }
    return start == -1 ? "" : s.substring(start, start + minLen);
  }
}
