/**
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 * <p>
 * For example,
 * Given nums = [0, 1, 3] return 2.
 * <p>
 * Note:
 * Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
 */

public class Solution {
  public int missingNumber(int[] nums) {
    int n = nums.length;
    int sum = 0;
    for (int num : nums) sum += num;
    int nsum = 0;
    for (int i = 0; i <= n; i++) nsum += i;
    return nsum - sum;
  }
}
