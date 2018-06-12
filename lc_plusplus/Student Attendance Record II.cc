// Given a positive integer n, return the number of all possible attendance records with length n, which will be regarded as rewardable. The answer may be very large, return it after mod 109 + 7.
//
// A student attendance record is a string that only contains the following three characters:
//
// 'A' : Absent.
// 'L' : Late.
// 'P' : Present.
// A record is regarded as rewardable if it doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).
//
// Example 1:
// Input: n = 2
// Output: 8
// Explanation:
// There are 8 records with length 2 will be regarded as rewardable:
// "PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
// Only "AA" won't be regarded as rewardable owing to more than one absent times.



class Solution {
public:
    int checkRecord(int n) {
        int mod{1000000007};
        vector<long> dp(n + 1);  // dp[n] = end with "P" dp[n - 1] + end with "PL" dp[n - 2] + end with "PLL" dp[n - 3]
        dp[0] = 1; dp[1] = 2; dp[2] = 4;
        for (int i = 3; i <= n; ++i) dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % mod;
        long cnt{dp[n]};  // without "A"
        for (int l = 0; l < n; ++l) {
            cnt = (cnt + dp[l] * dp[n - 1 - l]) % mod;  // contain "A"
        }
        return cnt;  // with and without "A"
    }
};
