// Given a binary tree, find the maximum path sum.
//
// For this problem, a path is defined as any sequence of nodes from some starting
// node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.
//
// For example:
// Given the below binary tree,
//
//        1
//       / \
//      2   3
// Return 6.


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
    int maxPathSum(TreeNode* root) {
        int maxPath = numeric_limits<int>::min();
        dfs(root, maxPath);
        return maxPath;
    }

    int dfs(TreeNode* curr, int& maxPath) {
        if (curr == nullptr) return 0;
        auto leftMax = dfs(curr->left, maxPath);
        auto rightMax = dfs(curr->right, maxPath);
        auto ret = curr->val + max(0, max(leftMax, rightMax));
        auto localMax = ret + max(0, min(leftMax, rightMax));
        maxPath = max(maxPath, localMax);
        return ret;
    }
};
