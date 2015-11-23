/**
 * Numbers can be regarded as product of its factors. For example,
 * 8 = 2 x 2 x 2;
 *   = 2 x 4.
 * Write a function that takes an integer n and return all possible combinations of its factors.
 * Note:
 * Each combination's factors must be sorted ascending, for example: The factors of 2 and 6 is [2, 6], not [6, 2].
 * You may assume that n is always positive.
 * Factors should be greater than 1 and less than n.
 * Examples:
 * input: 1
 * output:
 * []
 * input: 37
 * output:
 * []
 * input: 12
 * output:
 * [
 * [2, 6],
 * [2, 2, 3],
 * [3, 4]
 * ]
 * input: 32
 * output:
 * [
 * [2, 16],
 * [2, 2, 8],
 * [2, 2, 2, 4],
 * [2, 2, 2, 2, 2],
 * [2, 4, 4],
 * [4, 8]
 * ]
 */

public class Solution {
  // imagine every recursion call branch a new child at the next level
  public List<List<Integer>> getFactors(int n) {
    List<List<Integer>> rst = new ArrayList<List<Integer>>();
    if (n == 1) return rst;
    List<Integer> factors = new ArrayList<Integer>();
    dfs(rst, factors, n, 2);
    return rst;
  }

  private void dfs(List<List<Integer>> rst, List<Integer> factors, int n, int low) { // low is used to make factors non-dereasing
    if (n == 1) {
      if (factors.size() > 1) { // ERROR: >1 to exclude n itself being added as a whole
        rst.add(new ArrayList<Integer>(factors)); // ERROR: must clone
      }
      return;
    }
    // iterate every number not just prime number, and add the number as a single factor, not using multiple of multiple prime as a factor
    for (int i = low; i <= n; i++) { // ERROR: i must be allowed to equal to n, so that in sub-routine n itself can be added as a factor
      if (n % i == 0) {
        factors.add(i);
        dfs(rst, factors, n / i, i); // NOTE: every dfs call corresponds a child for a subtree
        factors.remove(factors.size() - 1);
      }
    }
  }
}
