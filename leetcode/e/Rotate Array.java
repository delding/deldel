/**
 * Rotate an array of n elements to the right by k steps.
 * <p>
 * For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
 **/

public class Solution {
  // reverse all, then reverse first k and the rest n - k
  public void rotate(int[] nums, int k) {
    k = k % nums.length; // ERROR: k can larger than total length
    reverse(nums, 0, nums.length - 1);
    reverse(nums, 0, k - 1);
    reverse(nums, k, nums.length - 1);
  }

  private void reverse(int[] nums, int lo, int hi) {
    while (lo < hi) {
      int tmp = nums[lo];
      nums[lo++] = nums[hi];
      nums[hi--] = tmp;
    }
  }
}
