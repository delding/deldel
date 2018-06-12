// Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
//
// For example:
// Given binary tree [3,9,20,null,null,15,7],
//     3
//    / \
//   9  20
//     /  \
//    15   7
// return its level order traversal as:
// [
//   [3],
//   [9,20],
//   [15,7]
// ]


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
    vector<vector<int>> levelOrder(TreeNode* root) {
        vector<vector<int>> ret;
        dfs(ret, root, 0);
        return ret;
    }

    void dfs(vector<vector<int>>& ret, TreeNode* n, int dep) {
        if (n == nullptr) return;
        if (dep == ret.size()) ret.push_back(vector<int>{});
        ret[dep].push_back(n->val);
        dfs(ret, n->left, dep + 1);
        dfs(ret, n->right, dep + 1);
    }

    vector<vector<int>> levelOrderBFS(TreeNode* root) {
        vector<vector<int>> ret;
        if (root == nullptr) return ret;
        queue<TreeNode*> q;
        q.push(root);
        while (!q.empty()) {
            vector<int> l;
            for (auto s = q.size(); s > 0; --s) {
                auto n = q.front(); q.pop();
                l.push_back(n->val);
                if (n->left != nullptr) q.push(n->left);
                if (n->right != nullptr) q.push(n->right);
            }
            ret.push_back(l);
        }
        return ret;
    }
};
