/**
 * Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the Hamming weight).
 * <p>
 * For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function should return 3.
 */

public class Solution {
  // you need to treat n as an unsigned value
  public int hammingWeight(int n) {
    int count = 0;
    for (int i = 0; i < 32; i++) {
      if ((n & 1) == 1) count++;
      n = n >>> 1;  // unsigned right shift
    }
    return count;
  }

  public int hammingWeight1(int n) {
    int total_ones = 0;
    while (n != 0) {
      n = n & (n - 1); // remove the lowerest 1 each time, 101000 & 100111 = 100000
      total_ones++;
    }
    return total_ones;
  }

}
