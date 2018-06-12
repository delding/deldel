// Given a sorted integer array where the range of elements are in the inclusive range [lower, upper], return its missing ranges.
//
// For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].


class Solution {
public:
    vector<string> findMissingRanges(vector<int>& nums, int lower, int upper) {
        vector<string> ret;
        long low = lower, up = upper;
        for (size_t i = 0; i < nums.size(); ++i) {
            if (nums[i] > low) { // > instead of != because nums[i] can be smaller than lower if nums[i] == nums[i - 1]
                if (nums[i] - 1 == low) ret.push_back(to_string(low));
                else ret.push_back(to_string(low) + "->" + to_string(nums[i] - 1));
            }
            low = static_cast<long>(nums[i]) + 1;
        }
        if (low != up + 1) {
            if (low == up) ret.push_back(to_string(up));
            else ret.push_back(to_string(low) + "->" + to_string(up));
        }
        return ret;
    }
};


class Solution {
public:
    vector<string> findMissingRanges(vector<int>& nums, int lower, int upper) {
        vector<string> ret;
        long prev{static_cast<long>(lower) - 1};  // can overflow if lower = INT_MIN
        for (auto num : nums) {
            if (num == prev + 2) {
                ret.push_back(to_string(prev + 1));
            }
            if (num > prev + 2) {
                ret.push_back(to_string(prev + 1) + "->" + to_string(num - 1));
            }
            prev = num;
        }
        if (prev + 1 == upper) ret.push_back(to_string(upper));
        if (prev + 1 < upper) ret.push_back(to_string(prev + 1) + "->" + to_string(upper));  // overflow if prev = INT_MAX
        return ret;
    }
};
