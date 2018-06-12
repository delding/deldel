// You are given a m x n 2D grid initialized with these three possible values.
//
// -1 - A wall or an obstacle.
// 0 - A gate.
// INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
// Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
//
// For example, given the 2D grid:
// INF  -1  0  INF
// INF INF INF  -1
// INF  -1 INF  -1
//   0  -1 INF INF
// After running your function, the 2D grid should be:
//   3  -1   0   1
//   2   2   1  -1
//   1  -1   2  -1
//   0  -1   3   4


class Solution {
public:
    void wallsAndGates(vector<vector<int>>& rooms) {
        queue<pair<int, int>> q;
        for (int i = 0; i < rooms.size(); ++i) {
            for (int j = 0; j < rooms[0].size(); ++j) {
                if (rooms[i][j] == 0) {
                    q.emplace(i, j);
                }
            }
        }
        pair<int, int> dirs[]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int dist = 0; !q.empty();) {
            ++dist;
            for (auto size = q.size(); size > 0; --size) {
                auto p = q.front();
                q.pop();
                for (auto dir : dirs) {
                    auto x = p.first + dir.first;
                    auto y = p.second + dir.second;
                    if (x >= 0 && x < rooms.size() && y >= 0 && y < rooms[0].size() && rooms[x][y] == numeric_limits<int>::max()) {
                        rooms[x][y] = dist;
                        q.emplace(x, y);
                    }
                }
            }
        }
    }
};
