// Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
//
// For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
//
//
// Example:
//
// Given the sorted array: [-10,-3,0,5,9],
//
// One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
//
//       0
//      / \
//    -3   9
//    /   /
//  -10  5


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
    TreeNode* sortedArrayToBST(vector<int>& nums) {
        if (nums.empty()) return nullptr;
        return bst(nums, 0, nums.size() - 1);
    }

    TreeNode* bst(vector<int>& nums, int l, int r) {
        int m = (l + r) / 2;
        auto n = new TreeNode(nums[m]);
        if (m - 1 >= l) n->left = bst(nums, l, m - 1);
        if (m + 1 <= r) n->right = bst(nums, m + 1, r);
        return n;
    }
};
