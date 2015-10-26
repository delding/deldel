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
    StringBuilder ret = new StringBuilder();
    while (n != 0) {
      char c = (char) ((n - 1) % 26 + 'A');
      ret.append(c);
      n = (n - 1) / 26;
    }
    return ret.reverse().toString();
  }
}
