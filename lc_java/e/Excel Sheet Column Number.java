/**
 * Given a column title as appear in an Excel sheet, return its corresponding column number.
 * <p>
 * For example:
 * <p>
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 */

public class Solution {
  public int titleToNumber(String s) {
    int num = 0;
    for (char c : s.toCharArray()) {
      num = num * 26 + (c - 'A' + 1);
    }
    return num;
  }
}
