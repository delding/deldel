/**
 * Validate if a given string is numeric.
 * <p>
 * Some examples:
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.
 **/

// todo
public class Solution {
  public boolean isNumber(String s) {
    if (s == null || s.length() == 0)
      return false;
    boolean sign = false, dot = false, exp = false, num = false;
    s = s.trim();
    if (s.isEmpty()) return false;
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      if (ch >= '0' && ch <= '9') {
        num = true;
        sign = true;        // no sign after num
      } else if (ch == '+' || ch == '-') {
        if (sign) return false;
        sign = true;
      } else if (ch == '.') {
        if (dot) return false;
        dot = true;
        sign = true;        // no sign after num
      } else if (ch == 'e' || ch == 'E') {
        if (exp || !num) return false;
        exp = true;
        sign = false;       // allow sign after e
        num = false;
        dot = true;         // no dot after e
      } else return false;
    }
    return num; // should have numeric characters
  }
}