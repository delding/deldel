/**
 * Given an array of integers, every element appears three times except for one. Find that single one.
 * <p>
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */

public class Solution {

  public int singleNumber(int[] nums) {
    int single = 0;
    for (int i = 31; i >= 0; i--) {
      int count = 0;
      for (int num : nums) {
        count += (num >> i) & 1;
      }
      single = 2 * single + (count % 3);
    }
    return single;
  }

  public int singleNumber1(int[] A) {
    int res = 0;
    int base = 1;
    for (int i = 0; i < 32; i++) {
      int count = 0;
      for (int num : A) {
        count += (num >> i) & 1;
      }
      res += count % 3 * base;
      base *= 2;
    }
    return res;
  }
}
