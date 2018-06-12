// Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
//
// Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
//
// For example, you may serialize the following tree
//
//     1
//    / \
//   2   3
//      / \
//     4   5
// as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
// Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.


/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Codec {
public:

    // Encodes a tree to a single string.
    // iterative level order
    string serialize1(TreeNode* root) {
        string s;
        queue<TreeNode*> q;
        q.push(root);
        while (!q.empty()) {
            auto n = q.front();
            q.pop();
            if (n == nullptr) s += "#,";
            else {
                s += to_string(n->val) + ",";
                q.push(n->left);  // for (auto c : n->children) push(c);
                q.push(n->right);
            }
        }
        return s;
    }

    // Decodes your encoded data to tree.
    TreeNode* deserialize1(string data) {
        stringstream ss{data};
        string val;
        getline(ss, val, ',');
        if (val == "#") return nullptr;
        TreeNode* root = new TreeNode{stoi(val)};
        queue<TreeNode*> q;
        q.push(root);
        while (!q.empty()) {
            auto n = q.front();
            q.pop();
            getline(ss, val, ',');
            if (val != "#") {
                n->left = new TreeNode{stoi(val)};
                q.push(n->left);
            }
            getline(ss, val, ',');
            if (val != "#") {
                n->right = new TreeNode{stoi(val)};
                q.push(n->right);
            }
        }
        return root;
    }

    // recursive, preorder traversal
    string serialize(TreeNode* root) {
        if (!root) {
            return "#,";
        }
        return to_string(root->val) + "," + serialize(root->left) + serialize(root->right);
    }

    TreeNode* deserialize(string data) {
        stringstream ss{data};
        return deserialize(ss);
    }

    TreeNode* deserialize(stringstream& ss) {
        string val;
        getline(ss, val, ',');
        if (val == "#") return nullptr;
        TreeNode* n = new TreeNode(stoi(val));
        n->left = deserialize(ss);
        n->right = deserialize(ss);
        return n;
    }

    struct NaryNode {
        int val;
        vector<NaryNode*> children;
        NaryNode(int v) : val{v} {}
    };

    string serializeNaryTree(NaryNode* root) {
        if (root == nullptr) return "";
        string s;
        s += "(" + to_string(root->val);
        for (auto c : root->children) s += serializeNaryTree(c);
        s += ")";
        return s;
    }

    NaryNode* deserializeNaryTree(const string& s, size_t& i) {
        if (s.size() == i) return nullptr;
        i++; // skip '('
        auto j = min(s.find(")", i), s.find("(", i));
        auto val = stoi(s.substr(i, j - i));
        auto root = new NaryNode(val);
        i = j;
        while (s[i] != ')') {
            root->children.push_back(deserializeNaryTree(s, i));
        }
        i++; // skip ')'
        return root;
    }

    NaryNode* deserializeNaryTree(string in)  {
		 NaryNode* root = nullptr;
		 NaryNode* parent = nullptr;
		 stack<NaryNode*> st;
		 int pos = 0;
		 while  (pos < in.size()) {
			 if (in[pos] == '(')  {
                pos ++;
				auto node = new NaryNode(in[pos] - '0');
				if (parent == nullptr)
					root = node;
				else
					parent->children.push_back(node);
				st.push(node) ;
				parent = node;
			 }
			 else  if (in[pos] == ')')  {
				 st.pop();
				 if (!st.empty())
					 parent = st.top();
			 }
			 pos++;
		 }
		 return root;
    }
};
