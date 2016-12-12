/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * <p>
 * For example,
 * Given [3,2,1,5,6,4] and k = 2, return 5.
 * <p>
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 */

public class Solution {
  // quickselect
  public int findKthLargest(int[] nums, int k) {
    return findKth(nums, k, 0, nums.length - 1);
  }

  int findKth(int nums[], int k, int l, int r) {
    int pivot = partition(nums, l, r);
    if (r - pivot + 1 == k) return nums[pivot];
    else if (r - pivot + 1 > k) return findKth(nums, k, pivot + 1, r);
    else return findKth(nums, k - (r - pivot + 1), l, pivot - 1);
  }

  int partition(int[] nums, int l, int r) {
    int val = nums[r];
    for (int i = l; i <= r;) {
      if (nums[i] < val) swap(nums, i++, l++);
      else if (nums[i] > val) swap(nums, i, r--);
      else i++;
    }
    return l;
  }

  void swap(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }

  // minheap
  public int findKthLargest1(int[] nums, int k) {
    PriorityQueue<Integer> q = new PriorityQueue<>(k);
    for (int num : nums) {
      q.add(num);
      if (q.size() > k) q.poll();
    }
    return q.poll();
  }
}