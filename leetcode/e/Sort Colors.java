/**
 Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

 Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

 Note:
 You are not suppose to use the library's sort function for this problem.
 **/

public class Solution {
  // three way partition
  public void sortColors(int[] nums) {
    int l = 0;
    int m = 0;
    int r = nums.length - 1;
    while (m <= r) {
      if (nums[m] == 1) m++;
      else if (nums[m] == 0) swap(nums, l++, m++);
      else swap(nums, m, r--);
    }
  }

  private void swap(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }
}