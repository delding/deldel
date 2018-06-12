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
  // prefix sum + binary search to find ceiling
  public int minSubArrayLen(int s, int[] nums) {
    int[] preSum = new int[nums.length + 1];
    for (int i = 1; i < preSum.length; i++) {
      preSum[i] = preSum[i - 1] + nums[i - 1];
    }
    int len = Integer.MAX_VALUE;
    for (int i = 0; i < preSum.length; i++) {
      int ceiling = preSum[i] + s;
      int l = i, r = preSum.length - 1;
      while (l <= r) {
        int m = l + (r - l) / 2;
        if (preSum[m] >= ceiling) r = m - 1;
        else l = m + 1;
      }
      if (l != preSum.length) { // ceiling is found
        len = Math.min(len, l - i);
      }
    }
    return len == Integer.MAX_VALUE ? 0 : len;
  }

  // two pointers
  public int minSubArrayLen1(int s, int[] nums) {
    int len = Integer.MAX_VALUE, sum = 0;
    for (int l = 0, r = 0; r < nums.length; r++) {
      sum += nums[r];
      while (sum >= s) {
        len = Math.min(len, r - l + 1);
        sum -= nums[l++];
      }
    }
    return len == Integer.MAX_VALUE ? 0 : len;
  }
}
