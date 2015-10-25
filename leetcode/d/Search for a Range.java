/**
 Given a sorted array of integers, find the starting and ending position of a given target value.

 Your algorithm's runtime complexity must be in the order of O(log n).

 If the target is not found in the array, return [-1, -1].

 For example,
 Given [5, 7, 7, 8, 8, 10] and target value 8,
 return [3, 4].
 **/

public class Solution {
  public int[] searchRange(int[] nums, int target) {
    int[] ret = new int[2];
    int lo = 0;
    int hi = nums.length - 1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      if (target <= nums[mid]) hi = mid - 1; // this will make lo become the lowest index of target
      else lo = mid + 1;
    }
    if (lo == nums.length || nums[lo] != target) return new int[]{-1, -1}; // not found, hi can also be -1 if target is too small
    ret[0] = lo;
    lo = 0;
    hi = nums.length - 1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      if (target >= nums[mid]) lo = mid + 1; // this will make hi become the highest index of target
      else hi = mid - 1;
    }
    ret[1] = hi;
    return ret;
  }
}