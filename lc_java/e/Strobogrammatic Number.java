/**
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * <p>
 * Write a function to determine if a number is strobogrammatic. The number is represented as a string.
 * <p>
 * For example, the numbers "69", "88", and "818" are all strobogrammatic.
 */

public class Solution {
  public boolean isStrobogrammatic(String num) {
    int lo = 0;
    int hi = num.length() - 1;
    while (lo < hi) {
      char l = num.charAt(lo);
      char r = num.charAt(hi);
      if (l != r) {
        if (l == '9' && r == '6' || l == '6' && r == '9') {
          lo++;
          hi--;
          continue;
        } else return false;
      } else {
        if (l == '0' || l == '1' || l == '8') {
          lo++;
          hi--;
          continue;
        } else return false;
      }
    }
    if (lo == hi) {
      char c = num.charAt(lo);
      return c == '0' || c == '1' || c == '8';
    }
    return true;
  }
}
