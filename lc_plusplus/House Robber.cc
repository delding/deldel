// You are a professional robber planning to rob houses along a street. Each house
// has a certain amount of money stashed, the only constraint stopping you from robbing
// each of them is that adjacent houses have security system connected and it will automatically
// contact the police if two adjacent houses were broken into on the same night.
//
// Given a list of non-negative integers representing the amount of money of each house,
// determine the maximum amount of money you can rob tonight without alerting the police.


class Solution {
public:
    int rob(vector<int>& nums) {
        if (nums.empty()) return 0;
        vector<int> rob(nums.size());
        rob[0] = nums[0];
        for (size_t i = 1; i < rob.size(); ++i) {
            if (i == 1) rob[1] = max(nums[0], nums[1]);
            else {
                rob[i] = max(nums[i] + rob[i - 2], rob[i - 1]);
            }
        }
        return rob.back();
    }
};
