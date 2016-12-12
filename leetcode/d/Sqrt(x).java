/**
 * Implement int sqrt(int x).
 * <p>
 * Compute and return the square root of x.
 **/

public class Solution {
  public int mySqrt(int x) {
    if (x == 0) return 0;
    int l = 1, r = x;
    while (l <= r) {
      int m = l + (r - l) / 2;
      if (m <= x / m) l = m + 1;
      else r = m - 1; // r return floor
    }
    return r;
  }

  // square root with precision
  double sqrt(double num) {
    double left = 0;
    double right = num;
    while(right - left > 10e-2) {
      double mid = (left + right) / 2.0;
      if (mid*mid > num) right = mid;
      else left = mid;
    }
    return left;
  }
}