/**
 Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

 Note:
 Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
 The solution set must not contain duplicate triplets.
 For example, given array S = {-1 0 1 2 -1 -4},

 A solution set is:
 (-1, 0, 1)
 (-1, -1, 2)
 **/

public class Solution {
  public List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> ret = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
      if (i > 0 && nums[i] == nums[i - 1]) continue;
      int sum = 0 - nums[i];
      int lo = i + 1;
      int hi = nums.length - 1;
      while (lo < hi) {
        if (lo > i + 1 && nums[lo] == nums[lo - 1]) {
          lo++;
          continue;
        }
        if (hi < nums.length - 1 && nums[hi] == nums[hi + 1]) {
          hi--;
          continue;
        }
        if (nums[lo] + nums[hi] < sum) {
          lo++;
        } else if (nums[lo] + nums[hi] > sum) {
          hi--;
        } else {
          List<Integer> cur = new ArrayList<>();
          cur.add(nums[i]);
          cur.add(nums[lo]);
          cur.add(nums[hi]);
          ret.add(cur);
          lo++;
          hi--;
        }
      }
    }
    return ret;
  }
}