import java.lang.String;
import java.lang.StringBuilder;

/**
 * Given two numbers represented as strings, return multiplication of the numbers as a string.
 * <p>
 * Note: The numbers can be arbitrarily large and are non-negative.
 **/

public class Solution {
  public String multiply(String num1, String num2) {
    if (num1.equals("0") || num2.equals("0")) return "0"; // bug: edge case for 0
    int len1 = num1.length();
    int len2 = num2.length();
    int[] ret = new int[len1 + len2]; // if no carry on most significant digit, length of multiplication is len1 + len2 - 1
    int len = len1 + len2;
    for (int i = 0; i < len; i++) {
      for (int j = 0; j <= Math.min(i, len1 - 1); j++) {
        int k = i - j;
        if (k > len2 - 1) continue;
        ret[len - 1 - i] += (num1.charAt(len1 - 1 - j) - '0') * (num2.charAt(len2 - 1 - k) - '0');
      }
    }
    int carry = 0;
    for (int i = len - 1; i >= 0; i--) {
      int val = carry + ret[i];
      ret[i] = val % 10;
      carry = val / 10;
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < len; i++) {
      if (i == 0 && ret[i] == 0) continue;
      ;
      sb.append(ret[i]);
    }
    return sb.toString();
  }
}

// an easier solution, reverse string first makes index easier
public class Solution {
  public String multiply(String num1, String num2) {
    num1 = new StringBuilder(num1).reverse().toString();
    num2 = new StringBuilder(num2).reverse().toString();
    int[] res = new int[num1.length() + num2.length()];
    for (int i = 0; i < num1.length(); i++) {
      for (int j = 0; j < num2.length(); j++) {
        res[i + j] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
      }
    }
    int carry = 0;
    int num = 0;
    for (int k = 0; k < res.length; k++) {
      num = (res[k] + carry) % 10;
      carry = (res[k] + carry) / 10;
      res[k] = num;
    }
    StringBuilder solu = new StringBuilder();
    for (int k = res.length - 1; k >= 0; k--) {
      if (res[k] == 0 && solu.length() == 0) continue; // skip leading zeros
      solu.append(res[k]);
    }
    if (solu.length() == 0) solu.append("0"); // edge case when one of inputs is 0
    return solu.toString();
  }
}