/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given an encoded message containing digits, determine the total number of ways to decode it.
 * For example,
 * Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
 * The number of ways decoding "12" is 2.
 */

public class Solution {

  public int numDecodings(String s) {
    if (s.length() == 0) return 0; // must check
    Map<Integer, Integer> memo = new HashMap<>();
    return ways(s, memo);
  }

  int ways(String s, Map<Integer, Integer> memo) {
    if (s.length() == 0) return 1;
    if (s.startsWith("0")) return 0;
    if (s.length() == 1) return 1;
    if (memo.containsKey(s.length())) return memo.get(s.length());
    int num = ways(s.substring(1), memo);
    if (Integer.parseInt(s.substring(0, 2)) <= 26) num += ways(s.substring(2), memo);
    memo.put(s.length(), num);
    return num;
  }

  // bottom-up dp
  public int numDecodings(String s) {
    if (s.length() == 0) return 0;
    int[] ways = new int[s.length()];
    ways[s.length() - 1] = s.charAt(s.length() - 1) == '0' ? 0 : 1;
    for (int i = s.length() - 2; i >= 0; i--) {
      int way = 0;
      if (s.charAt(i) != '0') { // both one digit and two digits cannot start with '0'
        way += ways[i + 1];
        if (1 <= Integer.parseInt(s.substring(i, i + 2)) && 26 >= Integer.parseInt(s.substring(i, i + 2))) {
          way += (i + 2 == s.length()) ? 1 : ways[i + 2];
        }
      }
      ways[i] = way;
    }
    return ways[0];
  }

  // constant space
  public int numDecodings(String s) {
    if (s.isEmpty()) return 0;
    int pre = 1, prepre = 1;
    for (int i = s.length() - 1; i >= 0; i--) {
      int way = 0;
      if (s.charAt(i) >= '1' && s.charAt(i) <= '9') way += pre;
      if (i + 1 < s.length() && s.charAt(i) != '0' && Integer.parseInt(s.substring(i, i + 2)) <= 26 && Integer.parseInt(s.substring(i, i + 2)) >= 1) way += prepre;
      prepre = pre;
      pre = way;
    }
    return pre;
  }
}
