import java.lang.StringBuilder;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * (you may want to display this pattern in a fixed font for better legibility)
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make this conversion given a number of rows:
 * <p>
 * string convert(string text, int nRows);
 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 **/

public class Solution {
  public String convert(String s, int numRows) {
    if (numRows == 1) return s;
    List<List<Character>> zz = new ArrayList<>(); // better to use List<StringBuilder> directly
    for (int i = 0; i < numRows; i++) {
      zz.add(new ArrayList<>());
    }
    boolean down = true;
    int row = 0;
    for (int i = 0; i < s.length(); i++) {
      zz.get(row).add(s.charAt(i));
      row = down ? row + 1 : row - 1;
      if (row == numRows) {
        down = false;
        row = numRows - 2; // bug: index out of range when numRows = 1 which is edge case
      }
      if (row == -1) {
        down = true;
        row = 1;
      }
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < zz.size(); i++) {
      for (int j = 0; j < zz.get(i).size(); j++) {
        sb.append(zz.get(i).get(j));
      }
    }
    return sb.toString();
  }
}