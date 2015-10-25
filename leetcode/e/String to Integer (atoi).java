/**
 Implement atoi to convert a string to an integer.

 Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

 Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.
 **/
public class Solution {
  public int myAtoi(String str) {
    if (str.isEmpty()) return 0;
    str = str.trim();
    boolean sign = str.charAt(0) == '-' ? true : false;
    long ret = 0;
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) > '9' || str.charAt(i) < '0') {
        if (i == 0)
          continue;
        else return sign ? (int) -ret : (int) ret; // "  -0012a42" should return -12
      }
      ret = 10 * ret + (str.charAt(i) - '0');
    }
    return sign ? (int) -ret : (int) ret; // not handle overflow convert
  }
}