/**
 * There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
 * <p>
 * The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.
 * <p>
 * Note:
 * All costs are positive integers.
 * <p>
 * Follow up:
 * Could you solve it in O(nk) runtime?
 */

public class Solution {
  // DP
  // minCosts[i][j]: the minimum total cost for painting i+1 houses when i-th house with j-th color
  // minCosts[i][j] = Min over all k {minCosts[i-1][k] + costs[i][j]}, k != j
  public int minCostII(int[][] costs) {
    int n = costs.length;
    if (n == 0) return 0;
    int k = costs[0].length;
    int[][] minCosts = new int[n][k];
    for (int j = 0; j < k; j++) {
      minCosts[0][j] = costs[0][j];
    }
    for (int i = 1; i < n; i++) {
      for (int j = 0; j < k; j++) {
        int minCost = Integer.MAX_VALUE;
        for (int kk = 0; kk < k; kk++) {
          if (kk == j) continue;
          minCost = Math.min(minCost, minCosts[i - 1][kk] + costs[i][j]);
        }
        minCosts[i][j] = minCost;
      }
    }
    int rst = minCosts[n - 1][0];
    for (int j = 1; j < k; j++) {
      rst = Math.min(rst, minCosts[n - 1][j]);
    }
    return rst;
  }
}
