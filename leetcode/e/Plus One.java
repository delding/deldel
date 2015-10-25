/**
 Given a non-negative number represented as an array of digits, plus one to the number.

 The digits are stored such that the most significant digit is at the head of the list.
 **/

public class Solution {
  public int[] plusOne(int[] digits) {
    int n = digits.length - 1;
    digits[n] += 1;
    int carry = 0;
    while (n >= 0) {
      int val = digits[n] + carry;
      carry = val / 10;
      digits[n] = val % 10;
      n--;
    }
    if (carry == 0) return digits;
    else {
      int[] res = new int[digits.length + 1];
      res[0] = carry;
      for (int i = 1; i < res.length; i++) {
        res[i] = digits[i - 1];
      }
      return res;
    }
  }
}