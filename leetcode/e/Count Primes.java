/**
 * Count the number of prime numbers less than a non-negative number, n.
 **/

public class Solution {
  public int countPrimes(int n) {
    boolean[] prime = new boolean[n];
    Arrays.fill(prime, true);
    int count = 0;
    for (int i = 2; i < n; i++) {
      if (prime[i]) {
        count++;
        int mutiple = i + i;
        while (mutiple < n) {
          prime[mutiple] = false;
          mutiple += i;
        }
      }
    }
    return count;
  }
}
