// Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.
//
// Your algorithm's runtime complexity must be in the order of O(log n).
//
// If the target is not found in the array, return [-1, -1].
//
// For example,
// Given [5, 7, 7, 8, 8, 10] and target value 8,
// return [3, 4].


class Solution {
public:
    vector<int> searchRange(vector<int>& nums, int target) {
        vector<int> res(2);
        int s = nums.size();
        int l = 0;
        int r = s - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (target <= nums[m]) r = m - 1;
            else l = m + 1;
        }
        if (l == s || nums[l] != target) return vector<int>{-1, -1};
        res[0] = l;
        l = 0;
        r = s - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (target >= nums[m]) l = m + 1;
            else r = m - 1;
        }
        res[1] = r;
        return res;
    }
};
