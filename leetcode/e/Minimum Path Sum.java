/**
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 * <p>
 * Note: You can only move either down or right at any point in time.
 **/

public class Solution {
  public int minPathSum(int[][] grid) {
    if (grid.length == 0 || grid[0].length == 0) return 0;
    int m = grid.length;
    int n = grid[0].length;
    int[][] dist = new int[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (i == 0 && j == 0) dist[0][0] = grid[0][0];
        else if (i == 0) dist[0][j] = grid[0][j] + dist[0][j - 1];
        else if (j == 0) dist[i][0] = grid[i][0] + dist[i - 1][0];
        else dist[i][j] = Math.min(dist[i - 1][j], dist[i][j - 1]) + grid[i][j];
      }
    }
    return dist[m - 1][n - 1];
  }
}