/**
 * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.
 * <p>
 * For example:
 * <p>
 * Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
 * <p>
 * Note:
 * The order of the result is not important. So in the above example, [5, 3] is also correct.
 * Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
 */

public class Solution {
  // divide nums into two group according to the bit that num1 and num2 are different, then perform xor for numbers in each group
  public int[] singleNumber(int[] nums) {
    int xor = nums[0];
    for (int i = 1; i < nums.length; i++) {
      xor = xor ^ nums[i];
    }
    int idxDiff = 0;
    while (((xor >> idxDiff) & 1) == 0) {
      idxDiff++;
    }
    int num1 = -1, num2 = -1;
    for (int num : nums) {
      if (((num >> idxDiff) & 1) == 0) {
        if (num1 == -1) {
          num1 = num;
        } else num1 ^= num;
      } else {
        if (num2 == -1) {
          num2 = num;
        } else num2 ^= num;
      }
    }
    return new int[]{num1, num2};
  }
}
