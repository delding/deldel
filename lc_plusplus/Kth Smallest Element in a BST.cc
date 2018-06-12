// Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
//
// Note:
// You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
//
// Follow up:
// What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?


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
     int kthSmallest(TreeNode* root, int k) {
         stack<TreeNode*> st;
         int val;
         while (k-- != 0) {
             for (auto cur = root; cur != nullptr; cur = cur->left) st.push(cur);
             auto& top = st.top();
             val = top->val;
             root = top->right;
             st.pop();
         }
         return val;
     }
 };
