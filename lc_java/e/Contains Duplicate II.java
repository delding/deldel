/**
 * Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the difference between i and j is at most k.
 */

public class Solution {
  public boolean containsNearbyDuplicate(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap();
    for (int i = 0; i < nums.length; i++) {
      Integer idx = map.get(nums[i]);
      if (idx == null) map.put(nums[i], i);
      else {
        if (i - idx <= k) return true;
        map.put(nums[i], i); // update index
      }
    }
    return false;
  }
}
