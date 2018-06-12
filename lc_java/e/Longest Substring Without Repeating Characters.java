/**
 * Given a string, find the length of the longest substring without repeating characters. For example, the
 * longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
 **/


public class Solution {
  public int lengthOfLongestSubstring(String s) {
    int max = 0;
    int start = 0;
    Set<Character> set = new HashSet<>();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (!set.contains(c)) {
        set.add(c);

      } else {
        while (s.charAt(start) != c) {
          set.remove(s.charAt(start++));
        }
        start++;
      }
      max = Math.max(max, set.size());
    }
    return max;
  }
}