// Given a set of candidate numbers (C) (without duplicates) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
//
// The same repeated number may be chosen from C unlimited number of times.
//
// Note:
// All numbers (including target) will be positive integers.
// The solution set must not contain duplicate combinations.
// For example, given candidate set [2, 3, 6, 7] and target 7,
// A solution set is:
// [
//   [7],
//   [2, 2, 3]
// ]


class Solution {
public:
    vector<vector<int>> combinationSum(vector<int>& candidates, int target) {
        vector<vector<int>> res;
        vector<int> vals;
        solve(candidates, target, 0, res, vals, 0);
        return res;
    }

    void solve(vector<int>& cand, int target, int sum, vector<vector<int>>& res, vector<int>& vals, size_t i) {
        if (sum == target) {
            res.push_back(vals);
            return;
        }
        for (size_t j = i; j < cand.size(); ++j) {
            if (cand[j] + sum <= target) {
                vals.push_back(cand[j]);
                solve(cand, target, sum + cand[j], res, vals, j);
                vals.pop_back();
            }
        }
    }
};
