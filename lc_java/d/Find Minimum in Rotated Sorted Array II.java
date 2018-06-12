/**
 * Follow up for "Find Minimum in Rotated Sorted Array":
 * What if duplicates are allowed?
 * <p>
 * Would this affect the run-time complexity? How and why?
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * <p>
 * Find the minimum element.
 * <p>
 * The array may contain duplicates.
 */

public class Solution {
  // worst case O(n), when all numbers are equal
  public int findMin(int[] nums) {
    int min = nums[0];
    int lo = 0;
    int hi = nums.length - 1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      if (nums[mid] < nums[hi]) {
        min = Math.min(min, nums[mid]);
        hi = mid - 1;
      } else if (nums[mid] > nums[lo]) {
        min = Math.min(min, nums[lo]);
        lo = mid + 1;
      } else {
        min = Math.min(min, nums[lo++]);
      }
    }
    return min;
  }
}
