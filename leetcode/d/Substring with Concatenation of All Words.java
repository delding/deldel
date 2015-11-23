/**
 * You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that
 * is a concatenation of each word in words exactly once and without any intervening characters.
 * <p>
 * For example, given:
 * s: "barfoothefoobarman"
 * words: ["foo", "bar"]
 * <p>
 * You should return the indices: [0,9].
 * (order does not matter).
 **/

public class Solution {

  // TLE for large data set
  public List<Integer> findSubstring(String s, String[] words) {
    Map<String, Integer> counts = new HashMap<>(); // words can be duplicated
    for (String w : words) {
      Integer c = counts.get(w);
      if (c == null) counts.put(w, 1);
      else counts.put(w, c + 1);
    }
    int len = words[0].length();
    List<Integer> indexes = new ArrayList<>();
    for (int i = 0; i <= s.length() - words.length * len; i++) {
      String w = s.substring(i, i + len);
      if (counts.containsKey(w)) {
        Map<String, Integer> copy = new HashMap<>(counts);
        int cur = i;
        while (!copy.isEmpty() && copy.containsKey(s.substring(cur, cur + len))) {
          String word = s.substring(cur, cur + len);
          Integer cnt = copy.get(word);
          if (cnt == 1) copy.remove(word);
          else copy.put(word, cnt - 1);
          cur += len;
        }
        if (copy.isEmpty()) indexes.add(i);
      }
    }
    return indexes;
  }

  // recursive solution: stack overflow for large data set
  void dfs(String s, int len, int start, int cur, Set<String> remain, Set<String> dict, List<Integer> indexes) {
    if (remain.isEmpty()) indexes.add(start);
    else if (cur + remain.size() * len > s.length()) return;
    else if (!remain.contains(s.substring(cur, cur + len)))
      dfs(s, len, start + 1, start + 1, new HashSet<String>(dict), dict, indexes);
    else {
      remain.remove(s.substring(cur, cur + len));
      dfs(s, len, start, cur + len, remain, dict, indexes);
    }
  }
}