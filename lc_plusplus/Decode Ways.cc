// A message containing letters from A-Z is being encoded to numbers using the following mapping:
//
// 'A' -> 1
// 'B' -> 2
// ...
// 'Z' -> 26
// Given an encoded message containing digits, determine the total number of ways to decode it.
//
// For example,
// Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
//
// The number of ways decoding "12" is 2.


class Solution {
public:
    int numDecodings(string s) {
        if (s.empty()) return 0;
        vector<int> dp(s.size());
        dp[0] = s[0] == '0' ? 0 : 1;
        for (size_t i = 1; i < s.size(); ++i) {
            if (s[i] != '0') dp[i] += dp[i - 1];
            auto num = stoi(s.substr(i - 1, 2));
            if (num >= 10 && num <= 26) dp[i] += i >= 2 ? dp[i - 2] : 1;
        }
        return dp.back();
    }
};
