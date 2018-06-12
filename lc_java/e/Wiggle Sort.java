/**
 * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
 * <p>
 * For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
 */

public class Solution {
  public void wiggleSort(int[] nums) {
    for (int i = 0; i < nums.length - 1; i++) {
      if (i % 2 == 0) {
        if (nums[i] > nums[i + 1]) swap(i, i + 1, nums);
      } else {
        if (nums[i] < nums[i + 1]) swap(i, i + 1, nums);
      }
    }
  }

  private void swap(int i, int j, int[] nums) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }
}
