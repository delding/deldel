import java.util.Collections;
import java.util.HashMap;

/**
 * Given an array of strings, group anagrams together.
 * <p>
 * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Return:
 * <p>
 * [
 * ["ate", "eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * Note:
 * For the return value, each inner list's elements must follow the lexicographic order.
 * All inputs will be in lower-case.
 **/

public class Solution {
  public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> map = new HashMap<>();
    for (String word : strs) {
      char[] k = word.toCharArray();
      Arrays.sort(k);
      String key = String.valueOf(k);
      List<String> list = map.get(key);
      if (list == null) {
        list = new ArrayList<String>();
        map.put(key, list);
      }
      list.add(word);
    }
    List<List<String>> ret = new ArrayList<>();
    for (List<String> l : map.values()) {
      Collections.sort(l);
      ret.add(l);
    }
    return ret;
  }

  // instead of sort, count int[26], e.g. apple, ppale -> 1a1e1l2p, use this sring as unique hash for each group
  // or map a to z -> prime number 2, 3, 5, 7, 11 ,13, 17, 19, ... use their multiplicaion as hash to unique identify a group of anagram
}