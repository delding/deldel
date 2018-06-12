/**
 * Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * <p>
 * For example, given
 * s = "leetcode",
 * dict = ["leet", "code"].
 * <p>
 * Return true because "leetcode" can be segmented as "leet code".
 */

public class Solution {

  // bottom up
  public boolean wordBreak(String s, Set<String> wordDict) {
    boolean[] canReach = new boolean[s.length()];
    for (int i = 0; i < canReach.length; i++) {
      if (i != 0 && !canReach[i - 1]) continue;
      for (int j = i + 1; j <= canReach.length; j++) {
        if (wordDict.contains(s.substring(i, j))) {
          canReach[j - 1] = true;
          if (j == canReach.length) return true;
        }
      }
    }
    return false;
  }

  // top down
  Map<String, Boolean> memo = new HashMap();

  public boolean wordBreak(String s, Set<String> wordDict) {
    if (s.length() == 0) return true;
    if (memo.containsKey(s)) return memo.get(s);
    // DP: string(0, n) = word(0,i) + string(i, n), for all possible word(0,i)
    // O(n^2), n number of sub-problems, each sub-problem is O(n), but O(2^n) if don't cache sub-problem
    for (int i = 1; i <= s.length(); i++) {
      if (wordDict.contains(s.substring(0, i))) {
        if (wordBreak(s.substring(i), wordDict)) {
          memo.put(s, true);
          return true;
        }
      }
    }
    memo.put(s, false);
    return false;
  }
}
