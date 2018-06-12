/**
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you.
 * You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language.
 * Derive the order of letters in this language.
 * <p>
 * For example,
 * Given the following words in dictionary,
 * <p>
 * [
 * "wrt",
 * "wrf",
 * "er",
 * "ett",
 * "rftt"
 * ]
 * The correct order is: "wertf".
 * <p>
 * Note:
 * You may assume all letters are in lowercase.
 * If the order is invalid, return an empty string.
 * There may be multiple valid order of letters, return any one of them is fine.
 */

public class Solution {
  public String alienOrder(String[] words) {
    Map<Character, Set<Character>> g = new HashMap<>();
    for (String w : words) {  // because some vertex can have no edges and will not be added to the graph
      for (char c : w.toCharArray()) {
        if (!g.containsKey(c)) g.put(c, new HashSet<>());
      }
    }
    for (int i = 0; i < words.length; i++) {
      String w1 = words[i];
      for (int j = i + 1; j < words.length; j++) {
        String w2 = words[j];
        int m = 0;
        for (; m < Math.min(w1.length(), w2.length()); m++) {
          if (w1.charAt(m) != w2.charAt(m)) {
            g.get(w1.charAt(m)).add(w2.charAt(m));
            break; // need to break
          }
        }
        // following this is only to pass the test case ["wrtkj","wrt"]
        if (m == w2.length() && w1.length() > w2.length()) return "";
      }
    }
    boolean[] visited = new boolean[26];
    Deque<Character> stack = new ArrayDeque<>();
    for (char c : g.keySet()) {
      if (!visited[c - 'a']) {
        if (!topoSort(c, g, stack, new boolean[26], visited)) return "";
      }
    }
    String ret = "";
    while (!stack.isEmpty()) ret = ret + stack.pop();
    return ret;
  }

  boolean topoSort(char cur, Map<Character, Set<Character>> g, Deque<Character> postorder, boolean[] visiting, boolean[] visited) {
    visiting[cur - 'a'] = true;
    for (char nei : g.get(cur)) {
      if (visiting[nei - 'a']) return false; // cycle
      if (!visited[nei - 'a']) {
        if (!topoSort(nei, g, postorder, visiting, visited)) return false;
      }
    }
    postorder.push(cur);
    visiting[cur - 'a'] = false;
    visited[cur - 'a'] = true;
    return true;
  }
}