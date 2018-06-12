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


  public boolean isNumber(String s) {

  /*
If we see [0-9] we reset the number flags.
We can only see . if we didn't see e or ..
We can only see e if we didn't see e but we did see a number. We reset numberAfterE flag.
We can only see + and - in the beginning and after an e
any other character break the validation.
At the and it is only valid if there was at least 1 number and if we did see an e then a number after it as well.
 */
    s = s.trim();

    boolean pointSeen = false;
    boolean eSeen = false;
    boolean numberSeen = false;
    boolean numberAfterE = true;
    for (int i = 0; i < s.length(); i++) {
      if ('0' <= s.charAt(i) && s.charAt(i) <= '9') {
        numberSeen = true;
        numberAfterE = true;
      } else if (s.charAt(i) == '.') {
        if (eSeen || pointSeen) {
          return false;
        }
        pointSeen = true;
      } else if (s.charAt(i) == 'e') {
        if (eSeen || !numberSeen) {
          return false;
        }
        numberAfterE = false;
        eSeen = true;
      } else if (s.charAt(i) == '-' || s.charAt(i) == '+') {
        if (i != 0 && s.charAt(i - 1) != 'e') {
          return false;
        }
      } else {
        return false;
      }
    }

    return numberSeen && numberAfterE;
  }



  public boolean isNumber(String s) {
    /**
     * isNumber(s)==true if and only if s=s1 or s1+'e'+s2, where s1, s2
     * are valid strings of a number without the char 'e', and s2 is an
     * integer.
     *
     * 'e' : valid_count=0~1; [boolean hasE]
     *
     * Valid chars in a string of a number without 'e':
     *
     * ' ' : valid_count=0~n; must appear at two ends
     *
     * '+/-' : valid_count=0~1; must be the first non-space valid char;
     * [boolean hasFirst]
     *
     * '.' : valid_count=0~1; cannot appear after 'e'; [boolean hasDot]
     *
     * '0~9' : valid_count=1~n; [boolean hasDigit]
     */

    s = s.trim();
    int n = s.length();
    if (n == 0)
      return false;

    boolean hasE, hasFirst, hasDot, hasDigit;
    hasE = hasFirst = hasDot = hasDigit = false;

    char c;
    for (int i = 0; i < n; i++) {
      c = s.charAt(i);

      if (c >= '0' && c <= '9') {
        hasFirst = hasDigit = true;
        continue;
      }

      switch (c) {
			/*
			 * case ' ': continue;
			 */ // extend to accept any space everywhere
        case 'e':
          // already has 'e' or no digit before 'e'
          if (hasE || !hasDigit)
            return false;
          hasE = true;

          // reset for the exponential number
          hasFirst = hasDigit = false;
          hasDot = true; // the exponent must be an integer, hence
          // regard as if a dot exists already. Set
          // hasDot = false extending to accept any
          // (decimal) number as an exponent.
          continue;
        case '+':
        case '-':
          if (hasFirst)
            return false;
          hasFirst = true;
          continue;
        case '.':
          if (hasDot)
            return false;
          hasFirst = hasDot = true;
          continue;
        default:
          return false;
      }
    }

    return hasDigit;
  }
}



