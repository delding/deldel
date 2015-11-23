/**
 Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

 The update(i, val) function modifies nums by updating the element at index i to val.
 Example:
 Given nums = [1, 3, 5]

 sumRange(0, 2) -> 9
 update(1, 2)
 sumRange(0, 2) -> 8
 Note:
 The array is only modifiable by the update function.
 You may assume the number of calls to update and sumRange function is distributed evenly.
 **/

public class NumArray {
  // segment tree, parent i, left child 2 * i + 1, right child 2 * i + 2; left child j, right child j + 1, parent (j - 1) / 2

  // All levels of the constructed segment tree will be completely filled except the last level. Also, the tree will be a Full Binary Tree
  // because we always divide segments in two halves at every level. Since the constructed tree is always full binary tree with n leaves,
  // there will be n-1 internal nodes. So total number of nodes will be 2 * n – 1.
  // Height of the segment tree will be log_2(n). Since the tree is represented using array and relation between parent and child
  // indexes must be maintained, size of memory allocated for segment tree will be 2 * 2 ^ h - 1

  int[] stSum;
  int len; // len = 0 will cause invalid input l > r, every method needs to consider this edge case

  public NumArray(int[] nums) {
    len = nums.length;
    if (len == 0) return;
    // height of segment tree
    int h = (int) (Math.ceil(Math.log(len) / Math.log(2)));
    // maximum size of segment tree
    int size = 2 * (int) Math.pow(2, h) - 1;
    stSum = new int[size];
    int[] preSum = new int[len];
    for (int i = 0; i < len; i++) {
      if (i == 0) preSum[i] = nums[i];
      else preSum[i] = preSum[i - 1] + nums[i];
    }
    init(0, nums, 0, len - 1, preSum);
  }

  // idx is the position of current node in segment tree, (l, r) inclusive is the range of current node
  private void init(int idx, int[] nums, int l, int r, int[] preSum) {
    if (l > r) return;
    else if (l == r) stSum[idx] = nums[l];
    else {
      int sum = l == 0 ? preSum[r] : preSum[r] - preSum[l - 1];
      stSum[idx] = sum;
      int mid = l + (r - l) / 2;
      init(idx * 2 + 1, nums, l, mid, preSum);
      init(idx * 2 + 2, nums, mid + 1, r, preSum);
    }
  }

  public void update(int i, int val) {
    if (len == 0) return;
    update(i, val, 0, 0, len - 1);
  }

  private int update(int i, int val, int idx, int l, int r) {
    int diff;
    if (l == r && l == i) {
      diff = val - stSum[idx];
      stSum[idx] += diff;
    } else if (i < l || i > r) diff = 0;
    else {
      int mid = l + (r - l) / 2;
      diff = update(i, val, idx * 2 + 1, l , mid) + update(i, val, idx * 2 + 2, mid + 1, r);
      stSum[idx] += diff;
    }
    return diff;
  }

  public int sumRange(int i, int j) {
    if (len == 0) return 0;
    return sumRange(0, 0, len - 1, i, j);
  }

  private int sumRange(int idx, int l, int r, int i, int j) {
    if (l >= i && r <= j) return stSum[idx];
    else if (j < l || i > r) return 0;
    else {
      int mid = l + (r - l) / 2;
      return sumRange(idx * 2 + 1, l, mid, i, j) + sumRange(idx * 2 + 2, mid + 1, r, i, j);
    }
  }
}

// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.update(1, 10);
// numArray.sumRange(1, 2);

