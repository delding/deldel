// Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
//
// For example,
// Given [100, 4, 200, 1, 3, 2],
// The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
//
// Your algorithm should run in O(n) complexity.
//


class Solution {
public:
    int longestConsecutive(vector<int>& nums) {
        unordered_multiset<int> ums{nums.begin(), nums.end()};
        int longest{0};
        for (size_t i = 0; i < nums.size(); ++i) {
            auto it = ums.find(nums[i]);
            if (it == ums.end()) continue;
            int len{1};
            for (int inc{*it + 1}; ums.find(inc) != ums.end(); ++inc) {
                ++len;
                ums.erase(ums.find(inc));
            }
            for (int dec{*it - 1}; ums.find(dec) != ums.end(); --dec) {
                ++len;
                ums.erase(ums.find(dec));
            }
            ums.erase(it);
            longest = max(longest, len);
        }
        return longest;
    }
};
