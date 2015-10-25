/**
 Write a function to find the longest common prefix string amongst an array of strings.
 **/

public class Solution {
  public String longestCommonPrefix(String[] strs) {
    String prefix = "";
    if (strs.length == 0) return prefix;
    for (int i = 0; i < strs[0].length(); i++) {
      char c = strs[0].charAt(i);
      for (String str : strs) {
        if (str.length() == i || str.charAt(i) != c) return prefix;
      }
      prefix += c;
    }
    return prefix;
  }
}