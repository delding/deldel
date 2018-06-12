/**
 Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
 The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
 Note that you can only put the bomb at an empty cell.

 Example:
 For the given grid

 0 E 0 0
 E 0 W E
 0 E 0 0

 return 3. (Placing a bomb at (1,1) kills 3 enemies)
 **/

public class Solution {
	public int maxKilledEnemies(char[][] grid) {
		if (grid.length == 0) return 0;
		int max = 0;
		int rowEne = 0;
		int[] colEne = new int[grid[0].length]; // current enemy number for each column
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (j == 0 || grid[i][j - 1] == 'W') {
					rowEne = 0;
					for (int k = j; k < grid[0].length && grid[i][k] != 'W'; k++) {
						if (grid[i][k] == 'E') rowEne++;
					}
				}
				if (i == 0 || grid[i - 1][j] == 'W') {
					colEne[j] = 0;
					for (int k = i; k < grid.length && grid[k][j] != 'W'; k++) {
						if (grid[k][j] == 'E') colEne[j]++;
					}
				}
				if (grid[i][j] == '0') max = Math.max(max, rowEne + colEne[j]);
			}
		}
		return max;
	}

	public int maxKilledEnemiesBruteForce(char[][] grid) {
		int max = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == '0') max = Math.max(max, count(grid, i, j));
			}
		}
		return max;
	}

	int count(char[][] grid, int i, int j) {
		int sum = 0;
		int l = j - 1;
		int r = j + 1;
		for (; l >= 0 && grid[i][l] != 'W'; l--) if (grid[i][l] == 'E') sum++;
		for (; r < grid[0].length && grid[i][r] != 'W'; r++) if (grid[i][r] == 'E') sum++;
		int u = i - 1;
		int d = i + 1;
		for (; u >= 0 && grid[u][j] != 'W'; u--) if (grid[u][j] == 'E') sum++;
		for (; d < grid.length && grid[d][j] != 'W'; d++) if (grid[d][j] == 'E') sum++;
		return sum;
	}
}