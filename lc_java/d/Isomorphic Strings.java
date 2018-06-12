/**
 * Given two strings s and t, determine if they are isomorphic.
 * <p>
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * <p>
 * All occurrences of a character must be replaced with another character while preserving the order
 * of characters. No two characters may map to the same character but a character may map to itself.
 * <p>
 * For example,
 * Given "egg", "add", return true.
 * <p>
 * Given "foo", "bar", return false.
 * <p>
 * Given "paper", "title", return true.
 * <p>
 * Note:
 * You may assume both s and t have the same length.
 */

public class Solution {
  public boolean isIsomorphic(String s, String t) {
    Map<Character, Character> map = new HashMap();
    Set<Character> taken = new HashSet();
    for (int i = 0; i < s.length(); i++) {
      char cs = s.charAt(i);
      char ct = t.charAt(i);
      Character c = map.get(cs);
      if (c == null) {
        if (taken.contains(ct)) return false; // ERROR: need check if ct is already mapped
        taken.add(ct);
        map.put(cs, ct);
      } else {
        if (c != ct) return false;
      }
    }
    return true;
  }
}
