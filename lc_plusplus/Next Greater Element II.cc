// Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.
//
// Example 1:
// Input: [1,2,1]
// Output: [2,-1,2]
// Explanation: The first 1's next greater number is 2;
// The number 2 can't find next greater number;
// The second 1's next greater number needs to search circularly, which is also 2.


class Solution {
public:
    // put all indexes into the stack, smaller index on the top. Then we start from end of the array look for the first element (index) in the stack which is greater than the current one. That one is guaranteed to be the Next Greater Element. Then put the current element (index) into the stack.
    vector<int> nextGreaterElements(vector<int>& nums) {
        int n = nums.size();
        vector<int> ret(n, -1);
        stack<int> idx;
        for (int i = n - 1; i >= 0; --i) idx.push(i);
        for (int i = n - 1; i >= 0; --i) {
            while (!idx.empty() && nums[idx.top()] <= nums[i]) idx.pop();
            if (!idx.empty()) ret[i] = nums[idx.top()];
            idx.push(i);
        }
        return ret;
    }
};
