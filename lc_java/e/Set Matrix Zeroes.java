/**
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
 **/

/* constant space solution:
1.先确定第一行和第一列是否需要清零
2.扫描剩下的矩阵元素，如果遇到了0，就将对应的第一行和第一列上的元素赋值为0
3.根据第一行和第一列的信息，已经可以讲剩下的矩阵元素赋值为结果所需的值了
4.根据1中确定的状态，处理第一行和第一列。
 */

// O(m + n)
public class Solution {
  public void setZeroes(int[][] matrix) {
    boolean[] row = new boolean[matrix.length];
    boolean[] col = new boolean[matrix[0].length];
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        if (matrix[i][j] == 0) {
          row[i] = true;
          col[j] = true;
        }
      }
    }
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        if (row[i] || col[j]) matrix[i][j] = 0;
      }
    }
  }
}