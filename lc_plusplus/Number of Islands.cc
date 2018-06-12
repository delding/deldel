// Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
//
// Example 1:
//
// 11110
// 11010
// 11000
// 00000
// Answer: 1
//
// Example 2:
//
// 11000
// 11000
// 00100
// 00011
// Answer: 3


class Solution {
public:
    int numIslands(vector<vector<char>>& grid) {
        int num = 0;
        if (grid.empty()) return num;
        pair<int, int> dirs[4]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (size_t i = 0; i < grid.size(); ++i) {
            for (size_t j = 0; j < grid[0].size(); ++j) {
                if (grid[i][j] == '1') {
                    ++num;
                    grid[i][j] = '0';
                    queue<pair<int, int>> q;
                    q.emplace(i, j);
                    while (!q.empty()) {
                        auto pos = q.front(); q.pop();
                        for (auto& d : dirs) {
                            auto x = pos.first + d.first;
                            auto y = pos.second + d.second;
                            if (x >= 0 && x < grid.size() && y >= 0 && y < grid[0].size() && grid[x][y] == '1') {
                                grid[x][y] = '0';
                                q.emplace(x, y);
                            }
                        }
                    }
                }
            }
        }
        return num;
    }

    int numIslandsDFS(vector<vector<char>>& grid) {
      int num{0};
      for (int x = 0; x < grid.size(); ++x) {
        for (int y = 0; y < grid[0].size(); ++y) {
          if (grid[x][y] == '1') {
            ++num;
            dfs(grid, x, y);
          }
        }
      }
      return num;
    }

    void dfs(vector<vector<char>>& grid, int x, int y) {
      if (grid[x][y] == '0') return;
      grid[x][y] = '0';
      pair<int, int> dirs[]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
      for (auto& dir : dirs) {
        auto i = x + dir.first;
        auto j = y + dir.second;
        if (i >= 0 && i < grid.size() && j >= 0 && j < grid[0].size()) {
          dfs(grid, i, j);
        }
      }
    }
};
