/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given an encoded message containing digits, determine the total number of ways to decode it.
 * <p>
 * For example,
 * Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
 * <p>
 * The number of ways decoding "12" is 2.
 */

public class Solution {
  public int numDecodings(String s) {
    if (s.isEmpty()) return 0; // ERROR: guard empty string
    Map<String, Integer> memo = new HashMap();
    //return ways(s, memo);
    return ways(s);
  }

  // top-down recursive dp
  private int ways(String s, Map<String, Integer> memo) { // ERROR: must memoization
    if (memo.containsKey(s)) return memo.get(s);
    if (s.length() == 0)
      return 1; // ERROR: guarantee empty string only results from valid prefix, so return 1
    if (s.length() == 1) { // ERROR: '0' is invalid, if end up with '0' return 0
      if (isValid(s)) return 1;
      else return 0;
    }
    int num = 0;
    if (isValid(s.substring(0, 1))) num += ways(s.substring(1), memo);
    if (isValid(s.substring(0, 2))) num += ways(s.substring(2), memo);
    memo.put(s, num);
    return num;
  }

  // bottom-up dp
  private int ways(String s) {
    if (s.length() == 0) return 0;
    int[] ways = new int[s.length() + 1];
    ways[0] = 1; // ERROR: length 0 return 1,  must guarantee empty string only results from valid suffix
    ways[1] = isValid(s.substring(0, 1)) ? 1 : 0;
    for (int i = 2; i <= s.length(); i++) {
      if (isValid(s.substring(i - 1, i))) ways[i] += ways[i - 1];
      if (isValid(s.substring(i - 2, i))) ways[i] += ways[i - 2];
    }
    return ways[s.length()];
  }

  private boolean isValid(String s) {
    if (s.length() > 2 || s.length() == 0) return false;
    else if (s.charAt(0) == '0') return false;
    else {
      int val = Integer.parseInt(s);
      return val >= 1 && val <= 26;
    }
  }
}
