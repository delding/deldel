// Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.
//
// If there is no such window in S that covers all characters in T, return the empty string "". If there are multiple such minimum-length windows, return the one with the left-most starting index.
//
// Example 1:
// Input:
// S = "abcdebdde", T = "bde"
// Output: "bcde"
// Explanation:
// "bcde" is the answer because it occurs before "bdde" which has the same length.
// "deb" is not a smaller window because the elements of T in the window must occur in order.
// Note:
//
// All the strings in the input will only contain lowercase letters.
// The length of S will be in the range [1, 20000].
// The length of T will be in the range [1, 100].


class Solution {
public:
    string minWindow(string S, string T) {
        int m = S.size(), n = T.size();
        vector<vector<int>> dp(m, vector<int>(n, -1));
        int k = -1, len = INT_MAX;
        for (int j = 0; j < n; ++j) {
            for (int i = j; i < m; ++i) {
                if (T[j] == S[i]) dp[i][j] = j > 0 ? dp[i - 1][j - 1] : i;
                else if (i > 0) dp[i][j] = dp[i - 1][j];
                if (j == n - 1 && dp[i][j] != -1) {
                    if (i - dp[i][j] + 1 < len) {
                        len = i - dp[i][j] + 1;
                        k = dp[i][j];
                    }
                }
            }
        }
        return k == -1 ? "" : S.substr(k, len);
    }
};
