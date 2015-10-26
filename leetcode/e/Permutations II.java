/**
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 * <p>
 * For example,
 * [1,1,2] have the following unique permutations:
 * [1,1,2], [1,2,1], and [2,1,1].
 **/

public class Solution {
  public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> ret = new ArrayList<>();
    dfs(nums, new boolean[nums.length], new ArrayList<Integer>(), ret);
    return ret;
  }

  void dfs(int[] nums, boolean[] used, List<Integer> cur, List<List<Integer>> ret) {
    if (cur.size() == nums.length) ret.add(new ArrayList<>(cur));
    else {
      Set<Integer> localUsed = new HashSet<>();
      for (int i = 0; i < nums.length; i++) {
        if (!used[i] && !localUsed.contains(nums[i])) {
          localUsed.add(nums[i]);
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