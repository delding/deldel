// Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
//
// For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].


class Solution {
public:
    void wiggleSort(vector<int>& nums) {
      for (size_t i = 0; i + 1 < nums.size(); ++i) {
        if (i % 2 == 0) {
          if (nums[i] > nums[i + 1]) swap(nums[i], nums[i + 1]);
        } else {
          if (nums[i] < nums[i + 1]) swap(nums[i], nums[i + 1]);
        }
      }
    }
};
