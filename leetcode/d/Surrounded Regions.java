/**
 * Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
 * <p>
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * <p>
 * For example,
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * After running your function, the board should be:
 * <p>
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 */

public class Solution {
  public void solve(char[][] board) {
    int m = board.length;
    if (m == 0) return;
    int n = board[0].length;
    for (int i = 0; i < m; i++) {
      if (board[i][0] == 'O') bfs(board, i, 0);
    }
    for (int i = 0; i < m; i++) {
      if (board[i][n - 1] == 'O') bfs(board, i, n - 1);
    }
    for (int i = 0; i < n; i++) {
      if (board[0][i] == 'O') bfs(board, 0, i);
    }
    for (int i = 0; i < n; i++) {
      if (board[m - 1][i] == 'O') bfs(board, m - 1, i);
    }
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (board[i][j] == 'O') board[i][j] = 'X';
        if (board[i][j] == '*') board[i][j] = 'O';
      }
    }
  }

  // flip all 'O' that are connected to boundaries to '*'
  private void dfs(char[][] board, int i, int j) {
    board[i][j] = '*';
    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    for (int[] dir : dirs) {
      int x = i + dir[0];
      int y = j + dir[1];
      if (0 <= x && x < board.length && 0 <= y && y < board[0].length && board[x][y] == 'O') {
        dfs(board, x, y);
      }
    }
  }

  private void bfs(char[][] board, int i, int j) {
    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    board[i][j] = '*';
    Queue<int[]> q = new LinkedList<int[]>();
    q.add(new int[]{i, j});
    while (!q.isEmpty()) {
      int[] pos = q.poll();
      for (int[] dir : dirs) {
        int x = pos[0] + dir[0];
        int y = pos[1] + dir[1];
        if (0 <= x && x < board.length && 0 <= y && y < board[0].length && board[x][y] == 'O') {
          board[x][y] = '*';
          q.add(new int[]{x, y});
        }
      }
    }
  }
}
