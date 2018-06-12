// Given a sorted array of integers nums and integer values a, b and c. Apply a
// quadratic function of the form f(x) = ax2 + bx + c to each element x in the array.
//
// The returned array must be in sorted order.
//
// Expected time complexity: O(n)
//
// Example:
// nums = [-4, -2, 2, 4], a = 1, b = 3, c = 5,
//
// Result: [3, 9, 15, 33]
//
// nums = [-4, -2, 2, 4], a = -1, b = 3, c = 5
//
// Result: [-23, -5, 1, 7]


class Solution {
public:
    vector<int> sortTransformedArray(vector<int>& nums, int a, int b, int c) {
        vector<int> res(nums.size());
        auto fun = [=](int x){return a * x * x + b * x + c;};
        if (a >= 0) {
            for (int i{nums.size() - 1}, l{0}, r{nums.size() - 1}; i >=0; --i) {
                if (fun(nums[l]) > fun(nums[r])) res[i] = fun(nums[l++]);
                else res[i] = fun(nums[r--]);
            }
        } else {
            for (int i{0}, l{0}, r{nums.size() - 1}; i < nums.size(); ++i) {
                if (fun(nums[l]) < fun(nums[r])) res[i] = fun(nums[l++]);
                else res[i] = fun(nums[r--]);
            }
        }
        return res;
    }
};
