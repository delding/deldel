/**
 * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 * <p>
 * For example,
 * Given n = 3,
 * <p>
 * You should return the following matrix:
 * [
 * [ 1, 2, 3 ],
 * [ 8, 9, 4 ],
 * [ 7, 6, 5 ]
 * ]
 */

public class Solution {
  public int[][] generateMatrix(int n) {
    int[][] matrix = new int[n][n];
    int up = 0, down = n - 1, left = 0, right = n - 1;
    int k = 1;
    while (true) {
      for (int i = left; i <= right; i++) {
        matrix[up][i] = k++;
      }
      if (++up > down) break;
      for (int i = up; i <= down; i++) {
        matrix[i][right] = k++;
      }
      if (--right < left) break;
      for (int i = right; i >= left; i--) {
        matrix[down][i] = k++;
      }
      if (--down < up) break;
      for (int i = down; i >= up; i--) {
        matrix[i][left] = k++;
      }
      if (++left > right) break;
    }
    return matrix;
  }
}
