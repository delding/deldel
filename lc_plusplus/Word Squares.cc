// Given a set of words (without duplicates), find all word squares you can build from them.
//
// A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).
//
// For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.
//
// b a l l
// a r e a
// l e a d
// l a d y
// Note:
// There are at least 1 and at most 1000 words.
// All words will have the exact same length.
// Word length is at least 1 and at most 5.
// Each word contains only lowercase English alphabet a-z.
// Example 1:
//
// Input:
// ["area","lead","wall","lady","ball"]
//
// Output:
// [
//   [ "wall",
//     "area",
//     "lead",
//     "lady"
//   ],
//   [ "ball",
//     "area",
//     "lead",
//     "lady"
//   ]
// ]
//
// Explanation:
// The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
// Example 2:
//
// Input:
// ["abat","baba","atan","atal"]
//
// Output:
// [
//   [ "baba",
//     "abat",
//     "baba",
//     "atan"
//   ],
//   [ "baba",
//     "abat",
//     "baba",
//     "atal"
//   ]
// ]
//
// Explanation:
// The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).


class Solution {
public:
    struct TrieNode {
        TrieNode* children[26];
        string word;  // can store index of word to save space
        TrieNode() : children{} {}
    };

    void insert(const string& word, TrieNode* root) {
        for (auto c : word) {
            if (root->children[c - 'a'] == nullptr) {
                root->children[c - 'a'] = new TrieNode();
            }
            root = root->children[c - 'a'];
        }
        root->word = word;
    }

    vector<string> find(const string& prefix, TrieNode* root) {
        vector<string> res;
        for (auto c : prefix) {
            if (root->children[c - 'a'] == nullptr) {
                return res;
            }
            root = root->children[c - 'a'];
        }
        findAll(root, res);
        return res;
    }

    void findAll(TrieNode* root, vector<string>& res) {
        if (root->word != "") {
            res.push_back(root->word);
        }
        for (auto p : root->children) {
            if (p != nullptr) {
                findAll(p, res);
            }
        }
    }

    vector<vector<string>> wordSquares(vector<string>& words) {
        auto root = new TrieNode();
        for (auto w : words) {
            insert(w, root);
        }
        vector<vector<string>> res;
        vector<string> square;
        for (auto& w : words) {
            square.push_back(w);
            collectAll(root, res, square, words[0].size());
            square.pop_back();
        }
        return res;
    }

    void collectAll(TrieNode* root, vector<vector<string>>& res, vector<string>& square, int wordLen) {
        if (wordLen == square.size()) {
            res.push_back(square);
        } else {
            auto col = square.size();
            string prefix;
            for (auto& w : square) {
                prefix += w[col];
            }
            for (auto& w : find(prefix, root)) {
                square.push_back(w);
                collectAll(root, res, square, wordLen);
                square.pop_back();
            }
        }
    }
};
