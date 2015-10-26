/**
 * Determine whether an integer is a palindrome. Do this without extra space.
 **/
public class Solution {
  public boolean isPalindrome(int x) {
    if (x < 0) return false;
    long divisor = 1;
    int digit = 1;
    // bug: divisor * 10 can be 10 times more than x and so can overflow, upon overflow condition in while loop can change unexpected
    while (x / (divisor * 10) > 0) {
      divisor *= 10;
      digit++;
    }
    digit /= 2;
    int lowdivisor = 1;
    for (int i = 1; i <= digit; i++) {
      int hi = x / (int) divisor;
      int lo = (x % (lowdivisor * 10)) / lowdivisor;
      if (hi != lo) return false;
      x -= hi * (int) divisor;
      divisor /= 10;
      lowdivisor *= 10;
    }
    return true;
  }

  // solution 2: reverse number
  public class Solution {
    public boolean isPalindrome(int x) {
      if (x < 0) {
        return false;
      }
      // use long in case reverse number is overflow
      long reverse = 0;
      int original = x; // NOTE: don't forget to keep a copy of x, x will be changed
      while (x > 0) {
        reverse = reverse * 10 + x % 10;
        x /= 10;
      }
      return (reverse == original);
    }
  }
}