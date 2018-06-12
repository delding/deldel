/**
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 * <p>
 * For example:
 * <p>
 * 1 -> A
 * 2 -> B
 * 3 -> C
 * ...
 * 26 -> Z
 * 27 -> AA
 * 28 -> AB
 **/

public class Solution {
  public String convertToTitle(int n) {
    String t = "";
    // convert to 26-digit
    while (n != 0) {
      int q = --n % 26;
      n = n / 26;
      t = (char) ('A' + q) + t;
    }
    return t;
  }
}