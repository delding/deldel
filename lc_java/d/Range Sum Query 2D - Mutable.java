/**
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left
 * corner (row1, col1) and lower right corner (row2, col2).
 * Range Sum Query 2D
 * The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.
 * Example:
 * Given matrix = [
 * [3, 0, 1, 4, 2],
 * [5, 6, 3, 2, 1],
 * [1, 2, 0, 1, 5],
 * [4, 1, 0, 1, 7],
 * [1, 0, 3, 0, 5]
 * ]
 * sumRegion(2, 1, 4, 3) -> 8
 * update(3, 2, 2)
 * sumRegion(2, 1, 4, 3) -> 10
 * Note:
 * The matrix is only modifiable by the update function.
 * You may assume the number of calls to update and sumRegion function is distributed evenly.
 * You may assume that row1 ≤ row2 and col1 ≤ col2.
 **/

public class NumMatrix {
  // https://www.topcoder.com/community/data-science/data-science-tutorials/binary-indexed-trees/
  // http://www.geeksforgeeks.org/binary-indexed-tree-or-fenwick-tree-2/
  /*
   * binary indexed tree
   * advantages of Binary Indexed Tree over Segment tree are that BIT requires less space and very easy to implement
   * The idea is based on the fact that all positive integers can be represented as sum of powers of 2.
   * Every node of BI Tree stores sum of n elements where n is a power of 2. For example, sum of first 12 elements can be obtained
   * by sum of last 4 elements (from 9 to 12) plus sum of 8 elements (from 1 to 8).
   * bit[idx] is sum from index (idx – 2^r + 1) to index idx, r is a position in idx of the
   * last digit 1 (from left to right) in binary notation, 2^r = idx & -idx
   */

  int[][] mat;
  int[][] bit2d;

  public NumMatrix(int[][] matrix) {
    if (matrix.length == 0 || matrix[0].length == 0) return;
    mat = new int[matrix.length][matrix[0].length];
    bit2d = new int[matrix.length + 1][matrix[0].length + 1]; // 1-based instead of 0-based
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        update(i, j, matrix[i][j]);
      }
    }
  }

  public void update(int row, int col, int val) {
    int add = val - mat[row][col];
    mat[row][col] = val;
    row += 1;
    col += 1; // since 1-based
    while (row < bit2d.length) {
      int colcopy = col;
      while (colcopy < bit2d[0].length) {
        bit2d[row][colcopy] += add;
        colcopy += (colcopy & -colcopy);
      }
      row += (row & -row);
    }
  }

  public int sumRegion(int row1, int col1, int row2, int col2) {
    row1++;
    col1++;
    row2++;
    col2++;
    return getSumByBit2d(row2, col2) + getSumByBit2d(row1 - 1, col1 - 1) - getSumByBit2d(row2, col1 - 1) - getSumByBit2d(row1 - 1, col2);
  }

  private int getSumByBit2d(int row, int col) {
    int sum = 0;
    while (row > 0) {
      int colcopy = col;
      while (colcopy > 0) {
        sum += bit2d[row][colcopy];
        colcopy -= (colcopy & -colcopy);
      }
      row -= (row & -row);
    }
    return sum;
  }
}

// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.update(1, 1, 10);
// numMatrix.sumRegion(1, 2, 3, 4);