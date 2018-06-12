/**
 Given an integer matrix, find the length of the longest increasing path.

 From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

 Example 1:

 nums = [
 [9,9,4],
 [6,6,8],
 [2,1,1]
 ]
 Return 4
 The longest increasing path is [1, 2, 6, 9].

 Example 2:

 nums = [
 [3,4,5],
 [3,2,6],
 [2,2,1]
 ]
 Return 4
 The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 **/

public class Solution {
	public int longestIncreasingPath(int[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0) return 0;
		int m = matrix.length;
		int n = matrix[0].length;
		int[][] pathLens = new int[m][n];
		int longest = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (pathLens[i][j] == 0) {
					dfs(matrix, i, j, pathLens);
				}
				longest = Math.max(longest, pathLens[i][j]);
			}
		}
		return longest;
	}

	int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	int dfs(int[][] matrix, int i, int j, int[][] pathLens) {
		if (pathLens[i][j] != 0) return pathLens[i][j];
		int len = 1;
		for (int[] dir : dirs) {
			int x = i + dir[0];
			int y = j + dir[1];
			if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length && matrix[x][y] > matrix[i][j]) {
				len = Math.max(len, dfs(matrix, x, y, pathLens) + 1);
			}
		}
		pathLens[i][j] = len;
		return len;
	}
}