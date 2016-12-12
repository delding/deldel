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

	public int maximalSquare(char[][] matrix) {
		int m = matrix.length;
		if (m == 0 || matrix[0].length == 0) return 0;
		int n = matrix[0].length;
		int maxLen = 0;
		for (int i = 0; i < m; i++) {
			char[] vector = new char[n];
			for (int j = i; j < m; j++) {
				if (j == i) vector = Arrays.copyOf(matrix[j], n);
				else {
					for (int k = 0; k < n; k++) {
						vector[k] = vector[k] == '1' && matrix[j][k] == '1' ? '1' : '0';
					}
				}
				if (findOnes(vector, j - i + 1)) {
					maxLen = Math.max(maxLen, (j - i + 1));
				}
			}
		}
		return maxLen * maxLen;
	}

	// find continous '1' streak length of which equals to len
	boolean findOnes(char[] vector, int len) {
		for (int n = 0, i = 0; i < vector.length; i++) {
			if (vector[i] == '1') n++;
			else n = 0;
			if (n == len) return true;
		}
		return false;
	}

	// dp[x][y] = min(dp[x - 1][y - 1], dp[x][y - 1], dp[x - 1][y]) + 1
	// matrix[x][y] is the bottom right cell in the square, dp[x-1][y-1] + 1 determine diagonal size of this square,
	// dp[x][y-1] + 1 determine bottom edge size of the square, dp[x-1][y] + 1determine the size of right edge
	// so dp[x][y] is the min of the three
	public int maximalSquare(char[][] matrix) {
		int m = matrix.length;
		if (m == 0 || matrix[0].length == 0) return 0;
		int n = matrix[0].length;
		int maximal = 0;
		int[][] maxLen = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 || j == 0) maxLen[i][j] = matrix[i][j] == '1' ? 1 : 0;
				else if (matrix[i][j] == '0') maxLen[i][j] = 0;
				else {
					// square means: minimum of diagonal, and two sides
					maxLen[i][j] = 1 + Math.min(Math.min(maxLen[i - 1][j - 1], maxLen[i][j - 1]), maxLen[i - 1][j]);
				}
				maximal = Math.max(maximal, maxLen[i][j]);
			}
		}
		return maximal * maximal;
	}
}




































}
