// A peak element is an element that is greater than its neighbors.
//
// Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
//
// The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
//
// You may imagine that num[-1] = num[n] = -∞.
//
// For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.


class Solution {
public:
    int findPeakElement(vector<int>& nums) {
        int n = nums.size();
        int l{0}, r{n - 1};
        while (l < r) {
            auto m = l + (r - l) / 2;
            if ((m == 0 || nums[m - 1] < nums[m]) && (m + 1 == n || nums[m + 1] < nums[m])) return m;
            if (m > 0 && nums[m] < nums[m - 1]) r = m - 1;
            else l = m + 1;
        }
        return l;  // return either l or r would work
    }
};
