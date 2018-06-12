// Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.
//
// Example:
//
// Input:
//
//    1
//     \
//      3
//     /
//    2
//
// Output:
// 1
//
// Explanation:
// The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).


/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    int getMinimumDifference(TreeNode* root) {
        int minval = INT_MAX, pre = -1;
        getMinval(root, pre, minval);
        return minval;
    }

    void getMinval(TreeNode* curr, int& pre, int& val) {
        if (curr->left != nullptr) getMinval(curr->left, pre, val);
        if (pre != -1) val = min(val, curr->val - pre);
        pre = curr->val;
        if (curr->right != nullptr) getMinval(curr->right, pre, val);
    }

    // bug
    int getMinimumDifferenceBug(TreeNode* root) {
        int minval = INT_MAX;
        getMinval(root, nullptr, minval);
        return minval;
    }

    void getMinval(TreeNode* curr, TreeNode* pre, int& val) {
        if (curr->left != nullptr) getMinval(curr->left, pre, val);
        if (pre != nullptr) val = min(val, curr->val - pre->val);
        *pre = *curr; // this won't work as pre is nullptr, memory is not allocated and can't deference
        if (curr->right != nullptr) getMinval(curr->right, pre, val);
    }
};
