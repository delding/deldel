/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 * <p>
 * Example 1:
 * <p>
 * 11110
 * 11010
 * 11000
 * 00000
 * Answer: 1
 * <p>
 * Example 2:
 * <p>
 * 11000
 * 11000
 * 00100
 * 00011
 * Answer: 3
 */

public class Solution {
  // bfs
  public int numIslands(char[][] grid) {
    int m = grid.length;
    if (m == 0 || grid[0].length == 0) return 0;
    int n = grid[0].length;
    int num = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == '1') {
          num++;
          grid[i][j] = '0';
          Deque<Integer> q = new ArrayDeque<>();
          q.offer(i * n + j);
          while (!q.isEmpty()) {
            int cell = q.poll();
            int x = cell / n;
            int y = cell % n;
            for (int[] dir : dirs) {
              int xx = x + dir[0];
              int yy = y + dir[1];
              if (xx >= 0 && xx < m && yy >= 0 && yy < n && grid[xx][yy] == '1') {
                grid[xx][yy] = '0';
                q.offer(xx * n + yy);
              }
            }
          }
        }
      }
    }
    return num;
  }
  // dfs
  public int numIslandsDFS(char[][] grid) {
    int m = grid.length;
    if (m == 0 || grid[0].length == 0) return 0;
    int n = grid[0].length;
    int num = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == '1') {
          num++;
          dfs(i, j, grid);
        }
      }
    }
    return num;
  }

  static int[][] dirs = new int[][] {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

  void dfs(int i, int j, char[][] grid) {
    grid[i][j] = '0';
    for (int[] dir : dirs) {
      int x = i + dir[0];
      int y = j + dir[1];
      if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == '1') dfs(x, y, grid);
    }
  }
}