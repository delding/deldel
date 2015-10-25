/**
 Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

 The Sudoku board could be partially filled, where empty cells are filled with the character '.'.


 A partially filled sudoku which is valid.

 Note:
 A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
 **/

public class Solution {
  public boolean isValidSudoku(char[][] board) {
    int m = board.length;
    int n = board[0].length;
    for (int i = 0; i < m; i++) {
      String digits = "";
      for (int j = 0; j < n; j++) {
        char c = board[i][j];
        if (c != '.') {
          if (digits.indexOf(c) != -1) {
            return false;
          } else digits += c;
        }
      }
    }
    for (int i = 0; i < m; i++) {
      String digits = "";
      for (int j = 0; j < n; j++) {
        char c = board[j][i];
        if (c != '.') {
          if (digits.indexOf(c) != -1) {
            return false;
          } else digits += c;
        }
      }
    }
    for (int i = 0; i < m; i = i + 3) {
      for (int j = 0; j < n; j = j + 3) {
        String digits = "";
        for (int k = 0; k < 3; k++) {
          for (int l = 0; l < 3; l++) {
            char c = board[i + k][j + l];
            if (c != '.') {
              if (digits.indexOf(c) != -1) {
                return false;
              } else digits += c;
            }
          }
        }
      }
    }
    return true;
  }
}