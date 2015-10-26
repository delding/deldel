import java.lang.Character;
import java.lang.String;

/**
 * Given a digit string, return all possible letter combinations that the number could represent.
 * <p>
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * <p>
 * <p>
 * <p>
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * Note:
 * Although the above answer is in lexicographical order, your answer could be in any order you want.
 **/

public class Solution {
  public List<String> letterCombinations(String digits) {
    Map<Character, String> maps = new HashMap<>();
    maps.put('2', "abc");
    maps.put('3', "def");
    maps.put('4', "ghi");
    maps.put('5', "jkl");
    maps.put('6', "mno");
    maps.put('7', "pqrs");
    maps.put('8', "tuv");
    maps.put('9', "wxyz");
    List<String> ret = new ArrayList<>();
    if (digits.isEmpty()) return ret; // edge case, otherwise "" will be add to the list
    dfs(digits, 0, maps, ret, "");
    return ret;
  }

  void dfs(String digits, int idx, Map<Character, String> map, List<String> ret, String cur) {
    if (idx == digits.length()) {
      ret.add(cur);
      return;
    }
    char d = digits.charAt(idx);
    String chars = map.get(d);
    for (int i = 0; i < chars.length(); i++) {
      char c = chars.charAt(i);
      dfs(digits, idx + 1, map, ret, cur + c);
    }
  }
}