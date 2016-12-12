/**
 * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
 * <p>
 * Each number in C may only be used once in the combination.
 * <p>
 * Note:
 * All numbers (including target) will be positive integers.
 * Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
 * The solution set must not contain duplicate combinations.
 * For example, given candidate set 10,1,2,7,6,1,5 and target 8,
 * A solution set is:
 * [1, 7]
 * [1, 2, 5]
 * [2, 6]
 * [1, 1, 6]
 **/

public class Solution {
  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    Arrays.sort(candidates);
    List<List<Integer>> combs = new ArrayList<>();
    dfs(candidates, target, combs, new ArrayList<Integer>(), 0);
    return combs;
  }

  void dfs(int[] cand, int target, List<List<Integer>> combs, List<Integer> comb, int idx) {
    if (target == 0) combs.add(new ArrayList<>(comb));
    else {
      for (int i = idx; i < cand.length; i++) {
        if (i > idx && cand[i] == cand[i - 1]) continue;
        if (cand[i] <= target) {
          comb.add(cand[i]);
          dfs(cand, target - cand[i], combs, comb, i + 1);
          comb.remove(comb.size() - 1);
        }
      }
    }
  }
}