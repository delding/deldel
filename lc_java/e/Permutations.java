/**
 * Given a collection of numbers, return all possible permutations.
 * <p>
 * For example,
 * [1,2,3] have the following permutations:
 * [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
 **/

// difference between permutation and combination is that combinationn use an extra index to add order contraint
public class Solution {
  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> ret = new ArrayList<>();
    dfs(nums, new boolean[nums.length], new ArrayList<Integer>(), ret);
    return ret;
  }

  void dfs(int[] nums, boolean[] used, List<Integer> cur, List<List<Integer>> ret) {
    if (cur.size() == nums.length) {
      ret.add(new ArrayList<Integer>(cur));
    } else {
      for (int i = 0; i < nums.length; i++) {
        if (!used[i]) {
          cur.add(nums[i]);
          used[i] = true;
          dfs(nums, used, cur, ret);
          used[i] = false;
          cur.remove(cur.size() - 1);
        }
      }
    }
  }
}