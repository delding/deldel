// Given a 01 matrix M, find the longest line of consecutive one in the matrix. The line could be horizontal, vertical, diagonal or anti-diagonal.
//
// Example:
// Input:
// [[0,1,1,0],
//  [0,1,1,0],
//  [0,0,0,1]]
// Output: 3


class Solution {
public:
    int longestLine(vector<vector<int>>& M) {
        if (M.empty()) {
            return 0;
        }
        int m{M.size()};
        int n{M[0].size()};
        int res{0};
        vector<vector<vector<int>>> dp{m, vector<vector<int>>{n, vector<int>(4)}};
        for (auto i = 0; i != m; ++i) {
            for (auto j = 0; j != n; ++j) {
                if (M[i][j] == 1) {
                    res = max(res, dp[i][j][0] = j > 0 ? dp[i][j - 1][0] + 1 : 1);  // horizontal
                    res = max(res, dp[i][j][1] = i > 0 ? dp[i - 1][j][1] + 1 : 1);  // vertical
                    res = max(res, dp[i][j][2] = i > 0 && j > 0 ? dp[i - 1][j - 1][2] + 1 : 1);  // diagonal
                    res = max(res, dp[i][j][3] = i > 0 && j < n - 1 ? dp[i - 1][j + 1][3] + 1 : 1);  // anti-diagonal
                }
            }
        }
        return res;
    }
};
