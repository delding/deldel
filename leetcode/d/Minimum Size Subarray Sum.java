/**
 * Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of
 * which the sum ≥ s. If there isn't one, return 0 instead.
 * <p>
 * For example, given the array [2,3,1,2,4,3] and s = 7,
 * the subarray [4,3] has the minimal length under the problem constraint.
 * <p>
 * More practice:
 * If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
 */

public class Solution {
  // two pointers O(n)
  public int minSubArrayLen1(int s, int[] nums) {
    int l = 0, r = 0;
    int len = 0;
    int sum = 0;
    for (; r < nums.length; ) {
      sum += nums[r++];
      while (sum >= s && l < r) { // sum up all the value from l to r-1
        if (len == 0) len = r - l;
        else len = Math.min(len, r - l);
        sum -= nums[l++];
      }
    }
    return len;
  }

  // cumulative sum and binary search O(nlg(n)), for each sum[i] find ceiling of sum[i] + s if there is any, compute len
  public int minSubArrayLen(int s, int[] nums) {
    if (nums.length == 0) return 0;
    int[] cumulative = new int[nums.length + 1]; // ERROR: must length + 1, and put 0 to first element, thus [1] - [0] is nums[0]
    cumulative[0] = 0;
    // cum[i] denotes sum from 0 up to i - 1
    for (int i = 1; i <= nums.length; i++) {
      cumulative[i] = cumulative[i - 1] + nums[i - 1];
    }
    int len = 0;
    for (int i = 0; i <= nums.length; i++) {
      int ceil = ceil(i + 1, cumulative, cumulative[i] + s);
      if (ceil != -1) {
        if (len == 0) len = ceil - i;
        else len = Math.min(len, ceil - i);
      }
    }
    return len;
  }

  private int ceil(int start, int[] nums, int target) {
    int lo = start;
    int hi = nums.length - 1;
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      if (nums[mid] == target) return mid;
      else if (nums[mid] < target) lo = mid + 1;
      else hi = mid - 1;
    }
    if (lo == nums.length) return -1; // all elements smaller than target
    return lo; // Error: to find ceil must return lo, last step when lo == hi, if curr < target lo++ which makes value > target, if curr > target hi-- also means lo makes value > target
  }
}
