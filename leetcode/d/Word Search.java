/**
 * Given a 2D board and a word, find if the word exists in the grid.
 * <p>
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 * <p>
 * For example,
 * Given board =
 * <p>
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
    if (board.length == 0 || board[0].length == 0) return false;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        if (exist(board, word, i, j)) return true;
      }
    }
    return false;
  }

  private boolean exist(char[][] board, String word, int i, int j) {
    if (word.length() == 1) { // ERROR: don't use length == 0 to return true, it can be last char match but no where to go on the board and thus no further call for empty string
      return board[i][j] == word.charAt(0);
    }
    if (board[i][j] != word.charAt(0)) return false;
    board[i][j] = '*';
    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    for (int[] dir : dirs) {
      int x = i + dir[0];
      int y = j + dir[1];
      if (x >= 0 && x < board.length && y >= 0 && y < board[0].length) {
        if (exist(board, word.substring(1), x, y)) return true;
      }
    }
    board[i][j] = word.charAt(0);
    return false;
  }
}
