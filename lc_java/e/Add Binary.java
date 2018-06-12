/**
 * Given two binary strings, return their sum (also a binary string).
 * <p>
 * For example,
 * a = "11"
 * b = "1"
 * Return "100".
 **/

public class Solution {
  public String addBinary(String a, String b) {
    if (a.length() < b.length()) return addBinary(b, a);
    int carry = 0;
    String sum = "";
    int i = 0;
    for (; i < b.length(); i++) {
      int val = carry + (a.charAt(a.length() - 1 - i) - '0') + (b.charAt(b.length() - 1 - i) - '0');
      sum = (val % 2) + sum;
      carry = val / 2;
    }
    for (; i < a.length(); i++) {
      int val = carry + (a.charAt(a.length() - 1 - i) - '0');
      sum = (val % 2) + sum;
      carry = val / 2;
    }
    if (carry == 1) sum = carry + sum;
    return sum;
  }
}