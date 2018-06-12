// Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
// Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.
//
// Note:
// A naive algorithm of O(n2) is trivial. You MUST do better than that.
//
// Example:
// Given nums = [-2, 5, -1], lower = -2, upper = 2,
// Return 3.
// The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.
//


class Solution {
public:
    int countRangeSum(vector<int>& nums, int lower, int upper) {
        vector<long> prefixSum(nums.size() + 1);
        for (auto i = 1; i < prefixSum.size(); ++i) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        multiset<long> bst{prefixSum.begin(), prefixSum.end()};
        int count = 0;
        for (auto s : prefixSum) {
            bst.erase(bst.find(s));  // only erase one
            auto lowVal = s + lower;
            auto highVal = s + upper;
            auto lt = bst.lower_bound(lowVal);
            auto rt = bst.upper_bound(highVal);
            // lt is the first element >= lowVal, and --rt is the last element <= highval
            count += distance(lt, rt);
        }
        return count;
    }
};
