/**
 * Given an array of integers, find out whether there are two distinct indices i and j in the array such that the difference between nums[i] and nums[j] is
 * at most t and the difference between i and j is at most k.
 */

public class Solution {
  public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
    if (k == 0) return false;

    TreeSet<Integer> tree = new TreeSet(); // tree for search nearest value
    Queue<Integer> q = new LinkedList(); // queue for remove oldest value
    for (int i = 0; i < nums.length; i++) {
      if (i > k) { // sliding window's length is k+1
        int val = q.poll();
        tree.remove(val);
      }
      if (!tree.isEmpty()) { // ERROR: when handling first value, tree is empty
        if (tree.ceiling(nums[i]) != null) { // Error: ceiling and floor can return null
          int ceil = tree.ceiling(nums[i]);
          if (Math.abs((long) (ceil - nums[i])) <= t) return true; // 2147483647 - -1 can overflow
        }
        if (tree.floor(nums[i]) != null) { // Error: ceiling and floor can return null
          int floor = tree.floor(nums[i]);
          if (Math.abs((long) (floor - nums[i])) <= t) return true;
        }
      }
      tree.add(nums[i]);
      q.add(nums[i]);
    }
    return false;
  }
}
