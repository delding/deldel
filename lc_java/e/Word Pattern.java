import java.util.HashMap;

/**
 * Given a pattern and a string str, find if str follows the same pattern.
 * <p>
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
 * <p>
 * Examples:
 * pattern = "abba", str = "dog cat cat dog" should return true.
 * pattern = "abba", str = "dog cat cat fish" should return false.
 * pattern = "aaaa", str = "dog cat cat dog" should return false.
 * pattern = "abba", str = "dog dog dog dog" should return false.
 * Notes:
 * You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.
 **/

public class Solution {
  public boolean wordPattern(String pattern, String str) {
    Map<String, String> charToWord = new HashMap<>();
    Map<String, String> wordToChar = new HashMap<>();
    String[] strs = str.split(" ");
    if (pattern.length() != strs.length) return false;
    for (int i = 0; i < pattern.length(); i++) {
      String cha = pattern.substring(i, i + 1);
      String word = strs[i];
      if (charToWord.containsKey(cha)) {
        if (!word.equals(charToWord.get(cha))) {
          return false;
        }
      }
      if (wordToChar.containsKey(word)) {
        if (!cha.equals(wordToChar.get(word))) {
          return false;
        }
      }
      charToWord.put(cha, word);
      wordToChar.put(word, cha);
    }
    return true;
  }
}