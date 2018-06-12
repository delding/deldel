/**
 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.
 * <p>
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * <p>
 * Note:
 * You are not suppose to use the library's sort function for this problem.
 **/

public class Solution {
    // three way partition
    public void sortColors(int[] nums) {
      for (int l = 0, i = 0, r = nums.length - 1; i <= r;) {
        if (nums[i] == 0) swap(l++, i++, nums);
        else if (nums[i] == 1) i++;
        else swap(r--, i, nums);
      }
    }

    void swap(int i, int j, int[] nums) {
      int tmp = nums[i];
      nums[i] = nums[j];
      nums[j] = tmp;
    }
}
