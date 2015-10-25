/**
 Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

 For example,
 Given the following matrix:

 [
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
 ]
 You should return [1,2,3,6,9,8,7,4,5].
 **/

public class Solution {
  public List<Integer> spiralOrder(int[][] matrix) {
    List<Integer> res = new ArrayList<>();
    if (matrix.length == 0 || matrix[0].length == 0) return res;
    int up = 0;
    int down = matrix.length - 1;
    int left = 0;
    int right = matrix[0].length - 1;
    while(true) {
      for (int i = left; i <= right; i++) {
        res.add(matrix[up][i]);
      }
      if (++up > down) break;
      for (int i = up; i <= down; i++) {
        res.add(matrix[i][right]);
      }
      if (--right < left) break;
      for (int i = right; i >= left; i--) {
        res.add(matrix[down][i]);
      }
      if (--down < up) break;
      for (int i = down; i >= up; i--) {
        res.add(matrix[i][left]);
      }
      if (++left > right) break;
    }
    return res;
  }
}