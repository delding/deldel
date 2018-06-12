/**
 * There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain
 * color is different. You have to paint all the houses such that no two adjacent houses have the same color.
 * The cost of painting each house with a certain color is represented by a n x k cost matrix. For example,
 * costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2,
 * and so on... Find the minimum cost to paint all houses.
 * Note:
 * All costs are positive integers.
 * Follow up:
 * Could you solve it in O(nk) runtime?
 */

public class Solution {
  public int minCostII1(int[][] costs) {
    int n = costs.length;
    if (n == 0 || costs[0].length == 0) return 0;
    int k = costs[0].length;
    int[] pre = Arrays.copyOf(costs[0], k);
    int[] cur = new int[k];
    for (int i = 1; i < n; i++) {
      for (int j = 0; j < k; j++) {
        cur[j] = Integer.MAX_VALUE;
        for (int h = 0; h < k; h++) {
          if (h != j) {
            cur[j] = Math.min(cur[j], pre[h] + costs[i][j]);
          }
        }
      }
      pre = Arrays.copyOf(cur, k);
    }
    int min = pre[0];
    for (int cost : pre) min = Math.min(min, cost);
    return min;
  }

  // O(nk) use min1 and min2 to track the indices of the 1st and 2nd smallest cost till previous house,
  // if the current color's index is same as min1, then we have to go with min2, otherwise we can safely go with min
  public int minCostII(int[][] costs) {
    int n = costs.length;
    if (n == 0 || costs[0].length == 0) return 0;
    int k = costs[0].length;
    int preMin = 0, pre2ndMin = 0, minIdx = -1;
    for (int i = 0; i < n; i++) {
      int curMin = 0, cur2ndMin = 0, curMinIdx = -1;
      for (int j = 0; j < k; j++) {
        costs[i][j] += j == minIdx ? pre2ndMin : preMin;
        if (curMin == 0 || costs[i][j] < curMin) {
          cur2ndMin = curMin;
          curMin = costs[i][j];
          curMinIdx = j;
        } else if (cur2ndMin == 0 || costs[i][j] < cur2ndMin) {
          cur2ndMin = costs[i][j];
        }
      }
      preMin = curMin;
      pre2ndMin = cur2ndMin;
      minIdx = curMinIdx;
    }
    return preMin;
  }
}
