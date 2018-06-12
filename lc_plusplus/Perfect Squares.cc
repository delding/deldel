// Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
//
// For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.


class Solution {
public:
    int numSquares(int n) {
        vector<int> dp(n + 1);
        for (int num = 1; num <= n; ++num) {
            int minNum{INT_MAX};
            for (int root = 1; root * root <= num; ++root) {
                minNum = min(minNum, 1 + dp[num - root * root]);
            }
            dp[num] = minNum;
        }
        return dp[n];
    }
};
