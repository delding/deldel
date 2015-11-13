/**
 Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

 Example:
 Given nums = [-2, 0, 3, -5, 2, -1]

 sumRange(0, 2) -> 1
 sumRange(2, 5) -> -1
 sumRange(0, 5) -> -3
 Note:
 You may assume that the array does not change.
 There are many calls to sumRange function.
 **/

public class NumArray {
  int[] prefixSum; // TLE if use 2-D sum[i][j], then O(n^2) instead of O(n) for pre-processing

  public NumArray(int[] nums) {
    prefixSum = new int[nums.length + 1];
    for (int len = 1; len <= nums.length; len++) {
      prefixSum[len] += prefixSum[len - 1] + nums[len - 1];
    }
  }

  public int sumRange(int i, int j) {
    return prefixSum[j + 1] - prefixSum[i];
  }
}


// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.sumRange(1, 2);