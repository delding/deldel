/**
 * You are given an n x n 2D matrix representing an image.
 * <p>
 * Rotate the image by 90 degrees (clockwise).
 * <p>
 * Follow up:
 * Could you do this in-place?
 */

public class Solution {
  public void rotate(int[][] matrix) {
    if (matrix.length == 0 || matrix[0].length == 0) return;
    int layer = matrix.length / 2;
    int l = 0;
    while (l < layer) {
      int top = 0 + l;
      int bot = matrix.length - 1 - l;
      int left = 0 + l;
      int right = matrix[0].length - 1 - l;
      for (int i = 0; i < right - left; i++) {
        int tmp = matrix[top][left + i];
        matrix[top][left + i] = matrix[bot - i][left];
        matrix[bot - i][left] = matrix[bot][right - i];
        matrix[bot][right - i] = matrix[top + i][right];
        matrix[top + i][right] = tmp;
      }
      l++;
    }
  }
}