// Say you have an array for which the ith element is the price of a given stock on day i.
//
// Design an algorithm to find the maximum profit. You may complete as many transactions
// as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
//
// You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
// After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
// Example:
//
// prices = [1, 2, 3, 0, 2]
// maxProfit = 3
// transactions = [buy, sell, cooldown, buy, sell]


class Solution {
public:
    int maxProfit(vector<int>& prices) {
        if (prices.size() < 2) return 0;
        vector<int> buys(prices.size());
        vector<int> sells(prices.size());
        buys[0] = -prices[0];
        buys[1] = -prices[1];
        sells[0] = 0;
        sells[1] = max(0, prices[1] - prices[0]);
        int profit = sells[1];
        for (int i = 2; i < prices.size(); ++i) {
            buys[i] = max(sells[i - 2] - prices[i], buys[i - 1] + prices[i - 1] - prices[i]); // i - 1 cooldown vs i - 1 no cooldown
            sells[i] = max(buys[i - 1] + prices[i], sells[i - 1] - prices[i - 1] + prices[i]); // buy at i - 1 vs buy before i - 1
            profit = max(profit, sells[i]);
        }
        return profit;
    }
};
