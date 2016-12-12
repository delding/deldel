/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
 * <p>
 * Only one letter can be changed at a time
 * Each intermediate word must exist in the word list
 * For example,
 * <p>
 * Given:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * Return
 * [
 * ["hit","hot","dot","dog","cog"],
 * ["hit","hot","lot","log","cog"]
 * ]
 **/

public class Solution {
  public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
    List<List<String>> ladders = new ArrayList<>();
    if (beginWord.equals(endWord)) {
      ladders.add(Arrays.asList(beginWord));
      return ladders;
    }
    Map<String, Set<String>> backtrack = new HashMap<>();
    boolean found = false;
    Queue<String> q = new ArrayDeque<>();
    Set<String> visited = new HashSet<>();
    q.add(beginWord);
    while (!found && !q.isEmpty()) {
      for (String w : q) { // mark all words in previous level as visited
        visited.add(w);
      }
      int size = q.size();
      Set<String> added = new HashSet<>();
      while (size-- > 0) {
        String w = q.poll();
        for (String nei : neighbors(w, wordList)) {
          if (nei.equals(endWord)) found = true;
          if (!visited.contains(nei)) { // explore all edges to next level
            Set<String> parents = backtrack.get(nei);
            if (parents == null) {
              parents = new HashSet<String>();
              backtrack.put(nei, parents);
            }
            parents.add(w); // explore every edges
            if (!added.contains(nei)) {
              q.add(nei);
              added.add(nei);
            }
          }
        }
      }
    }
    if (found) dfs(endWord, beginWord, backtrack, ladders, new ArrayList<String>());
    return ladders;
  }

  void dfs(String end, String begin, Map<String, Set<String>> backtrack, List<List<String>> ladders, List<String> ladder) {
    ladder.add(end);
    if (end.equals(begin)) {
      ladders.add(new ArrayList<>(ladder));
      Collections.reverse(ladders.get(ladders.size() - 1));
    } else {
      for (String parent : backtrack.get(end)) {
        dfs(parent, begin, backtrack, ladders, ladder);
      }
    }
    ladder.remove(ladder.size() - 1);
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