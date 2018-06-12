// Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.
//
// Note: The length of path between two nodes is represented by the number of edges between them.
//
// Example 1:
//
// Input:
//
//               5
//              / \
//             4   5
//            / \   \
//           1   1   5
// Output:
//
// 2
// Example 2:
//
// Input:
//
//               1
//              / \
//             4   5
//            / \   \
//           4   4   5
// Output:
//
// 2


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
    int longestUnivaluePath(TreeNode* root) {
        int nodes = 0;
        longestNodes(root, nodes);
        return max(0, nodes - 1);  // path = 0 if nodes = 0 or 1
    }

    int longestNodes(TreeNode* cur, int& nodes) {
        if (cur == nullptr) return 0;
        int n = 1;
        int ret = 1;
        auto lNodes = longestNodes(cur->left, nodes);
        if (cur->left != nullptr && cur->left->val == cur->val) {
            ret = max(ret, 1 + lNodes);
            n += lNodes;
        }
        auto rNodes = longestNodes(cur->right, nodes);
        if (cur->right != nullptr && cur->right->val == cur->val) {
            ret = max(ret, 1+ rNodes);
            n += rNodes;
        }
        nodes = max(n, nodes);
        return ret;
    }
};
