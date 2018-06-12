// Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. You may assume the dictionary does not contain duplicate words.
//
// Return all such possible sentences.
//
// For example, given
// s = "catsanddog",
// dict = ["cat", "cats", "and", "sand", "dog"].
//
// A solution is ["cats and dog", "cat sand dog"].
//


class Solution {
public:
    vector<string> wordBreak(string s, vector<string>& wordDict) {
        unordered_set<string> d{wordDict.begin(), wordDict.end()};
        vector<vector<int>> dp(s.size());
        for (size_t i = 0; i < s.size(); ++i) {
            if (i == 0 || !dp[i - 1].empty()) {
                for (size_t j = i; j < s.size(); ++j) {
                    if (d.count(s.substr(i, j - i + 1)) == 1) dp[j].push_back(i);
                }
            }
        }
        vector<string> res, words;
        dfs(res, words, dp.size() - 1, dp, s);
        return res;
    }

    void dfs(vector<string>& res, vector<string>& words, int idx, vector<vector<int>>& dp, string& str) {
        if (idx == -1) {
            string s;
            for (auto it = words.rbegin(); it != words.rend(); ++it) s += *it + " ";
            s.pop_back();
            res.push_back(s);
            return;
        }
        for (auto i : dp[idx]) {
            words.push_back(str.substr(i, idx - i + 1));
            dfs(res, words, i - 1, dp, str);
            words.pop_back();
        }
    }
};
