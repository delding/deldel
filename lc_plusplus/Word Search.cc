// Given a 2D board and a word, find if the word exists in the grid.
//
// The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
//
// For example,
// Given board =
//
// [
//   ['A','B','C','E'],
//   ['S','F','C','S'],
//   ['A','D','E','E']
// ]
// word = "ABCCED", -> returns true,
// word = "SEE", -> returns true,
// word = "ABCB", -> returns false.


class Solution {
public:
    bool exist(vector<vector<char>>& board, string word) {
        for (int i = 0; i < static_cast<int>(board.size()); ++i) {
            for (int j = 0; j < static_cast<int>(board[0].size()); ++j) {
                if (exist(board, word, 0, i, j)) return true;
            }
        }
        return false;
    }

    bool exist(vector<vector<char>>& board, string& word, int idx, int i, int j) {
        if (word[idx] != board[i][j]) return false;
        if (idx == word.size() - 1) return true;
        board[i][j] = '*';
        pair<int, int> dirs[] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (auto& d : dirs) {
            auto x = i + d.first;
            auto y = j + d.second;
            if (x >= 0 && x < board.size() && y >= 0 && y < board[0].size() && board[x][y] != '*') {
                if (exist(board, word, idx + 1, x, y)) {
                    board[i][j] = word[idx];
                    return true;
                }
            }
        }
        board[i][j] = word[idx];
        return false;
    }
};
