// A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
//
// The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
//
// How many possible unique paths are there?


class Solution {
public:
    int uniquePaths(int m, int n) {
        vector<int> dp(m, 1);  // start as (0, 0)
        for (int j = n - 2; j >= 0; --j) {
            for (int i = m - 2; i >= 0; --i) {
                dp[i] += dp[i + 1];
            }
        }
        return dp[0];
    }
};
