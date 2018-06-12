/**
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 * <p>
 * Note:
 * Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
 * The solution set must not contain duplicate triplets.
 * For example, given array S = {-1 0 1 2 -1 -4},
 * <p>
 * A solution set is:
 * (-1, 0, 1)
 * (-1, -1, 2)
 **/

public class Solution {
  // sort
  public List<List<Integer>> threeSum1(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> ret = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
      if (i > 0 && nums[i] == nums[i - 1]) continue;
      int j = i + 1, k = nums.length - 1;
      while (j < k) {
        if (j > i + 1 && nums[j] == nums[j - 1]) j++;
        else {
          if (nums[i] + nums[j] + nums[k] == 0) {
            ret.add(Arrays.asList(nums[i], nums[j], nums[k]));
            j++;
            k--;
          } else if (nums[i] + nums[j] + nums[k] < 0) j++;
          else k--;
        }
      }
    }
    return ret;
  }

  // hashmap, has dup
  public List<List<Integer>> threeSum1(int[] nums) {
    List<List<Integer>> ret = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
      Set<Integer> memo = new HashSet<>();
      for (int j = i + 1; j < nums.length; j++) { // two sum, target = -nums[i]
        if (memo.contains(-nums[i] - nums[j])) {
          ret.add(Arrays.asList(nums[i], -nums[i] - nums[j], nums[j]));
        }
        memo.add(nums[j]);
      }

    }
    return ret;
  }
}