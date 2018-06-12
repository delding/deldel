/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * <p>
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * <p>
 * The replacement must be in-place, do not allocate extra memory.
 * <p>
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 **/

public class Solution {
  public void nextPermutation(int[] nums) {
    int j = -1;
    for (int i = nums.length - 1; i > 0; i--) {
      if (nums[i] > nums[i - 1]) { // nums[i] > nums[i - 1] to find previousPermutation
        j = i - 1;
        break;
      }
    }
    if (j == -1) {
      reverse(nums, 0, nums.length - 1);
    } else {
      for (int i = nums.length - 1; i > j; i--) {
        if (nums[i] > nums[j]) {
          int tmp = nums[i];
          nums[i] = nums[j];
          nums[j] = tmp;
          reverse(nums, j + 1, nums.length - 1);
          break;
        }
      }
    }
  }

  void reverse(int[] nums, int l, int r) {
    while (l < r) {
      int tmp = nums[l];
      nums[l++] = nums[r];
      nums[r--] = tmp;
    }
  }


  public void nextPermutation1(int[] nums) {
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