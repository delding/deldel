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
      if (nums[lo] == nums[mid] && nums[mid] == nums[hi]) {
        if (lo == hi) // ERROR: must take min, if lo = hi, which means the value will be lost after lo++, if lo < hi array still has mutiple number of this value, no need to check
          min = Math.min(nums[lo], min);
        lo++;
      } else if (nums[lo] <= nums[mid]) {
        min = Math.min(nums[lo], min);
        lo = mid + 1;
      } else {
        min = Math.min(nums[mid], min);
        hi = mid - 1;
      }
    }
    return min;
  }
}
