/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * <p>
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 * For example,
 * <p>
 * Consider the following matrix:
 * <p>
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 * Given target = 5, return true.
 * <p>
 * Given target = 20, return false.
 */

// http://articles.leetcode.com/2010/10/searching-2d-sorted-matrix-part-ii.html
public class Solution {
  // We call this the Step-wise Linear Search method. Similar to Diagonal Binary Search, we begin with the upper right corner
  // (or the bottom left corner). Instead of traversing diagonally each step, we traverse one step to the left or bottom.
  // Essentially, each step we are able to eliminate either a row or a column. The worst case scenario is where it ended
  // up in the opposite corner of the matrix, which takes at most 2n steps
  public boolean searchMatrix(int[][] matrix, int target) {
    // matrix[i][j] is always the biggest in its row and smallest in its column
    if (matrix.length == 0) return false;
    int i = 0;
    int j = matrix[0].length - 1;
    while (true) {
      if (matrix[i][j] == target) return true;
      else if (matrix[i][j] < target) {
        i++;
        if (i == matrix.length) break;
      } else {
        j--;
        if (j < 0) break;
      }
    }
    return false;
  }
}
