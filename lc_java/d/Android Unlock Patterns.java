/**
 Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9, count the total number of unlock patterns of the Android lock screen, which consist of minimum of m keys and maximum n keys.

 Rules for a valid pattern:
 Each pattern must connect at least m keys and at most n keys.
 All the keys must be distinct.
 If the line connecting two consecutive keys in the pattern passes through any other keys, the other keys must have previously selected in the pattern. No jumps through non selected key is allowed.
 The order of keys used matters.

 Explanation:
 | 1 | 2 | 3 |
 | 4 | 5 | 6 |
 | 7 | 8 | 9 |
 Invalid move: 4 - 1 - 3 - 6
 Line 1 - 3 passes through key 2 which had not been selected in the pattern.

 Invalid move: 4 - 1 - 9 - 2
 Line 1 - 9 passes through key 5 which had not been selected in the pattern.

 Valid move: 2 - 4 - 1 - 3 - 6
 Line 1 - 3 is valid because it passes through key 2, which had been selected in the pattern

 Valid move: 6 - 5 - 4 - 1 - 9 - 2
 Line 1 - 9 is valid because it passes through key 5, which had been selected in the pattern.

 Example:
 Given m = 1, n = 1, return 9.
 **/

public class Solution {
	public int numberOfPatterns(int m, int n) {
		int[][] connected = new int[10][10]; // matrix representation of edges, 0 means i j is connected
		connected[1][3] = connected[3][1] = 2; // 1, 3 are connected if 2 is visited
		connected[1][7] = connected[7][1] = 4;
		connected[1][9] = connected[9][1] = 5;
		connected[2][8] = connected[8][2] = 5;
		connected[9][3] = connected[3][9] = 6;
		connected[7][3] = connected[3][7] = 5;
		connected[4][6] = connected[6][4] = 5;
		connected[9][7] = connected[7][9] = 8;
		boolean[] visited = new boolean[10];
		return 4 * dfs(connected, visited, m, n, 1, 1) + 4 * dfs(connected, visited, m, n, 2, 1) + dfs(connected, visited, m, n, 5, 1);
	}

	// count number of nodes from level min to level max
	int dfs(int[][] connected, boolean[] visited, int min, int max, int curKey, int level) {
		if (visited[curKey]) return 0;
		if (level == max) return 1;
		visited[curKey] = true;
		int numPattern = level >= min ? 1 : 0;
		for (int i = 1; i <= 9; i++) {
			if (connected[curKey][i] == 0 || visited[connected[curKey][i]]) {
				numPattern += dfs(connected, visited, min, max, i, level + 1);
			}
		}
		visited[curKey] = false;
		return numPattern;
	}
}