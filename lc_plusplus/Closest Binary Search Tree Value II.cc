// Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.
//
// Note:
// Given target value is a floating point.
// You may assume k is always valid, that is: k ≤ total nodes.
// You are guaranteed to have only one unique set of k values in the BST that are closest to the target.


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
    vector<int> closestKValues(TreeNode* root, double target, int k) {
        vector<int> values;
        double d = abs(target - root->val);
        auto closest = root;
        for (auto cur = root; cur != nullptr;) {
            if (abs(cur->val - target) < d) {
                d = abs(cur->val - target);
                closest = cur;
            }
            if (cur->val > target) cur = cur->left;
            else cur = cur->right;
        }
        for (auto pre = closest, suc = getSuc(root, closest); k > 0; k--) {
            if (pre == nullptr) {
                values.push_back(suc->val);
                suc = getSuc(root, suc);
            } else if (suc == nullptr) {
                values.push_back(pre->val);
                pre = getPre(root, pre);
            } else if (abs(pre->val - target) < abs(suc->val - target)) {
                values.push_back(pre->val);
                pre = getPre(root, pre);
            } else {
                values.push_back(suc->val);
                suc = getSuc(root, suc);
            }
        }
        return values;
    }

    TreeNode* getPre(TreeNode* root, TreeNode* node) {
        TreeNode* pre = nullptr;
        if (node->left != nullptr) {
            pre = node->left;
            while (pre->right != nullptr) pre = pre->right;
        } else {
            while (root != node) {
                if (root->val <= node->val) {
                    pre = root;
                    root = root->right;
                } else root = root->left;
            }
        }
        return pre;
    }

    TreeNode* getSuc(TreeNode* root, TreeNode* node) {
        TreeNode* suc = nullptr;
        if (node->right != nullptr) {
            suc = node->right;
            while (suc->left != nullptr) suc = suc->left;
        } else {
            while (root != node) {
                if (root->val > node->val) {
                    suc = root;
                    root = root->left;
                } else root = root->right;
            }
        }
        return suc;
    }
};
