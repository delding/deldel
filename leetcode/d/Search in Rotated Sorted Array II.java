/**
 * Follow up for "Search in Rotated Sorted Array":
 * What if duplicates are allowed?
 * <p>
 * Would this affect the run-time complexity? How and why?
 * <p>
 * Write a function to determine if a given target is in the array.
 */

public class Solution {
  // worst case O(n) when all numbers are equal and not equal to target
  public boolean search(int[] nums, int target) {
    int lo = 0;
    int hi = nums.length - 1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      if (nums[mid] == target) return true;
      else if (nums[mid] > nums[lo]) { // left half in order
        if (target >= nums[lo] && target < nums[mid]) hi = mid - 1;
        else lo = mid + 1;
      } else if (nums[mid] < nums[hi]) { // right half in order
        if (target > nums[mid] && target <= nums[hi]) lo = mid + 1;
        else hi = mid - 1;
      } else {
        if (target == nums[lo++]) return true;
      }
    }
    return false;
  }

  // search ceiling in cyclic buffer
  int searchCeiling(int[] buffer, int target) {
    int l = 0, r = buffer.length - 1;
    int ceil = -1;
    while (l <= r) {
      int m = l + (r - l) / 2;
      if (buffer[m] >= id && (ceil == -1 || buffer[ceil] > buffer[m])) ceil = m;
      if (buffer[m] < buffer[r]) {
        if (id >= buffer[m] && id <= buffer[r]) {
          l = m + 1;
        }
        else { // need to consider m, but not r
          r = m - 1;
        }
      } else {
        if (id <= buffer[m] && id >= buffer[l]) { // need to consider m but not r
          r = m - 1;
        } else { // need to consider l, but not m
          if (buffer[l] > id && (ceil == -1 || buffer[ceil] > buffer[l])) ceil = l;
          l = m + 1;
        }
      }
    }
    return ceil;
  }
}
