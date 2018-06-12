/**
 * Reverse digits of an integer.
 * <p>
 * Example1: x = 123, return 321
 * Example2: x = -123, return -321
 */

public class Solution {
  public int reverse(int x) {
    long xx = Math.abs((long) x);
    long ret = 0;
    while (xx > 0) {
      ret = 10 * ret + xx % 10;
      xx /= 10;
    }
    if (ret > Integer.MAX_VALUE) return 0;
    return x > 0 ? (int) ret : (int) -ret;
  }
}