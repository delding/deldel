// Given a 2D board and a list of words from the dictionary, find all words in the board.
//
// Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
//
// For example,
// Given words = ["oath","pea","eat","rain"] and board =
//
// [
//   ['o','a','a','n'],
//   ['e','t','a','e'],
//   ['i','h','k','r'],
//   ['i','f','l','v']
// ]
// Return ["eat","oath"].
// Note:
// You may assume that all inputs are consist of lowercase letters a-z.


class Solution {
    struct Node {
        Node* ch[26];
        string word;
        Node() : ch{} {}
    };

public:
    vector<string> findWords(vector<vector<char>>& board, vector<string>& words) {
        auto root = new Node{};
        for (auto& w : words) {
            auto cur = root;
            for (auto c : w) {
                if (cur->ch[c - 'a'] == nullptr) cur->ch[c - 'a'] = new Node{};
                cur = cur->ch[c - 'a'];
            }
            cur->word = w;
        }
        vector<string> ret;
        for (int i = 0; i < board.size(); ++i) {
            for (int j = 0; j < board[0].size(); ++j) {
                find(i, j, board, root, ret);
            }
        }
        return ret;
    }

    void find(int i, int j, vector<vector<char>>& board, Node* n, vector<string>& ret) {
        auto c = board[i][j];
        board[i][j] = '*';
        if ((n = n->ch[c - 'a']) != nullptr) {
            if (!n->word.empty()) {
                ret.push_back(n->word);
                n->word = "";  // dedup
            }
            for (auto dir : vector<pair<int, int>>{{0, 1}, {0, -1}, {1, 0}, {-1, 0}}) {
                auto x = i + dir.first;
                auto y = j + dir.second;
                if (x >= 0 && x < board.size() && y >= 0 && y < board[0].size() && board[x][y] != '*') {
                    find(x, y, board, n, ret);
                }
            }
        }
        board[i][j] = c;
    }
};
