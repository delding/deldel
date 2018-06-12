/**
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets.
 * Note:
 * Elements in a subset must be in non-descending order.
 * The solution set must not contain duplicate subsets.
 * For example,
 * If nums = [1,2,2], a solution is:
 * [
 * [2],
 * [1],
 * [1,2,2],
 * [2,2],
 * [1,2],
 * []
 * ]
 */

public class Solution {
  public List<List<Integer>> subsetsWithDup(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> sets = new ArrayList<>();
    dfs(sets, new ArrayList<Integer>(), nums, 0);
    return sets;
  }

  void dfs(List<List<Integer>> sets, List<Integer> set, int[] nums, int idx) {
    if (idx == nums.length) {
      sets.add(new ArrayList<>(set));
    } else {
      for (int i = idx; i <= nums.length; i++) {
        if (i == nums.length) dfs(sets, set, nums, i);
        else if (i == idx || nums[i] != nums[i - 1]) {
          set.add(nums[i]);
          dfs(sets, set, nums, i + 1);
          set.remove(set.size() - 1);
        }
      }
    }
  }
}