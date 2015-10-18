/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

You may assume no duplicate exists in the array.
* */

public class Solution {
    public int findMin(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        int min = nums[0];
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] >= nums[lo]) {
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
