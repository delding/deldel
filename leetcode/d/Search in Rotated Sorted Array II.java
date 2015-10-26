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
  public boolean search1(int[] nums, int target) {
    int lo = 0;
    int hi = nums.length - 1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      if (nums[mid] == target) return true;
      else if (nums[mid] == nums[lo] && nums[mid] == nums[hi]) {
        lo++;
        hi--;
      } else if (nums[lo] == nums[mid]) {
        if (nums[mid] > target) {
          if (nums[hi] > nums[mid]) return false;
          else lo = mid + 1;
        } else {
          lo = mid + 1;
        }
      } else if (nums[hi] == nums[mid]) {
        if (nums[mid] > target) {
          hi = mid - 1;
        } else {
          if (nums[lo] < nums[mid]) return false;
          else hi = mid - 1;
        }
      } else {
        if (nums[mid] > target) {
          if (nums[hi] > nums[mid]) hi = mid - 1;
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
    }
    return false;
  }

  // more concise solution
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
        if (target == nums[lo]) return true;
        else lo++;
      }
    }
    return false;
  }
}
