// Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
//
// Note: The solution set must not contain duplicate subsets.
//
// For example,
// If nums = [1,2,2], a solution is:
//
// [
//   [2],
//   [1],
//   [1,2,2],
//   [2,2],
//   [1,2],
//   []
// ]


class Solution {
public:
    vector<vector<int>> subsetsWithDup(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        vector<vector<int>> res;
        res.push_back({});
        vector<vector<int>> last;
        for (int i = 0; i < nums.size(); ++i) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                last.clear();
                for (auto v : res) {
                    v.push_back(nums[i]);
                    last.push_back(move(v));
                }
            } else {
                for (auto& v : last) {
                    v.push_back(nums[i]);
                }
            }
            for (auto& v : last) res.push_back(v);
        }
        return res;
    }


    vector<vector<int>> subsetsWithDupDFS(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        vector<vector<int>> res;
        vector<int> s;
        dfs(res, nums, 0, s);
        return res;
    }

    void dfs(vector<vector<int>>& res, const vector<int>& nums, int i, vector<int>& s) {
        if (i == nums.size()) res.push_back(s);
        else {
            for (int j = i; j <= nums.size(); ++j) {
                if (j == nums.size()) dfs(res, nums, j, s);
                else {
                    if (j == i || nums[j] != nums[j - 1]) {
                        s.push_back(nums[j]);
                        dfs(res, nums, j + 1, s);
                        s.pop_back();
                    }
                }
            }
        }
    }
};
