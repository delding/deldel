// Given a binary tree, find the length of the longest consecutive sequence path.
//
// The path refers to any sequence of nodes from some starting node to any node
// in the tree along the parent-child connections. The longest consecutive path
// need to be from parent to child (cannot be the reverse).
//
// For example,
//    1
//     \
//      3
//     / \
//    2   4
//         \
//          5
// Longest consecutive sequence path is 3-4-5, so return 3.
//    2
//     \
//      3
//     /
//    2
//   /
//  1
// Longest consecutive sequence path is 2-3,not3-2-1, so return 2.


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
    int longestConsecutive(TreeNode* root) {
        int longest = 0;
        longestConsecutive(root, longest);
        return longest;
    }

    int longestConsecutive(TreeNode* root, int& longest) {
        if (root == nullptr) return 0;
        int len = 0;
        auto left = longestConsecutive(root->left, longest);
        auto right = longestConsecutive(root->right, longest);
        if (root->left != nullptr && root->left->val - 1 == root->val) len = max(len, left);
        if (root->right != nullptr && root->right->val - 1 == root->val) len = max(len, right);
        longest = max(longest, len + 1);
        return len + 1;
    }
};
