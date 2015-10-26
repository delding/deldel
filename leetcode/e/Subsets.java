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
    Arrays.sort(nums);
    List<List<Integer>> subsets = new ArrayList<List<Integer>>();
    dfs(nums, subsets, new ArrayList<Integer>(), 0);
    return subsets;
  }

  private void dfs(int[] nums, List<List<Integer>> subsets, List<Integer> subset, int idx) {
    if (idx == nums.length) subsets.add(new ArrayList<Integer>(subset));
    else {
      for (int i = idx; i <= nums.length; i++) {
        if (i == nums.length) dfs(nums, subsets, subset, nums.length);
        else {
          subset.add(nums[i]);
          dfs(nums, subsets, subset, i + 1);
          subset.remove(subset.size() - 1);
        }
      }
    }
  }

  // solution 2
  private void dfs2(int[] nums, int idx, List<List<Integer>> res, List<Integer> list) {
    if (idx == nums.length) {
      res.add(new ArrayList(list));
      return;
    }
    dfs2(nums, idx + 1, res, list); // not add
    list.add(nums[idx]);
    dfs2(nums, idx + 1, res, list); // add
    list.remove(list.size() - 1);
  }
}
