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
    if (b.length() > a.length()) return addBinary(b, a);
    String sum = "";
    int i = a.length() - 1;
    int j = b.length() - 1;
    boolean carry = false;
    while (j >= 0) {
      char c1 = a.charAt(i);
      char c2 = b.charAt(j);
      if (c1 == '0' && c2 == '0') {
        if (carry) sum = "1" + sum;
        else sum = "0" + sum;
        carry = false;
      } else if (c1 == '1' && c2 == '1') {
        if (carry) sum = "1" + sum;
        else sum = "0" + sum;
        carry = true;
      } else {
        if (carry) {
          sum = "0" + sum;
        } else {
          sum = "1" + sum;
        }
      }
      j--;
      i--;
    }
    while (i >= 0) {
      char c = a.charAt(i);
      if (carry) {
        if (c == '0') {
          sum = "1" + sum;
          carry = false;
        } else sum = "0" + sum;
      } else {
        sum = c + sum;
      }
      i--;
    }
    if (carry) sum = "1" + sum; // bug: must check carry at last
    return sum;
  }
}

// another solution
public class Solution {
  public String addBinary(String a, String b) {
    char[] A = a.toCharArray();
    char[] B = b.toCharArray();
    int[] C = new int[Math.max(A.length, B.length) + 1];
    int i = A.length - 1;
    int j = B.length - 1;
    int k = C.length - 1;
    while (i >= 0 && j >= 0) {
      C[k--] = (A[i--] - '0') + (B[j--] - '0');

    }
    while (i >= 0) {
      C[k--] = A[i--] - '0';
    }
    while (j >= 0) {
      C[k--] = B[j--] - '0';
    }
    int carry = 0;
    for (k = C.length - 1; k >= 0; k--) {
      int val = C[k] + carry;
      carry = val / 2;
      C[k] = val % 2;
    }
    StringBuilder res = new StringBuilder();
    boolean headZero = true;
    for (k = 0; k < C.length; k++) {
      while (headZero && k < C.length && C[k] == 0) k++;
      headZero = false;
      if (k == C.length) { // all zeros
        res.append(0);
      } else {
        res.append(C[k]);
      }
    }
    return res.toString();
  }
}