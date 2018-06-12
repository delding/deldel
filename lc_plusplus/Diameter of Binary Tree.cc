// Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
//
// Example:
// Given a binary tree
//           1
//          / \
//         2   3
//        / \
//       4   5
// Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
//
// Note: The length of path between two nodes is represented by the number of edges between them.
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
    int diameterOfBinaryTree(TreeNode* root) {
        int numNodes{0};
        diameter(root, numNodes);
        return numNodes == 0 ? 0 : numNodes - 1; // length = num of nodes - 1
    }

    int diameter(TreeNode* n, int& d) {
        if (n == nullptr) return 0;
        auto leftNodes = diameter(n->left, d);
        auto rightNodes = diameter(n->right, d);
        d = max(d, 1 + leftNodes + rightNodes);
        return 1 + max(leftNodes, rightNodes);
    }
};
