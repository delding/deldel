/**
 * Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
 * <p>
 * The same repeated number may be chosen from C unlimited number of times.
 * <p>
 * Note:
 * All numbers (including target) will be positive integers.
 * Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
 * The solution set must not contain duplicate combinations.
 * For example, given candidate set 2,3,6,7 and target 7,
 * A solution set is:
 * [7]
 * [2, 2, 3]
 **/

public class Solution {
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    Arrays.sort(candidates);
    List<List<Integer>> combs = new ArrayList<>();
    dfs(candidates, target, combs, new ArrayList<Integer>(), 0);
    return combs;
  }

  void dfs(int[] cand, int target, List<List<Integer>> combs, List<Integer> comb, int idx) {
    if (target == 0) combs.add(new ArrayList<>(comb));
    else {
      for (int i = idx; i < cand.length; i++) {
        if (cand[i] <= target) {
          comb.add(cand[i]);
          dfs(cand, target - cand[i], combs, comb, i);
          comb.remove(comb.size() - 1);
        }
      }
    }
  }
}