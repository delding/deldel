/**
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets.
 * <p>
 * Note:
 * Elements in a subset must be in non-descending order.
 * The solution set must not contain duplicate subsets.
 * For example,
 * If nums = [1,2,2], a solution is:
 * <p>
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
    List<List<Integer>> ret = new ArrayList();
    dfs(nums, 0, new ArrayList<Integer>(), ret);
    return ret;
  }

  private void dfs(int[] nums, int idx, List<Integer> curr, List<List<Integer>> ret) {
    if (idx == nums.length) ret.add(new ArrayList<Integer>(curr));
    else {
      int prev = nums[idx];
      for (int i = idx; i <= nums.length; i++) {
        if (i == nums.length) dfs(nums, i, curr, ret); // add nothing at current recursion
        else if (i > idx && nums[i] == prev)
          continue; // skip duplicates given nums[] is sorted, treat duplicate numbers as a group
        else {
          curr.add(nums[i]);
          dfs(nums, i + 1, curr, ret);
          curr.remove(curr.size() - 1);
          prev = nums[i];
        }
      }
    }
  }
}
