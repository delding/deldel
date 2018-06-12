// Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
//
// The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
//
//
// A partially filled sudoku which is valid.
//
// Note:
// A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.


class Solution {
public:
    bool isValidSudoku(vector<vector<char>>& board) {
        for (int i = 0; i < 9; ++i) {
            bool row[9]{};
            bool col[9]{};
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] != '.') {
                    if (row[board[i][j] - '1']) return false;
                    row[board[i][j] - '1'] = true;
                }
                if (board[j][i] != '.') {
                    if (col[board[j][i] - '1']) return false;
                    col[board[j][i] - '1'] = true;
                }
            }
        }
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                bool square[9]{};
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        if (board[i + k][j + l] != '.') {
                            if (square[board[i + k][j + l] - '1']) return false;
                            square[board[i + k][j + l] - '1'] = true;
                        }
                    }
                }
            }
        }
        return true;
    }
};
