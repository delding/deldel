// Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
//
// Find the maximum coins you can collect by bursting the balloons wisely.
//
// Note:
// (1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
// (2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
//
// Example:
//
// Given [3, 1, 5, 8]
//
// Return 167
//
//     nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
//    coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167


class Solution {
public:
    int maxCoins(vector<int>& nums) {
        if (nums.empty()) return 0;
        vector<vector<int>> dp(nums.size(), vector<int>(nums.size()));
        for (int len = 1; len <= nums.size(); ++len) {
            for (int l = 0, r = l + len - 1; r < nums.size(); ++l, ++r) {
                int maxcoins = 0;
                for (int lastburst = l; lastburst <= r; lastburst++) {
                    auto coins = nums[lastburst] * (l - 1 >= 0 ? nums[l - 1] : 1) * (r + 1 < nums.size() ? nums[r + 1] : 1);
                    maxcoins = max(maxcoins, coins + (lastburst - 1 >= l ? dp[l][lastburst - 1] : 0) + (lastburst + 1 <= r ? dp[lastburst + 1][r] : 0));
                }
                dp[l][r] = maxcoins;
            }
        }
        return dp[0][nums.size() - 1];
    }
};
