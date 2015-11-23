/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 */

/*
profit[i][j] = max(profit[i – 1][j], profit[i – 1][j – 1] + diff)
diff是第i天和第i-1天的差额收益，
如果第i -1天当天本身也有交易，那么这两次交易就可以合为一次交易，
这样profit[i – 1][j – 1] + diff实际上只进行了j-1次交易，而不是最多可以的j次，这样得到的最大收益就小了。

用local[i][j]表示到达第i天时，最多进行j次交易的局部最优解；用global[i][j]表示到达第i天时，最多进行j次的全局最优解。它们二者的关系如下
（其中diff = prices[i] – prices[i – 1]）：

local[i][j] = max(global[i – 1][j – 1] + max(diff, 0), local[i – 1][j] + diff)
global[i][j] = max(global[i – 1][j], local[i][j])
其中的local[i – 1][j] + diff就是为了避免第i天交易和第i-1天交易合并成一次交易而少一次交易收益。
*/

public class Solution {
  public int maxProfit(int k, int[] prices) {
    if (prices.length < 2) return 0;
    if (k >= prices.length) return maxProfit2(prices);

    int[] local = new int[k + 1]; // number of transactions range from j = 0 to j = k, j = 0 is initial condition
    int[] global = new int[k + 1];

    for (int i = 1; i < prices.length; i++) {
      int diff = prices[i] - prices[i - 1];
      for (int j = k; j > 0; j--) { // ERROR: if iterate from j = 1 to k, when assign local[j], global[j-1] has been updated to the current value
        // local[j] = Math.max(global[j - 1] + Math.max(diff, 0), local[j] + diff);
        local[j] = Math.max(global[j - 1] + diff, local[j] + diff); // buying stock at day i - 1 v.s. not buying stock at day i - 1
        global[j] = Math.max(global[j], local[j]); // selling stock at day i v.s. not selling stock at day i
      }
    }

    return global[k];
  }


  private int maxProfit2(int[] prices) {
    int maxProfit = 0;
    for (int i = 1; i < prices.length; i++) {
      if (prices[i] > prices[i - 1]) {
        maxProfit += prices[i] - prices[i - 1];
      }
    }
    return maxProfit;
  }

  public int maxProfit2D(int k, int[] prices) {
    if (prices.length < 2 || k <= 0) return 0;
    if (k >= prices.length) return maxProfit2(prices);
    int[][] local = new int[prices.length][k + 1]; // local[i][j]: max profit till i day, j transactions, where there is transaction happening on i day
    int[][] global = new int[prices.length][k + 1];// global[i][j]: max profit across i days, j transactions
    for (int i = 1; i < prices.length; i++) {
      int diff = prices[i] - prices[i - 1];
      for (int j = 1; j <= k; j++) {
        // local[i][j] = Math.max(global[i - 1][j - 1] + Math.max(diff, 0), local[i - 1][j] + diff);
        local[i][j] = Math.max(global[i - 1][j - 1] + diff, local[i - 1][j] + diff); // buying stock at day i - 1 v.s. not buying stock at day i - 1
        global[i][j] = Math.max(global[i - 1][j], local[i][j]); // selling stock at day i v.s. not selling stock at day i
      }
    }
    return global[prices.length - 1][k];
  }
}
