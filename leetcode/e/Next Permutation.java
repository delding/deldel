/**
 Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

 If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

 The replacement must be in-place, do not allocate extra memory.

 Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 1,2,3 → 1,3,2
 3,2,1 → 1,2,3
 1,1,5 → 1,5,1
 **/

public class Solution {
  public void nextPermutation(int[] nums) {
    int e = nums.length - 1;
    while (e > 0) {
      if (nums[e] > nums[e - 1]) {
        break;
      }
      e--;
    }
    if (e == 0) Arrays.sort(nums);
    else {
      int tmp = nums[e];
      int idx = e;
      for (int i = e; i < nums.length; i++) {
        if (nums[i] > nums[e - 1] && nums[i] <= tmp) { // bug: must <= tmp not <, find the last position when there are multiple same values, thus following swap will make values starting from e in ascending order
          tmp = nums[i];
          idx = i;
        }
      }
      nums[idx] = nums[e - 1];
      nums[e - 1] = tmp;
      int lo = e;
      int hi = nums.length - 1;
      while (lo < hi) { // can simply Arrays.sort(nums, e, nums.length)
        int t = nums[hi];
        nums[hi--] = nums[lo];
        nums[lo++] = t;
      }
    }
    return;
  }
}