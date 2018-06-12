/**
 * Given a roman numeral, convert it to an integer.
 * <p>
 * Input is guaranteed to be within the range from 1 to 3999.
 **/

// I, II, III, IV, V, VI, VII, VIII, IX, X, L 50, C	100, D 500, M 1,000
public class Solution {

  public int romanToInt(String s) {
    Map<Character, Integer> roman = new HashMap<>();
    roman.put('I', 1);
    roman.put('V', 5);
    roman.put('X', 10);
    roman.put('L', 50);
    roman.put('C', 100);
    roman.put('D', 500);
    roman.put('M', 1000);
    int ret = 0;
    for (int i = 0; i < s.length(); i++) {
      int curr = roman.get(s.charAt(i));
      if (i + 1 < s.length() && roman.get(s.charAt(i + 1)) > curr) ret -= curr; // if next is strictly bigger, minus curr
      else ret += curr; // if curr bigger than or equal to next, plus curr
    }
    return ret;
  }

  public int romanToInt2(String s) {
    Map<String, Integer> roman = new HashMap<>();
    roman.put("I", 1);
    roman.put("IV", 4);
    roman.put("V", 5);
    roman.put("IX", 9);
    roman.put("X", 10);
    roman.put("XL", 40);
    roman.put("L", 50);
    roman.put("XC", 90);
    roman.put("C", 100);
    roman.put("CD", 400);
    roman.put("D", 500);
    roman.put("CM", 900);
    roman.put("M", 1000);
    int ret = 0;
    for (int i = 0; i < s.length(); i++) {
      if (i == s.length() - 1) {
        ret += roman.get(s.substring(i));
      } else {
        if (roman.containsKey(s.substring(i, i + 2))) {
          ret += roman.get(s.substring(i, i + 2));
          i++;
        } else ret += roman.get(s.substring(i, i + 1));
      }
    }
    return ret;
  }
}