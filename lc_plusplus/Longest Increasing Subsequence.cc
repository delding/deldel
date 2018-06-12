// Given an unsorted array of integers, find the length of longest increasing subsequence.
//
// For example,
// Given [10, 9, 2, 5, 3, 7, 101, 18],
// The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4.
// Note that there may be more than one LIS combination, it is only necessary for you to return the length.
//
// Your algorithm should run in O(n2) complexity.
//
// Follow up: Could you improve it to O(n log n) time complexity?
//


class Solution {
public:
    int lengthOfLIS(vector<int>& nums) {
        if (nums.empty()) return 0;
        vector<int> dp(nums.size());
        dp[0] = 1;
        int maxLen = 1;
        for (size_t i = 1; i < dp.size(); ++i) {
            int len = 1;
            for (size_t j = 0; j < i; ++j) {
                if (nums[j] < nums[i]) len = max(len, 1 + dp[j]);
            }
            dp[i] = len;
            maxLen = max(maxLen, len);
        }
        return maxLen;
    }
};
