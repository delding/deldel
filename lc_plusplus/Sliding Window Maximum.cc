// Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
//
// For example,
// Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
//
// Window position                Max
// ---------------               -----
// [1  3  -1] -3  5  3  6  7       3
//  1 [3  -1  -3] 5  3  6  7       3
//  1  3 [-1  -3  5] 3  6  7       5
//  1  3  -1 [-3  5  3] 6  7       5
//  1  3  -1  -3 [5  3  6] 7       6
//  1  3  -1  -3  5 [3  6  7]      7
// Therefore, return the max sliding window as [3,3,5,5,6,7].
//
// Note:
// You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.
//


class Solution {
public:
    vector<int> maxSlidingWindow(vector<int>& nums, int k) {
        vector<int> ret;
        if (k == 0) return ret;
        deque<int> window;
        for (int i{0}; i < nums.size(); ++i) {
            while (!window.empty() && nums[window.back()] <= nums[i]) window.pop_back();
            while (!window.empty() && i - window.front() >= k) window.pop_front();
            window.push_back(i);
            if (i >= k - 1) ret.push_back(nums[window.front()]);
        }
        return ret;
    }
};
