// Write a program to solve a Sudoku puzzle by filling the empty cells.
//
// Empty cells are indicated by the character '.'.
//
// You may assume that there will be only one unique solution.


class Solution {
public:
    void solveSudoku(vector<vector<char>>& board) {
        solve(board, 0, 0);
    }

    bool solve(vector<vector<char>>& b, int i, int j) {
        if (i == 8 && j == 9) return true;
        if (j == 9) return solve(b, i + 1, 0);
        if (b[i][j] != '.') return solve(b, i, j + 1);
        auto n = numbers(i, j, b);
        for (int k = 0; k < 9; ++k) {
            if (n[k]) {
                b[i][j] = '1' + k;
                if (solve(b, i, j + 1)) return true;
                b[i][j] = '.';
            }
        }
        return false;
    }

    vector<bool> numbers(int i, int j, vector<vector<char>>& b) {
        vector<bool> n(9, true);
        for (auto k = 0; k < 9; ++k) {
            if (b[i][k] != '.') n[b[i][k] - '1'] = false;
            if (b[k][j] != '.') n[b[k][j] - '1'] = false;
        }
        for (int k = i / 3 * 3; k < i / 3 * 3 + 3; ++k) {
            for (int l = j / 3 * 3; l < j / 3 * 3 + 3; ++l) {
                if (b[k][l] != '.') n[b[k][l] - '1'] = false;
            }
        }
        return n;
    }
};
