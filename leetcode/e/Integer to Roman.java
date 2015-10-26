/**
 * Given an integer, convert it to a roman numeral.
 * <p>
 * Input is guaranteed to be within the range from 1 to 3999.
 **/

public class Solution {
  public String intToRoman(int num) {
    String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    int[] integer = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String ret = "";
    for (int i = 0; i < integer.length; i++) {
      while (num - integer[i] >= 0) {
        num -= integer[i];
        ret += roman[i];
      }
    }
    return ret;
  }
}