// Given a set of distinct integers, nums, return all possible subsets (the power set).
//
// Note: The solution set must not contain duplicate subsets.
//
// For example,
// If nums = [1,2,3], a solution is:
//
// [
//   [3],
//   [1],
//   [2],
//   [1,2,3],
//   [1,3],
//   [2,3],
//   [1,2],
//   []
// ]


class Solution {
public:
    vector<vector<int>> subsets(vector<int>& nums) {
        vector<vector<int>> res;
        vector<int> s;
        subsets(res, s, 0, nums);
        return res;
    }

    void subsets(vector<vector<int>>& res, vector<int>& s, size_t idx, vector<int>& nums) {
        if (idx == nums.size()) res.push_back(s);
        else {
            for (size_t i = idx; i <= nums.size(); ++i) {
                if (i == nums.size()) subsets(res, s, i, nums);
                else {
                    s.push_back(nums[i]);
                    subsets(res, s, i + 1, nums);
                    s.pop_back();
                }
            }
        }
    }
};
