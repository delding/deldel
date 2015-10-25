/**
 Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

 The same repeated number may be chosen from C unlimited number of times.

 Note:
 All numbers (including target) will be positive integers.
 Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
 The solution set must not contain duplicate combinations.
 For example, given candidate set 2,3,6,7 and target 7,
 A solution set is:
 [7]
 [2, 2, 3]
 **/

public class Solution {
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    Arrays.sort(candidates);
    List<List<Integer>> ret = new ArrayList<>();
    dfs(candidates, target, 0, new ArrayList<Integer>(), ret);
    return ret;
  }

  void dfs(int[] cand, int target, int idx, List<Integer> cur, List<List<Integer>> ret) {
    if (target == 0) ret.add(new ArrayList<>(cur));
    else {
      for (int i = idx; i < cand.length; i++) {
        if (target - cand[i] < 0) break; // bug: must prune here, otherwise infinite recursion since allow duplication
        cur.add(cand[i]);
        dfs(cand, target - cand[i], i, cur, ret); // pass i instead of i + 1 to allow duplicates
        cur.remove(cur.size() - 1);
      }
    }
  }
}