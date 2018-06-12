// Implement an iterator over a binary search tree (BST). Your iterator will be
// initialized with the root node of a BST.
//
// Calling next() will return the next smallest number in the BST.
//
// Note: next() and hasNext() should run in average O(1) time and uses O(h)
// memory, where h is the height of the tree.
//


/**
 * Definition for binary tree
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class BSTIterator {
    stack<TreeNode*> st;
    TreeNode* cur;
public:
    BSTIterator(TreeNode *root) : cur{root} {

    }

    /** @return whether we have a next smallest number */
    bool hasNext() {
        return cur != nullptr || !st.empty();
    }

    /** @return the next smallest number */
    int next() {
        for (; cur != nullptr; cur = cur->left) st.push(cur);
        auto n = st.top();
        st.pop();
        cur = n->right;
        return n->val;
    }
};

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = BSTIterator(root);
 * while (i.hasNext()) cout << i.next();
 */
