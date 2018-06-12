// Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.
//
// Two trees are duplicate if they have the same structure with same node values.
//
// Example 1:
//         1
//        / \
//       2   3
//      /   / \
//     4   2   4
//        /
//       4
// The following are two duplicate subtrees:
//       2
//      /
//     4
// and
//     4
// Therefore, you need to return above trees' root in the form of a list.


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
    vector<TreeNode*> findDuplicateSubtrees(TreeNode* root) {
        vector<TreeNode*> dups;
        unordered_map<string, vector<TreeNode*>> umap;
        serialize(root, umap);
        for (auto& e : umap) {
            if (e.second.size() > 1) dups.push_back(e.second.front());
        }
        return dups;
    }

    string serialize(TreeNode* cur, unordered_map<string, vector<TreeNode*>>& umap) {
        if (cur == nullptr) return "*,";
        auto s = to_string(cur->val) + "," + serialize(cur->left, umap) + serialize(cur->right, umap);
        umap[s].push_back(cur);
        return s;
    }
};
