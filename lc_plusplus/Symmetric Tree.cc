// Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
//
// For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
//
//     1
//    / \
//   2   2
//  / \ / \
// 3  4 4  3
// But the following [1,2,2,null,3,null,3] is not:
//     1
//    / \
//   2   2
//    \   \
//    3    3
// Note:
// Bonus points if you could solve it both recursively and iteratively.


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
    bool isSymmetric(TreeNode* root) {
        if (root == nullptr) return true;
        queue<TreeNode*> q;
        q.push(root->left); q.push(root->right);
        while (!q.empty()) {
            auto l = q.front(); q.pop();
            auto r = q.front(); q.pop();
            if (l == nullptr ^ r == nullptr) return false;
            if (l != nullptr && r != nullptr) {
                if (l->val != r->val) return false;
                q.push(l->left); q.push(r->right);
                q.push(l->right); q.push(r->left);
            }
        }
        return true;
    }

    bool isSymmetricDFS(TreeNode* root) {
        if (root == nullptr) return true;
        return isSym(root->left, root->right);
    }

    bool isSym(TreeNode* l, TreeNode* r) {
        if (l == nullptr && r == nullptr) return true;
        if (l == nullptr ^ r == nullptr) return false;
        return l->val == r->val && isSym(l->left, r->right) && isSym(l->right, r->left);
    }
};
