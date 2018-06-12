// You have 4 cards each containing a number from 1 to 9. You need to judge whether
// they could operated through *, /, +, -, (, ) to get the value of 24.
//
// Example 1:
// Input: [4, 1, 8, 7]
// Output: True
// Explanation: (8-4) * (7-1) = 24
// Example 2:
// Input: [1, 2, 1, 2]
// Output: False
// Note:
// The division operator / represents real division, not integer division. For example, 4 / (1 - 2/3) = 12.
// Every operation done is between two numbers. In particular, we cannot use - as a unary operator. For example, with [1, 1, 1, 1] as input, the expression -1 - 1 - 1 - 1 is not allowed.
// You cannot concatenate numbers together. For example, if the input is [1, 2, 1, 2], we cannot write this as 12 + 12.


class Solution {
public:
    bool judgePoint24(vector<int>& nums) {
        vector<double> currRound;
        for (auto num : nums) {
            currRound.push_back(num);
        }
        return judge(currRound);
    }

    bool judge(vector<double>& nums) {
        if (nums.size() == 1) {
            return abs(nums[0] - 24) < 0.001;
        }
        for (auto i = 0; i < nums.size(); ++i) {
            for (auto j = 0; j < nums.size(); ++j) {
                if (j == i) continue;
                vector<double> nextRound;
                for (auto k = 0; k < nums.size(); ++k) {
                    if (k != i && k != j) {
                        nextRound.push_back(nums[k]);
                    }
                }
                nextRound.push_back(nums[i] + nums[j]);
                if (judge(nextRound)) {
                    return true;
                }
                *nextRound.rbegin() = nums[i] - nums[j];
                if (judge(nextRound)) {
                    return true;
                }
                *nextRound.rbegin() = nums[i] * nums[j];
                if (judge(nextRound)) {
                    return true;
                }
                if (nums[j] != 0) {
                    *nextRound.rbegin() = nums[i] / nums[j];
                    if (judge(nextRound)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
};
