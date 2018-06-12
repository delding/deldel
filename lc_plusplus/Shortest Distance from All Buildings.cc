// You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
//
// Each 0 marks an empty land which you can pass by freely.
// Each 1 marks a building which you cannot pass through.
// Each 2 marks an obstacle which you cannot pass through.
// For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):
//
// 1 - 0 - 2 - 0 - 1
// |   |   |   |   |
// 0 - 0 - 0 - 0 - 0
// |   |   |   |   |
// 0 - 0 - 1 - 0 - 0
// The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.
//
// Note:
// There will be at least one building. If it is not possible to build such house according to the above rules, return -1.


class Solution {
public:
    int shortestDistance(vector<vector<int>>& grid) {
      pair<int, int> dirs[]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
      vector<vector<int>> dists{grid.size(), vector<int>(grid[0].size())};
      int numBuild{0};
      for (auto i = 0; i < grid.size(); ++i) {
        for (auto j = 0; j < grid[0].size(); ++j) {
          if (grid[i][j] == 1) {
            ++numBuild;
            vector<vector<bool>> visited{grid.size(), vector<bool>(grid[0].size())};
            int dist{0};
            queue<pair<int, int>> q;
            q.push(make_pair(i, j));
            while (!q.empty()) {
              ++dist;
              auto size = q.size();
              while (size-- > 0) {
                auto next = q.front();
                q.pop();
                for (auto dir : dirs) {
                  auto x = next.first + dir.first;
                  auto y = next.second + dir.second;
                  if (x >= 0 && x < grid.size() && y >= 0 && y < grid[0].size() && grid[x][y] != 1 && grid[x][y] != 2 && !visited[x][y]) {
                    dists[x][y] += dist;
                    visited[x][y] = true;
                    --grid[x][y];  // count how many buildings can be reached from this empty cell
                    q.push(make_pair(x, y));
                  }
                }
              }
            }
          }
        }
      }
      int shortest{INT_MAX};
      for (auto i = 0; i < grid.size(); ++i) {
        for (auto j = 0; j < grid[0].size(); ++j) {
          if (grid[i][j] == -numBuild) {  // reachable from all buildings
            shortest = min(shortest, dists[i][j]);
          }
        }
      }
      return shortest == INT_MAX ? -1 : shortest;
    }
};
