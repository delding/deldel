/**
 * Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.
 * Return all such possible sentences.
 * For example, given
 * s = "catsanddog",
 * dict = ["cat", "cats", "and", "sand", "dog"].
 * A solution is ["cats and dog", "cat sand dog"].
 */

public class Solution {
  public List<String> wordBreak(String s, Set<String> wordDict) {
    List<Set<Integer>> backtrace = new ArrayList<>();
    for (int i = 0; i < s.length(); i++) backtrace.add(new HashSet<>());
    for (int i = 0; i < s.length(); i++) {
      if (i == 0 || backtrace.get(i - 1).size() > 0) { // This condition is an optimization
        for (int j = i + 1; j <= s.length(); j++) {
          String w = s.substring(i, j);
          if (wordDict.contains(w)) {
            backtrace.get(j - 1).add(i);
          }
        }
      }
    }
    List<String> ret = new ArrayList<>();
    dfs(ret, s, backtrace, backtrace.size() - 1, "");
    return ret;
  }

  void dfs(List<String> ret, String s, List<Set<Integer>> bt, int i, String path) {
    if (i == -1) ret.add(path.substring(1));
    else {
      for (int beg : bt.get(i)) {
        dfs(ret, s, bt, beg - 1, " " + s.substring(beg, i + 1) + path);
      }
    }
  }
}



