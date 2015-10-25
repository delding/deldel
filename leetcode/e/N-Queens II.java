/**
 Follow up for N-Queens problem.

 Now, instead outputting board configurations, return the total number of distinct solutions.
 **/

public class Solution {
  int count = 0;
  public int totalNQueens(int n) {
    char[][] board = new char[n][n];
    for (int i = 0; i < board.length; i++) {
      Arrays.fill(board[i], '.');
    }
    dfs(board, 0);
    return count;
  }

  void dfs(char[][] board, int line) {
    if (line == board.length) count++;
    else {
      for (int i = 0; i < board.length; i++) {
        if (board[line][i] != 'Q') {
          if (isValid(line, i, board)) {
            board[line][i] = 'Q';
            dfs(board, line + 1);
            board[line][i] = '.';
          }
        }
      }
    }
  }

  boolean isValid(int i, int j, char[][] board) {
    int[][] dirs = {
        {0, -1}, {0, 1}, {-1, 0}, {1, 0}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
    };
    for (int[] dir : dirs) {
      for (int d = 1; d < board.length; d++) {
        int x = i + d * dir[0];
        int y = j + d * dir[1];
        if (x >= 0 && x < board.length && y >= 0 && y < board.length) {
          if (board[x][y] == 'Q') return false;
        }
      }
    }
    return true;
  }
}