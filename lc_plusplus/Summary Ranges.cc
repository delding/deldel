// Given a sorted integer array without duplicates, return the summary of its ranges.
//
// Example 1:
// Input: [0,1,2,4,5,7]
// Output: ["0->2","4->5","7"]
// Example 2:
// Input: [0,2,3,4,6,8,9]
// Output: ["0","2->4","6","8->9"]


class Solution {
public:
    vector<string> summaryRanges(vector<int>& nums) {
        vector<string> ret;
        for (size_t i = 0, j = 0; j < nums.size(); ++j) {
            if (j + 1 == nums.size() || nums[j] + 1 != nums[j + 1]) {
                if (i == j) ret.push_back(to_string(nums[i]));
                else ret.push_back(to_string(nums[i]) + "->" + to_string(nums[j]));
                i = j + 1;
            }
        }
        return ret;
    }
};
