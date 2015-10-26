/**
 * Follow up for "Unique Paths":
 * <p>
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 * <p>
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 * <p>
 * For example,
 * There is one obstacle in the middle of a 3x3 grid as illustrated below.
 * <p>
 * [
 * [0,0,0],
 * [0,1,0],
 * [0,0,0]
 * ]
 * The total number of unique paths is 2.
 * <p>
 * Note: m and n will be at most 100.
 **/

public class Solution {
  public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    if (obstacleGrid.length == 0 || obstacleGrid[0].length == 0 || obstacleGrid[0][0] == 1)
      return 0;
    int m = obstacleGrid.length;
    int n = obstacleGrid[0].length;
    int[][] nums = new int[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (i == 0 && j == 0) nums[i][j] = 1;
        else if (i == 0) nums[i][j] = obstacleGrid[i][j] == 1 ? 0 : nums[i][j - 1];
        else if (j == 0) nums[i][j] = obstacleGrid[i][j] == 1 ? 0 : nums[i - 1][j];
        else nums[i][j] = obstacleGrid[i][j] == 1 ? 0 : nums[i - 1][j] + nums[i][j - 1];
      }
    }
    return nums[m - 1][n - 1];
  }
}