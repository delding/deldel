// You are given a map in form of a two-dimensional integer grid where 1 represents
// land and 0 represents water. Grid cells are connected horizontally/vertically (not diagonally).
// The grid is completely surrounded by water, and there is exactly one island (i.e.,
// one or more connected land cells). The island doesn't have "lakes" (water inside that isn't
// connected to the water around the island). One cell is a square with side length 1.
// The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.
//
// Example:
//
// [[0,1,0,0],
//  [1,1,1,0],
//  [0,1,0,0],
//  [1,1,0,0]]
//
// Answer: 16


class Solution {
public:
    int islandPerimeter(vector<vector<int>>& grid) {
        int perimeter{0};
        if (!grid.empty()) {
            for (auto i = 0; i != grid.size(); ++i) {
                for (auto j = 0; j != grid[0].size(); ++j) {
                    if (grid[i][j] == 1) {
                        return dfs(i, j, grid);
                    }
                }
            }
        }
        return perimeter;
    }

    int dfs(int x, int y, vector<vector<int>>& grid) {
        if (grid[x][y] == -1) return 0;
        grid[x][y] = -1;
        int p{4};
        pair<int, int> dirs[]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (auto& dir : dirs) {
            auto xx = x + dir.first;
            auto yy = y + dir.second;
            if (xx >= 0 && xx < grid.size() && yy >= 0 && yy < grid[0].size() && grid[xx][yy] != 0) {
                p += dfs(xx, yy, grid) - 1;
            }
        }
        return p;
    }
};



class Solution {
public:
    int islandPerimeter(vector<vector<int>>& grid) {
        int perimeter{0};
        if (!grid.empty()) {
            for (auto i = 0; i != grid.size(); ++i) {
                for (auto j = 0; j != grid[0].size(); ++j) {
                    if (grid[i][j] == 1) {
                        dfs(make_pair(i, j), grid, perimeter);
                        return perimeter;  // need to break both loops
                    }
                }
            }
        }
        return perimeter;
    }

    void dfs(pair<int, int> cur, vector<vector<int>>& grid, int& per) {
        grid[cur.first][cur.second] = 2;
        int p{4};
        vector<pair<int, int>> dirs{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (auto& dir : dirs) {
            auto x = cur.first + dir.first;
            auto y = cur.second + dir.second;
            if (x >= 0 && x < grid.size() && y >= 0 && y < grid[0].size() && grid[x][y] != 0) {
                --p;
                if (grid[x][y] == 1) {
                    dfs(make_pair(x, y), grid, per);
                }
            }
        }
        per += p;
    }
};
