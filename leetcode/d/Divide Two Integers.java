/**
 Divide two integers without using multiplication, division and mod operator.

 If it is overflow, return MAX_INT.
 **/

public class Solution {
  // use binary representation for decimal: 17 / 3 = 2^2 * 3 + 2^1 * 3
  public int divide(int dividend, int divisor) {
    if(dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE; // only case for overflow
    boolean sign = (dividend > 0) ^ (divisor > 0);
    long divid = Math.abs((long) dividend);
    long divis = Math.abs((long) divisor);
    int bit = 0;
    int ret = 0;
    while (divid >= divis) {
      while (divid >= divis << (bit + 1)) bit++;
      ret += 1 << bit;
      divid -= divis << bit;
      bit = 0; // bug: reset to 0
    }
    return sign ? -1 * ret : ret;
  }
}
