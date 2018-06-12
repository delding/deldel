// Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
//
// If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
//
// The replacement must be in-place, do not allocate extra memory.
//
// Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
// 1,2,3 → 1,3,2
// 3,2,1 → 1,2,3
// 1,1,5 → 1,5,1


class Solution {
public:
    void nextPermutation(vector<int>& nums) {
        auto lit = nums.end();
        for (auto it = nums.end() - 1; it > nums.begin(); --it) {
            if (*it > *(it - 1)) {
                lit = it - 1;
                break;
            }
        }
        if (lit == nums.end()) {
            reverse(nums.begin(), nums.end());
        } else {
            for (auto it = nums.end() - 1; it > lit; --it) {
                if (*it > *lit) {
                    swap(*it, *lit);
                    break;
                }
            }
            reverse(lit + 1, nums.end());
        }
    }
};
