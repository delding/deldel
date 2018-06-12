/**
 * Given a pattern and a string str, find if str follows the same pattern.
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.
 * Examples:
 * pattern = "abab", str = "redblueredblue" should return true.
 * pattern = "aaaa", str = "asdasdasdasd" should return true.
 * pattern = "aabb", str = "xyzabcxzyabc" should return false.
 * Notes:
 * You may assume both pattern and str contains only lowercase letters.
 **/

public class Solution {
  public boolean wordPatternMatch(String pattern, String str) {
    return match(pattern, str, new HashMap<String, String>(), new HashMap<String, String>());
  }

  private boolean match(String p, String str, Map<String, String> map, Map<String, String> invert) {
    if (p.isEmpty() || str.isEmpty()) return p.isEmpty() && str.isEmpty();
    if (map.containsKey(p.substring(0, 1))) {
      String word = map.get(p.substring(0, 1));
      if (str.indexOf(word) != 0) return false;
      else return match(p.substring(1), str.substring(word.length()), map, invert);
    }
    for (int i = 0; i < str.length(); i++) {
      String word = str.substring(0, i + 1);
      if (invert.containsKey(word)) continue;
      map.put(p.substring(0, 1), word);
      invert.put(word, p.substring(0, 1));
      if (match(p.substring(1), str.substring(i + 1), map, invert)) return true;
      map.remove(p.substring(0, 1));
      invert.remove(word);
    }
    return false;
  }
}

