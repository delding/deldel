/**
 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
 * <p>
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * <p>
 * For example,
 * Given input array nums = [1,1,2],
 * <p>
 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't matter what you leave beyond the new length.
 */


public class Solution {

  // O(n)
  public int removeDuplicates(int[] nums) {
    if (nums.length == 0) return 0;
    int head = 0;
    int curr = 1;
    while (curr < nums.length) {
      if (nums[curr] != nums[head]) nums[++head] = nums[curr++];
      else curr++;
    }
    return head + 1;
  }


  // O(n^2) no good
  public int removeDuplicatesSlow(int[] nums) {
    int end = nums.length - 1;
    for (int i = 0; i < end; ) {
      if (nums[i] == nums[i + 1]) { // ERROR: don't increase i
        for (int j = i + 1; j < end; j++) swap(nums, j, j + 1);
        end--;
      } else i++;
    }
    return end + 1;
  }

  private void swap(int nums[], int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }
}
