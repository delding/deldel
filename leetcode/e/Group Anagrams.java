import java.util.Collections;
import java.util.HashMap;

/**
 Given an array of strings, group anagrams together.

 For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
 Return:

 [
 ["ate", "eat","tea"],
 ["nat","tan"],
 ["bat"]
 ]
 Note:
 For the return value, each inner list's elements must follow the lexicographic order.
 All inputs will be in lower-case.
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
}