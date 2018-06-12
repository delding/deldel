// Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words. You may assume the dictionary does not contain duplicate words.
//
// For example, given
// s = "leetcode",
// dict = ["leet", "code"].
//
// Return true because "leetcode" can be segmented as "leet code".


class Solution {
public:
    bool wordBreak(string s, vector<string>& wordDict) {
        unordered_set<string> dict{wordDict.begin(), wordDict.end()};
        vector<bool> dp(s.size());
        for (size_t i = 0; i < dp.size(); ++i) {
            for (size_t len = 1; i + len <= dp.size(); ++len) {
                if ((i == 0 || dp[i - 1]) && dict.count(s.substr(i, len)) == 1) dp[i + len - 1] = true;
            }
        }
        return dp[s.size() - 1];
    }

    bool wordBreak(string s, vector<string>& wordDict) {
        if (s.empty()) return false;
        unordered_set<string> d{wordDict.begin(), wordDict.end()};
        vector<bool> dp(s.size());
        for (size_t i = 0; i < s.size(); ++i) {
            for (size_t j = i; j < s.size(); ++j) {
                if (i == 0 || dp[i - 1]) {
                    dp[j] = dp[j] || (d.count(s.substr(i, j - i + 1)) == 1);
                }
            }
        }
        return dp[s.size() - 1];
    }

    bool wordBreak2(string s, vector<string>& wordDict) {
        unordered_set<string> dict{wordDict.begin(), wordDict.end()};
        vector<bool> dp(s.size() + 1);
        dp[0] = true;
        for (int len{1}; len <= s.size(); ++len) {
            for (int i{0}; i < len; ++i) {
                if (dp[i]) {
                    auto w = s.substr(i, len - i);
                    if (dict.count(w) == 1) {
                        dp[len] = true;
                        break;
                    }
                }
            }
        }
        return dp[s.size()];
    }
};
