/**
 Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, compute the volume of water it is able to trap after raining.

 Note:
 Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.

 Example:

 Given the following 3x6 height map:
 [
 [1,4,3,1,3,2],
 [3,2,1,3,2,4],
 [2,3,3,2,3,1]
 ]

 Return 4.
 **/

public class Solution {
	// heap and bfs
	// trapped water can be as high as the lowest height of the border
	public int trapRainWater(int[][] heightMap) {
		int m = heightMap.length;
		if (m == 0 || heightMap[0].length == 0) return 0;
		int n = heightMap[0].length;
		int vol = 0;
		PriorityQueue<Cell> pq = new PriorityQueue<>((c1, c2) -> c1.h - c2.h);
		for (int i = 0; i < m; i++) {
			pq.offer(new Cell(heightMap[i][0], i, 0));
			heightMap[i][0] = -1; // mark as visited
			pq.offer(new Cell(heightMap[i][n - 1], i, n - 1));
			heightMap[i][n - 1] = -1;
		}
		for (int j = 0; j < n; j++) {
			pq.offer(new Cell(heightMap[0][j], 0, j));
			heightMap[0][j] = -1;
			pq.offer(new Cell(heightMap[m - 1][j], m - 1, j));
			heightMap[m - 1][j] = -1;
		}
		int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		while (!pq.isEmpty()) {
			Cell c = pq.poll();
			for (int[] dir : dirs) {
				int x = c.x + dir[0];
				int y = c.y + dir[1];
				if (x >= 0 && x < m && y >= 0 && y < n && heightMap[x][y] != -1) { // update border
					if (c.h > heightMap[x][y]) {
						vol += c.h - heightMap[x][y];
						pq.offer(new Cell(c.h, x, y));
					} else pq.offer(new Cell(heightMap[x][y], x, y));
					heightMap[x][y] = -1;
				}
			}
		}
		return vol;
	}

	static class Cell {
		int h;
		int x;
		int y;

		Cell(int hh, int xx, int yy) {
			h = hh;
			x = xx;
			y = yy;
		}
	}
}