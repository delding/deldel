/**
 * Given a set of distinct integers, nums, return all possible subsets.
 * <p>
 * Note:
 * Elements in a subset must be in non-descending order.
 * The solution set must not contain duplicate subsets.
 * For example,
 * If nums = [1,2,3], a solution is:
 * <p>
 * [
 * [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 */

public class Solution {
  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> ret = new ArrayList<>();
    if (nums.length == 0) return ret;
    Arrays.sort(nums);
    solve2(nums, ret, new ArrayList<Integer>(), 0);
    return ret;
  }

  // this search tree each node has two children
  void solve(int[] nums, List<List<Integer>> ret, List<Integer> set, int i) {
    if (i == nums.length) {
      ret.add(new ArrayList<Integer>(set));
      return;
    }
    solve(nums, ret, set, i+1);
    set.add(nums[i]);
    solve(nums, ret, set, i + 1);
    set.remove(set.size() - 1);
  }

  // this search tree each node has many children (from i = idx to i = nums.length)
  void solve2(int[] nums, List<List<Integer>> subsets, List<Integer> subset, int idx) {
    if (idx == nums.length) subsets.add(new ArrayList<Integer>(subset));
    else {
      for (int i = idx; i <= nums.length; i++) {
        if (i == nums.length) solve2(nums, subsets, subset, i); // i==nums.length corresponds to select no number
        else {
          subset.add(nums[i]);
          solve2(nums, subsets, subset, i + 1);
          subset.remove(subset.size() - 1);
        }
      }
    }
  }
}
