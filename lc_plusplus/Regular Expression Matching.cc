// Implement regular expression matching with support for '.' and '*'.
//
// '.' Matches any single character.
// '*' Matches zero or more of the preceding element.
//
// The matching should cover the entire input string (not partial).
//
// The function prototype should be:
// bool isMatch(const char *s, const char *p)
//
// Some examples:
// isMatch("aa","a") → false
// isMatch("aa","aa") → true
// isMatch("aaa","aa") → false
// isMatch("aa", "a*") → true
// isMatch("aa", ".*") → true
// isMatch("ab", ".*") → true
// isMatch("aab", "c*a*b") → true


class Solution {
public:
    bool isMatch(string s, string p) {
        vector<vector<bool>> dp(s.size() + 1, vector<bool>(p.size() + 1));
        dp[0][0] = true;
        for (size_t i = 0; i < dp.size(); ++i) {
            for (size_t j = 1; j < dp[0].size(); ++j) {
                if (p[j - 1] == '*') {
                    dp[i][j] = dp[i][j - 2];
                    if (i > 0 && (p[j - 2] == '.' || p[j - 2] == s[i - 1])) dp[i][j] = dp[i][j] || dp[i - 1][j];
                } else if (i > 0) {
                    if (p[j - 1] == '.' || p[j - 1] == s[i - 1]) dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[s.size()][p.size()];
    }
};
