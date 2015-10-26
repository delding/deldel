/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * <p>
 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 */

public class Solution {
  public int maxProfit(int[] prices) {
    int len = prices.length;
    if (len == 0) return 0; // ERROR: edge case
    int[] premin = new int[len];
    int[] suffmax = new int[len];
    premin[0] = prices[0];
    suffmax[len - 1] = prices[len - 1];
    for (int i = 1; i < len; i++) {
      premin[i] = Math.min(prices[i], premin[i - 1]);
    }
    for (int i = len - 2; i >= 0; i--) {
      suffmax[i] = Math.max(suffmax[i + 1], prices[i]);
    }
    int profit = 0;
    for (int i = 0; i < len; i++) {
      profit = Math.max(profit, suffmax[i] - premin[i]);
    }
    return profit;
  }
}
