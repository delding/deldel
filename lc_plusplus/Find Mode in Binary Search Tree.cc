// Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.
//
// Assume a BST is defined as follows:
//
// The left subtree of a node contains only nodes with keys less than or equal to the node's key.
// The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
// Both the left and right subtrees must also be binary search trees.
// For example:
// Given BST [1,null,2,2],
//    1
//     \
//      2
//     /
//    2
// return [2].
//
// Note: If a tree has more than one mode, you can return them in any order.
//
// Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
//


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
    // in-order traversal since all duplicates are consecutive in sorted order
    vector<int> findMode(TreeNode* root) {
        vector<int> modes;
        if (root == nullptr) return modes;
        int freq{1}, maxFreq{1};
        int preVal = root->val + 1; // to guarantee preVal not equal to the smallest value in the bst
        findMaxFreq(root, preVal, freq, maxFreq); cout << maxFreq;
        preVal = root->val + 1;
        freq = 1;
        findMode(root, modes, preVal, freq, maxFreq);
        return modes;
    }

    void findMaxFreq(TreeNode* cur, int& preVal, int& freq, int& maxFreq) { // all preVal, freq, maxFreq must be references
        if (cur == nullptr) return;
        findMaxFreq(cur->left, preVal, freq, maxFreq);
        if (preVal == cur->val) ++freq;
        else freq = 1;
        maxFreq = max(maxFreq, freq);
        preVal = cur->val;
        findMaxFreq(cur->right, preVal, freq, maxFreq);
    }

    void findMode(TreeNode* cur, vector<int>& modes, int& preVal, int& freq, int maxFreq) {
        if (cur == nullptr) return;
        findMode(cur->left, modes, preVal, freq, maxFreq);
        if (preVal == cur->val) ++freq;
        else freq = 1;
        if (freq == maxFreq) modes.push_back(cur->val);
        preVal = cur->val;
        findMode(cur->right, modes, preVal, freq, maxFreq);
    }
};
