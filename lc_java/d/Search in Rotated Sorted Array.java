/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * <p>
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * <p>
 * You may assume no duplicate exists in the array.
 */

public class Solution {
  public int search(int[] nums, int target) {
    int lo = 0;
    int hi = nums.length - 1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      if (nums[mid] == target) return mid;
      else if (nums[mid] > target) {
        if (nums[mid] < nums[hi]) hi = mid - 1;
        else {
          if (target >= nums[lo]) hi = mid - 1;
          else lo = mid + 1;
        }
      } else {
        if (nums[mid] > nums[lo]) lo = mid + 1;
        else {
          if (nums[hi] >= target) lo = mid + 1;
          else hi = mid - 1;
        }
      }
    }
    return -1;
  }
}
