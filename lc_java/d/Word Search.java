/**
 * Given a 2D board and a word, find if the word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are
 * those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 * For example,
 * Given board =
 * [
 * ["ABCE"],
 * ["SFCS"],
 * ["ADEE"]
 * ]
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 */

public class Solution {
  public boolean exist(char[][] board, String word) {
    if (word.isEmpty()) return true;
    int m = board.length;
    if (m == 0 || board[0].length == 0) return false;
    int n = board[0].length;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (search(board, i, j, word)) return true;
      }
    }
    return false;
  }

  static int[][] dirs = new int[][] {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

  boolean search(char[][] board, int i, int j, String word) {
    if (board[i][j] != word.charAt(0)) return false;
    else if (word.length() == 1) return true;
    else {
      board[i][j] = '\0'; // mark visited
      for (int[] dir : dirs) {
        int x = i + dir[0];
        int y = j + dir[1];
        if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && board[x][y] != '\0') {
          if (search(board, x, y, word.substring(1))) return true;
        }
      }
      board[i][j] = word.charAt(0);
    }
    return false;
  }
}