/**
 Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

 Each number in C may only be used once in the combination.

 Note:
 All numbers (including target) will be positive integers.
 Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
 The solution set must not contain duplicate combinations.
 For example, given candidate set 10,1,2,7,6,1,5 and target 8,
 A solution set is:
 [1, 7]
 [1, 2, 5]
 [2, 6]
 [1, 1, 6]
 **/

public class Solution {
  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    Arrays.sort(candidates);
    List<List<Integer>> ret = new ArrayList<>();
    dfs(candidates, target, 0, new ArrayList<Integer>(), ret);
    return ret;
  }

  private void dfs(int[] candidates, int target, int idx, List<Integer> cur, List<List<Integer>> ret) {
    if (target < 0) return;
    if (target == 0) {
      List<Integer> copy = new ArrayList<Integer>(cur);
      ret.add(copy);
      return;
    }
    int pre = -1;
    for (int i = idx; i < candidates.length; i++) {
      if (candidates[i] == pre) continue;
      pre = candidates[i];
      cur.add(pre);
      dfs(candidates, target - pre, i + 1, cur, ret);
      cur.remove(cur.size() - 1);
    }
  }
}