// Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

// For example,
// Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.


class Solution {
public:
    int trap(vector<int>& height) {
        int water{0};
        for (size_t l = 0, r = height.size(); l + 2 < r;) {
            if (height[l] <= height[r - 1]) {
                water += max(0, height[l] - height[l + 1]);
                height[l + 1] = max(height[l], height[l + 1]);
                ++l;
            } else {
                water += max(0, height[r - 1] - height[r - 2]);
                height[r - 2] = max(height[r - 1], height[r - 2]);
                --r;
            }
        }
        return water;
    }
};



class Solution {
public:
    int trap(vector<int>& height) {
        int water{0};
        for (int l{0}, r{height.size() - 1}; l + 1 < r;) {
            if (height[l] < height[r]) {
                water += max(0, height[l] - height[l + 1]);
                height[l + 1] = max(height[l], height[l + 1]);
                ++l;
            } else {
                water += max(0, height[r] - height[r - 1]);
                height[r - 1] = max(height[r], height[r - 1]);
                --r;
            }
        }
        return water;
    }
};
