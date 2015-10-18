/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

11110
11010
11000
00000
Answer: 1

Example 2:

11000
11000
00100
00011
Answer: 3
* */

public class Solution {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        if (m == 0) return 0;
        int n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, m, n, i, j);
                }
            }
        }
        return count;
    }
    
    private void dfs(char[][] grid, int m ,int n, int i, int j) {
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        grid[i][j] = '2';
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if ( x >=0 && x < m && y >=0 && y < n && grid[x][y] == '1') {
                dfs(grid, m, n, x, y);
            }
        }
    }
}
