/**
 You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

 Each 0 marks an empty land which you can pass by freely.
 Each 1 marks a building which you cannot pass through.
 Each 2 marks an obstacle which you cannot pass through.
 For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):

 1 - 0 - 2 - 0 - 1
 |   |   |   |   |
 0 - 0 - 0 - 0 - 0
 |   |   |   |   |
 0 - 0 - 1 - 0 - 0
 The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.
 **/

public class Solution {
	public int shortestDistance(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		int[][] dirs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1,0}};
		int[][] dists = new int[m][n];
		int notVisited = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					Queue<Integer> q = new ArrayDeque<>();
					int dist = -1;
					q.add(i * n + j);
					while (!q.isEmpty()) {
						dist++;
						int size = q.size();
						while (size-- > 0) {
							int pos = q.poll();
							int x = pos / n;
							int y = pos % n;
							if (dist > 0) { // exclude initial building being updated in dists[][]
								dists[x][y] += dist;
							}
							for (int[] dir : dirs) {
								int xx = x + dir[0];
								int yy = y + dir[1];
								if (xx >= 0 && xx < m && yy >= 0 && yy < n && grid[xx][yy] != '1' && grid[xx][yy] != '2' && grid[xx][yy] == notVisited) {
									grid[xx][yy] = notVisited - 1;
									q.add(xx * n + yy);
								}
							}
						}
					}
					notVisited--; // for cells that are not reachable from current building, their values will not be equal to the value of next round notVisited, and will not be added to queue anymore
				}
			}
		}
		int shortest = -1;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == notVisited) { // cells that are reachable from all buildings
					if (shortest == -1) shortest = dists[i][j];
					else shortest = Math.min(shortest, dists[i][j]);
				}
			}
		}
		return shortest;
	}
}