/**
 Implement pow(x, n).
 **/

public class Solution {
  // not pass edge case
  public double myPow(double x, int n) {
    if (n == 0) return 1;
    if (n < 0) return 1 / myPow(x, -1 * n); // bug: Must consider minus value and MIN_VALUE can overflow
    if (n % 2 == 0) return myPow(x, n / 2);
    else return x * myPow(x, (n - 1) / 2);
  }

  // correct
  public double myPow(double x, int n) {
    if (n == 0) return 1;
    double val = myPow(x, n / 2);
    if (n % 2 == 0)  return val * val;
    else {
      if (n > 0)
        return x * val * val;
      else return val * val / x;
    }
  }
}