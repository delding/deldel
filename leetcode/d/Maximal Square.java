/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing all 1's and return its area.
 * <p>
 * For example, given the following matrix:
 * <p>
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * Return 4.
 */

public class Solution {

  // dp[x][y] = min(dp[x - 1][y - 1], dp[x][y - 1], dp[x - 1][y]) + 1
  // matrix[x][y] is the bottom right cell in the square, dp[x-1][y-1] + 1 determine diagonal size of this square,
  // dp[x][y-1] + 1 determine bottom edge size of the square, dp[x-1][y] + 1determine the size of right edge
  // so dp[x][y] is the min of the three
  public int maximalSquare(char[][] matrix) {
    int max = 0;
    int m = matrix.length;
    if (m == 0) return 0;
    int n = matrix[0].length;
    int[][] dp = new int[m][n];
    for (int i = 0; i < m; i++) {
      if (matrix[i][0] == '0') dp[i][0] = 0;
      else {
        dp[i][0] = 1;
        max = 1;
      }
    }
    for (int j = 0; j < n; j++) {
      if (matrix[0][j] == '0') dp[0][j] = 0;
      else {
        dp[0][j] = 1;
        max = 1;
      }
    }
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        if (matrix[i][j] == '0') dp[i][j] = 0;
        else {
          dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
        }
        max = Math.max(max, dp[i][j] * dp[i][j]);
      }
    }

    return max;
  }


  // following compute max rectangle in matrix, not just square

  // compute largest square for each row based on the row based histogram
  // largest square could contain either of the m rows, so iterate through all rows and find the largest
  public int maximalSquare1(char[][] matrix) {
    int max = 0;
    int m = matrix.length;
    if (m == 0) return 0;
    int n = matrix[0].length;
    int[] hist = new int[n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (matrix[i][j] == '0') hist[j] = 0;
        else hist[j] += 1;
      }
      max = Math.max(max, maxRectangleInHistogram(hist));
    }
    return max;
  }

  // stack top is the lowest pillar of those high pillar in the middle, it doesn't have to be the left most or right most one
  // and the left edge of those pillars is 1 + pillar before top, the right edge of those pillars is current pillar - 1
  private int maxRectangleInHistogram(int[] hist) {
    int max = 0;
    Stack<Integer> pillars = new Stack();
    for (int i = 0; i < hist.length; ) {
      if (pillars.isEmpty() || hist[i] >= pillars.peek()) {
        pillars.push(hist[i++]);
      } else { // don't update i
        int h = pillars.pop();
        int l = pillars.isEmpty() ? 0 : pillars.peek() + 1;
        int r = i - 1;
        int w = r - l + 1;
        max = Math.max(max, h * w);
      }
    }
    // pillars still in stack are all lower than right most pillar and have it as right edge
    while (!pillars.isEmpty()) {
      int h = pillars.pop();
      int l = pillars.isEmpty() ? 0 : pillars.peek() + 1;
      int r = hist.length - 1;
      int w = r - l + 1;
      max = Math.max(max, h * w);
    }
    return max;
  }
}
