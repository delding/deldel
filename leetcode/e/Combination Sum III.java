/**
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
 * Ensure that numbers within the set are sorted in ascending order.
 * <p>
 * Example 1:
 * <p>
 * Input: k = 3, n = 7
 * <p>
 * Output:
 * <p>
 * [[1,2,4]]
 * <p>
 * Example 2:
 * <p>
 * Input: k = 3, n = 9
 * <p>
 * Output:
 * <p>
 * [[1,2,6], [1,3,5], [2,3,4]]
 **/

public class Solution {
  public List<List<Integer>> combinationSum3(int k, int n) {
    List<List<Integer>> combs = new ArrayList<>();
    dfs(n, combs, new ArrayList<Integer>(), 1, k);
    return combs;
  }

  void dfs(int target, List<List<Integer>> combs, List<Integer> comb, int num, int k) {
    if (comb.size() == k) {
      if (target == 0) combs.add(new ArrayList<>(comb));
    } else {
      for (int i = num; i < 10; i++) {
        if (i <= target) {
          comb.add(i);
          dfs(target - i, combs, comb, i + 1, k);
          comb.remove(comb.size() - 1);
        }
      }
    }
  }
}