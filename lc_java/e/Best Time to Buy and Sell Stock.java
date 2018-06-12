/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * <p>
 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 */

public class Solution {

  public int maxProfit(int[] prices) {
    int min = Integer.MAX_VALUE, max = 0;
    for (int i = 0; i < prices.length; i++) {
      min = Math.min(min, prices[i]);
      max = Math.max(max, prices[i] - min);
    }
    return max;
  }

  public int maxProfit1(int[] prices) {
    int[] lmin = new int[prices.length];
    int[] rmax = new int[prices.length];
    for (int i = 0; i < prices.length; i++) {
      if (i == 0) {
        lmin[i] = prices[i];
        rmax[prices.length - 1 - i] = prices[prices.length - 1 - i];
      } else {
        lmin[i] = Math.min(lmin[i - 1], prices[i]);
        rmax[prices.length - 1 - i] = Math.max(rmax[prices.length - i], prices[prices.length - 1 - i]);
      }
    }
    int p = 0;
    for (int i = 0; i < prices.length - 1; i++) {
      p = Math.max(p, rmax[i + 1] - lmin[i]);
    }
    return p;
  }
}
