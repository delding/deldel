// Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.
//
// Example 1:
// Input:
//
// "bbbab"
// Output:
// 4
// One possible longest palindromic subsequence is "bbbb".
// Example 2:
// Input:
//
// "cbbd"
// Output:
// 2
// One possible longest palindromic subsequence is "bb".


class Solution {
public:
    int longestPalindromeSubseq(string s) {
        if (s.empty()) return 0;
        vector<vector<int>> dp{s.size(), vector<int>(s.size())};
        for (size_t len = 1; len <= s.size(); ++len) {
            for (size_t i = 0, j = i + len - 1; j < s.size(); ++i, ++j) {
                if (len == 1) dp[i][j] = 1;
                else if (len == 2) dp[i][j] = s[i] == s[j] ? 2 : 1;
                else {
                    if (s[i] == s[j]) dp[i][j] = dp[i + 1][j - 1] + 2;
                    else {
                        dp[i][j] = max(dp[i + 1][j], dp[i][j - 1]);
                    }
                }
            }
        }
        return dp[0][s.size() - 1];
    }
};
