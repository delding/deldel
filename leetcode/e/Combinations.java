/**
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 * <p>
 * For example,
 * If n = 4 and k = 2, a solution is:
 * <p>
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 */

public class Solution {
  public List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> ret = new ArrayList();
    dfs(1, n, k, ret, new ArrayList<Integer>());
    return ret;
  }

  private void dfs(int lo, int n, int k, List<List<Integer>> ret, List<Integer> comb) {
    if (k == 0) ret.add(new ArrayList<Integer>(comb));
    else {
      for (int i = lo; i <= n; i++) {
        comb.add(i);
        dfs(i + 1, n, k - 1, ret, comb);
        comb.remove(comb.size() - 1);
      }
    }
  }
}
