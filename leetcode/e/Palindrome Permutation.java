/**
 * Given a string, determine if a permutation of the string could form a palindrome.
 * <p>
 * For example,
 * "code" -> False, "aab" -> True, "carerac" -> True.
 */

public class Solution {
  public boolean canPermutePalindrome(String s) {
    Map<Character, Integer> map = new HashMap();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      Integer num = map.get(c);
      if (num == null) map.put(c, 1);
      else map.put(c, num + 1);
    }
    int count = 0;
    for (char c : map.keySet()) {
      if (map.get(c) % 2 == 1) count++; // count odd number of char
    }
    return count < 2;
  }
}
