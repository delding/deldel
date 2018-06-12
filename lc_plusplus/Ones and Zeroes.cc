// In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.
//
// For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with strings consisting of only 0s and 1s.
//
// Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.
//
// Note:
// The given numbers of 0s and 1s will both not exceed 100
// The size of given string array won't exceed 600.
// Example 1:
// Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
// Output: 4
//
// Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”
// Example 2:
// Input: Array = {"10", "0", "1"}, m = 1, n = 1
// Output: 2
//
// Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".


class Solution {
public:
    int findMaxForm(vector<string>& strs, int m, int n) {
        vector<vector<vector<int>>> dp(strs.size() + 1, vector<vector<int>>(m + 1, vector<int>(n + 1)));
        for (int i = 1; i <= strs.size(); ++i) {
            int ones = 0;
            for (auto c : strs[i - 1]) {
                if (c == '1') ++ones;
            }
            int zeros = strs[i - 1].size() - ones;
            // can reduce to 2d dp if for(j = m; j >= zeros; --j) for(k = n; k >= ones; --k) {dp[j][k] = max(dp[j][k], 1 + dp[j - zeros][k - ones]);}
            for (int j = 0; j <= m; ++j) {
                for (int k = 0; k <= n; ++k) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (j - zeros >= 0 && k - ones >= 0) {
                        dp[i][j][k] = max(dp[i][j][k], dp[i - 1][j - zeros][k - ones] + 1);
                    }
                }
            }
        }
        return dp[strs.size()][m][n];
    }
};
