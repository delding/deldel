/**
 * Given two sparse matrices A and B, return the result of AB.
 You may assume that A's column number is equal to B's row number.
 */
public class Solution {
	public int[][] multiply(int[][] A, int[][] B) {
		int m = A.length;
		int n = A[0].length;
		int l = B[0].length;
		int[][] C = new int[m][l];
		// naive
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < l; j++) {
//                for (int k = 0; k < n; k++) {
//                    C[i][j] += A[i][k] * B[k][j];
//                }
//            }
//        }
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (A[i][j] != 0) {
					for (int k = 0; k < l; k++) { // join B[j][k] with A[i][j] where j are equal
						C[i][k] += A[i][j] * B[j][k];
					}
				}
			}
		}
		return C;
	}
}