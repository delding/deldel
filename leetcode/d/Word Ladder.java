/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
 * <p>
 * Only one letter can be changed at a time
 * Each intermediate word must exist in the word list
 * For example,
 * <p>
 * Given:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * <p>
 * Note:
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 */

public class Solution {
  public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
    if (beginWord.equals(endWord)) return 1;
    int dist = 1;
    Queue<String> q = new ArrayDeque<>();
    q.add(beginWord);
    wordList.remove(beginWord);
    while (!q.isEmpty()) {
      dist++;
      int size = q.size();
      while (size-- > 0) {
        String w = q.poll();
        for (String nei : neighbors(w, wordList)) {
          if (nei.equals(endWord)) return dist;
          if (wordList.contains(nei)) {
            q.add(nei);
            wordList.remove(nei);
          }
        }
      }
    }
    return 0;
  }

  Set<String> neighbors(String w, Set<String> dict) {
    char[] cs = w.toCharArray();
    Set<String> ret = new HashSet<>();
    for (int i = 0; i < cs.length; i++) {
      char ci = cs[i];
      for (char c = 'a'; c <= 'z'; c++) {
        if (c != ci) {
          cs[i] = c;
          String nei = String.valueOf(cs);
          if (dict.contains(nei)) ret.add(nei);
        }
      }
      cs[i] = ci;
    }
    return ret;
  }
}
