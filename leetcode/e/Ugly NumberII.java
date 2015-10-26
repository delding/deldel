/**
 * Write a program to find the n-th ugly number.
 * <p>
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 * <p>
 * Note that 1 is typically treated as an ugly number.
 */

public class Solution {
  public int nthUglyNumber(int n) {
    int idx2 = 0, idx3 = 0, idx5 = 0;
    int[] ugly = new int[n];
    ugly[0] = 1;
    for (int i = 1; i < n; i++) {
      int nextUgly = Math.min(Math.min(ugly[idx2] * 2, ugly[idx3] * 3), ugly[idx5] * 5);
      if (nextUgly == ugly[idx2] * 2) idx2++;
      if (nextUgly == ugly[idx3] * 3) idx3++;
      if (nextUgly == ugly[idx5] * 5) idx5++;
      ugly[i] = nextUgly;
    }
    return ugly[n - 1];
  }
}
