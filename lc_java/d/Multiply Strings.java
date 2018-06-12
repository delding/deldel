/**
 * Given two numbers represented as strings, return multiplication of the numbers as a string.
 * <p>
 * Note: The numbers can be arbitrarily large and are non-negative.
 **/
public class Solution {
  public String multiply(String num1, String num2) {
    int[] mult = new int[num1.length() + num2.length()];
    for (int i = 0; i < num1.length(); i++) {
      for (int j = 0; j < num2.length(); j++) {
        char n1 = num1.charAt(num1.length() - 1 - i);
        char n2 = num2.charAt(num2.length() - 1 - j);
        mult[mult.length - 1 - i - j] += (n1 - '0') * (n2 - '0');
      }
    }
    for (int i = mult.length - 1, carry = 0; i >= 0; i--) {
      int val = (mult[i] + carry) % 10;
      carry = (mult[i] + carry) / 10;
      mult[i] = val;
    }
    String res = "";
    for (int v : mult) {
      if (v == 0 && res.isEmpty()) continue;
      res += v;
    }
    return res.isEmpty() ? "0" : res; // corner case for "0"
  }
}