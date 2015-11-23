/**
 * Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.
 * Return all such possible sentences.
 * For example, given
 * s = "catsanddog",
 * dict = ["cat", "cats", "and", "sand", "dog"].
 * A solution is ["cats and dog", "cat sand dog"].
 */

public class Solution {

  // bottom-up DP: word1 + word2 + ... + wordn = string(0, n) for all possible words
  public List<String> wordBreak(String s, Set<String> wordDict) {
    // words end at the same index of s
    List<Set<String>> memo = new ArrayList<Set<String>>(s.length()); // ERROR: this is a empty list with only capacity = s.length(), but size = 0
    for (int i = 0; i < s.length(); i++) memo.add(new HashSet<String>());
    for (int i = 0; i < s.length(); i++) {
      if (i > 0 && memo.get(i - 1).isEmpty())
        continue; // ERROR: TLE without this optimazion, skip curr index if no words end at previous index
      for (String word : wordDict) {
        int len = word.length();
        if (i + len <= s.length() && s.substring(i, i + len).equals(word)) {
          Set<String> set = memo.get(i + len - 1);
          set.add(word);
        }
      }
    }
    List<String> ret = new ArrayList<String>();
    dfs(memo, ret, "", s.length() - 1);
    return ret;
  }

  // imagine different idx denote different vertex, words at this idx are out edges, dfs starts at a vertex exlpore all its edges
  private void dfs(List<Set<String>> memo, List<String> ret, String sentence, int idx) {
    if (idx == -1) { // ERROR: -1 not 0, length 1 word ends at index 0, after take this word index become -1
      ret.add(sentence);
      return;
    }
    if (memo.get(idx).isEmpty())
      return; // ERROR: words end at index idx can be zero, need to check first
    for (String word : memo.get(idx)) {
      int len = word.length();
      String sen = sentence.length() == 0 ? word : word + " " + sentence;
      dfs(memo, ret, sen, idx - len);
    }
  }
}
