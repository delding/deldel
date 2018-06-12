/**
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 * <p>
 * For example,
 * [1,1,2] have the following unique permutations:
 * [1,1,2], [1,2,1], and [2,1,1].
 **/

public class Solution {
  public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    dfs(nums, new boolean[nums.length], res, new ArrayList<>());
    return res;
  }

  void dfs(int[] nums, boolean[] used, List<List<Integer>> res, List<Integer> perm) {
    if (perm.size() == nums.length) {
      res.add(new ArrayList<>(perm));
    } else {
      Set<Integer> usedLocal = new HashSet<>();
      for (int i = 0; i < nums.length; i++) {
        if (!used[i] && !usedLocal.contains(nums[i])) { // local duplicates can lead next level of search tree has duplicate branches
          usedLocal.add(nums[i]);
          used[i] = true;
          perm.add(nums[i]);
          dfs(nums, used, res, perm);
          used[i] = false;
          perm.remove(perm.size() - 1);
        }
      }
    }
  }
}