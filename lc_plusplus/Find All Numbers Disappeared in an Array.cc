// Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
//
// Find all the elements of [1, n] inclusive that do not appear in this array.
//
// Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
//
// Example:
//
// Input:
// [4,3,2,7,8,2,3,1]
//
// Output:
// [5,6]


class Solution {
public:
    vector<int> findDisappearedNumbers(vector<int>& nums) {
        for (auto i = 0; i < nums.size(); ++i) {
            while (i + 1 != nums[i] && nums[i] != nums[nums[i] - 1]) {
                swap(nums[i], nums[nums[i] - 1]);
            }
        }
        vector<int> res;
        for (auto i = 0; i < nums.size(); ++i) {
            if (i +1 != nums[i]) res.push_back(i + 1);
        }
        return res;
    }
};

class Solution2 {
public:
    vector<int> findDisappearedNumbers(vector<int>& nums) {
        for (auto n : nums) {
            int slot = abs(n) - 1;
            if (nums[slot] > 0) nums[slot] *= -1;  // flip to negative as a mark of presense
        }
        vector<int> missing;
        for (int i = 0; i < nums.size(); ++i) {
            if (nums[i] > 0) missing.push_back(i + 1);
        }
        return missing;
    }
};
