// Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
//
// (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
//
// You are given a target value to search. If found in the array return its index, otherwise return -1.
//
// You may assume no duplicate exists in the array.
//


class Solution {
public:
    int search(vector<int>& nums, int target) {
        for (int i = 0, j = static_cast<int>(nums.size()) - 1; i <= j;) {
            auto m = (i + j) / 2;
            if (target == nums[m]) return m;
            if (nums[m] >= nums[i]) {  // must >= not just > for the case of m == i
                if (target > nums[m] || target < nums[i]) i = m + 1;
                else j = m - 1;
            } else {
                if (target < nums[m] || target > nums[j]) j = m - 1;
                else i = m + 1;
            }
        }
        return -1;
    }
};
