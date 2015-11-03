/**
 Given an unsorted array of integers, find the length of longest increasing subsequence.

 For example,
 Given [10, 9, 2, 5, 3, 7, 101, 18],
 The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.

 Your algorithm should run in O(n2) complexity.

 Follow up: Could you improve it to O(n log n) time complexity?
 **/

public class Solution {
  // O(n^2)
  public int lengthOfLIS(int[] nums) {
    int maxLen = 0; // intialize to 0 for empty array input
    int[] lens = new int[nums.length];
    for (int i = 0; i < nums.length; i++) {
      int len = 1;
      for (int j = 0; j < i; j++) {
        if (nums[j] < nums[i]) {
          len = Math.max(len, lens[j] + 1);
        }
      }
      lens[i] = len;
      maxLen = Math.max(maxLen, len);
    }
    return maxLen;
  }

  // Todo: O(nlogn)
  // M[j] — stores the index k of the smallest value X[k] such that there is an increasing subsequence of length j ending at X[k] on the range k ≤ i.
  public int lengthOfLIS(int[] nums) {
    int maxLen = 0; // intialize to 0 for empty array input
    int[] M = new int[nums.length + 1];
    for (int i = 0; i < nums.length; i++) {
      int len = 1;
      int lo = 1;
      int hi = maxLen;
      while (lo <= hi) {
        int mid = lo + (hi - lo) / 2;

      }
      M[len] = nums[j];
      maxLen = Math.max(maxLen, len);
    }
    return maxLen;
  }
}