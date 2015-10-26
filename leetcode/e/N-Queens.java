import java.lang.StringBuilder;

/**
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
 * <p>
 * For example,
 * There exist two distinct solutions to the 4-queens puzzle:
 * <p>
 * [
 * [".Q..",  // Solution 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * <p>
 * ["..Q.",  // Solution 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 **/

public class Solution {
  public List<List<String>> solveNQueens(int n) {
    List<List<String>> ret = new ArrayList<>();
    char[][] board = new char[n][n];
    for (int i = 0; i < board.length; i++) {
      Arrays.fill(board[i], '.');
    }
    dfs(board, ret, new ArrayList<String>(), 0);
    return ret;
  }

  void dfs(char[][] board, List<List<String>> ret, List<String> cur, int line) {
    if (line == board.length) ret.add(new ArrayList<String>(cur));
    else {
      for (int i = 0; i < board.length; i++) {
        if (board[line][i] != 'Q') {
          if (isValid(line, i, board)) {
            StringBuilder sb = new StringBuilder();
            int k = 0;
            while (k++ < i) sb.append('.');
            sb.append('Q');
            while (sb.length() < board.length) sb.append('.');
            cur.add(sb.toString());
            board[line][i] = 'Q';
            dfs(board, ret, cur, line + 1);
            board[line][i] = '.';
            cur.remove(cur.size() - 1);
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