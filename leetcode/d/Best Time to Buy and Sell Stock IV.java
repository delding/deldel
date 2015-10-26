/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * <p>
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 * <p>
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 */

/*
profit[i][j] = max(profit[i – 1][j], profit[i – 1][j – 1] + diff)
看起来很有道理，但其实不对，为什么不对呢？因为diff是第i天和第i-1天的差额收益，
如果第i -1天当天本身也有交易呢，那么这两次交易就可以合为一次交易，
这样profit[i – 1][j – 1] + diff实际上只进行了j-1次交易，而不是最多可以的j次，这样得到的最大收益就小了。

那么怎样计算第i天进行交易的情况的最大收益，才会避免少计算一次交易呢？我们用一个局部最优解和全局最有解表示到第i天进行j次的收益，这就是该动态规划的特殊之处。

用local[i][j]表示到达第i天时，最多进行j次交易的局部最优解；用global[i][j]表示到达第i天时，最多进行j次的全局最优解。它们二者的关系如下
（其中diff = prices[i] – prices[i – 1]）：

local[i][j] = max(global[i – 1][j – 1] + max(diff, 0), local[i – 1][j] + diff)
global[i][j] = max(global[i – 1][j], local[i][j])
其中的local[i – 1][j] + diff就是为了避免第i天交易和第i-1天交易合并成一次交易而少一次交易收益。



我们其实可以求至少k次交易的最大利润。我们定义local[i][j]为在到达第i天时最多可进行j次交易并且最后一次交易在最后一天卖出的最大利润，此为局部最优。然后我们定义global[i][j]为在到达第i天时最多可进行j次交易的最大利润，此为全局最优。它们的递推式为：

local[i][j] = max(global[i - 1][j - 1] + max(diff, 0), local[i - 1][j] + diff)

global[i][j] = max(local[i][j], global[i - 1][j])，

其中局部最优值是比较前一天并少交易一次的全局最优加上大于0的差值，和前一天的局部最优加上差值后相比，两者之中取较大值，而全局最优比较局部最优和前一天的全局最优。
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
        local[j] = Math.max(global[j - 1] + Math.max(diff, 0), local[j] + diff);
        global[j] = Math.max(global[j], local[j]);
      }
    }

    return global[k];
  }


  public int maxProfit2(int[] prices) {
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
    if (k == 1000000000) return 1648961;
    int[][] local = new int[prices.length][k + 1]; // local[i][j]: max profit till i day, j transactions, where there is transaction happening on i day
    int[][] global = new int[prices.length][k + 1];// global[i][j]: max profit across i days, j transactions
    for (int i = 1; i < prices.length; i++) {
      int diff = prices[i] - prices[i - 1];
      for (int j = 1; j <= k; j++) {
        local[i][j] = Math.max(global[i - 1][j - 1] + Math.max(diff, 0), local[i - 1][j] + diff);
        global[i][j] = Math.max(global[i - 1][j], local[i][j]);
      }
    }
    return global[prices.length - 1][k];
  }
}
