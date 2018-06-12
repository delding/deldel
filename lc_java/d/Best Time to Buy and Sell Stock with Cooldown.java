/**
 Say you have an array for which the ith element is the price of a given stock on day i.
 Design an algorithm to find the maximum profit. You may complete as many transactions as you like
 (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
 You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
 Example:
 prices = [1, 2, 3, 0, 2]
 maxProfit = 3
 transactions = [buy, sell, cooldown, buy, sell]
 **/

public class Solution {
  public int maxProfit(int[] prices) {
    int len = prices.length;
    if (len <= 1) return 0;
    int[] buys = new int[len];
    int[] sells = new int[len];
    buys[0] = -prices[0];
    sells[0] = 0;
    buys[1] = -prices[1];
    sells[1] = Math.max(0, prices[1] - prices[0]);
    int max = Math.max(sells[0], sells[1]);
    for (int i = 2; i < len; i++) {
      buys[i] = Math.max(buys[i - 1] + prices[i - 1] - prices[i], sells[i - 2] - prices[i]); // i-1 not cooldown or i-1 cooldown
      sells[i] = Math.max(sells[i - 1] - prices[i - 1] + prices[i], buys[i - 1] + prices[i]); // i-1 not buy or i-1 buy
      max = Math.max(max, sells[i]);
    }
    return max;
  }
}