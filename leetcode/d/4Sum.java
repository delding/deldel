import java.lang.Integer;

/**
 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target?
 * Find all unique quadruplets in the array which gives the sum of target.
 * <p>
 * Note:
 * Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
 * The solution set must not contain duplicate quadruplets.
 * For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
 * <p>
 * A solution set is:
 * (-1,  0, 0, 1)
 * (-2, -1, 1, 2)
 * (-2,  0, 0, 2)
 **/

public class Solution {
  public List<List<Integer>> fourSum(int[] nums, int target) {
    List<List<Integer>> ret = new ArrayList<>();
    Arrays.sort(nums);
    for (int i = 0; i < nums.length; i++) {
      if (i > 0 && nums[i] == nums[i - 1])
        continue; // for same index must avoid duplicate, at each loop level, duplicate values must only handle once
      for (int j = i + 1; j < nums.length; j++) {
        if (j > i + 1 && nums[j] == nums[j - 1]) continue; // for same index must avoid duplicate
        int sum = target - nums[i] - nums[j];
        int lo = j + 1;
        int hi = nums.length - 1;
        while (lo < hi) {
          if (lo > j + 1 && nums[lo] == nums[lo - 1]) { // for same index must avoid duplicate
            lo++;
            continue;
          }
          if (hi < nums.length - 1 && nums[hi] == nums[hi + 1]) { // for same index must avoid duplicate
            hi--;
            continue;
          }
          if (nums[lo] + nums[hi] < sum) lo++;
          else if (nums[lo] + nums[hi] > sum) hi--;
          else {
            List<Integer> cur = new ArrayList<Integer>();
            cur.add(nums[i]);
            cur.add(nums[j]);
            cur.add(nums[lo]);
            cur.add(nums[hi]);
            ret.add(cur);
            lo++; // bug: must update index
            hi--;
          }
        }
      }
    }
    return ret;
  }
}