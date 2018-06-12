// A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
//
// Example:
//
// Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
// Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
//
// 0 0 0
// 0 0 0
// 0 0 0
// Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
//
// 1 0 0
// 0 0 0   Number of islands = 1
// 0 0 0
// Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
//
// 1 1 0
// 0 0 0   Number of islands = 1
// 0 0 0
// Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
//
// 1 1 0
// 0 0 1   Number of islands = 2
// 0 0 0
// Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
//
// 1 1 0
// 0 0 1   Number of islands = 3
// 0 1 0
// We return the result as an array: [1, 1, 2, 3]
//
// Challenge:
//
// Can you do it in time complexity O(k log mn), where k is the length of the positions?



class Solution {
public:
    vector<int> numIslands2(int m, int n, vector<pair<int, int>>& positions) {
        vector<vector<pair<int, int>>> parent{m, vector<pair<int, int>>(n, {-1, -1})};
        int num = 0;
        vector<int> ret;
        for (auto& p : positions) {
            auto x = p.first;
            auto y = p.second;
            parent[x][y] = make_pair(x, y);
            ++num;
            for (auto d : vector<pair<int, int>>{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}) {
                auto xx = x + d.first;
                auto yy = y + d.second;
                if (xx >= 0 && xx < m && yy >= 0 && yy < n && parent[xx][yy].first != -1) {
                    auto p1 = find(x, y, parent);
                    auto p2 = find(xx, yy, parent);
                    if (p1 != p2) {
                        --num;
                        parent[p1.first][p1.second] = p2;
                    }
                }
            }
            ret.push_back(num);
        }
        return ret;
    }

    pair<int, int> find(int i, int j, vector<vector<pair<int, int>>>& parent) {
        while (parent[i][j].first != i || parent[i][j].second != j) {
            auto p = parent[i][j];
            parent[i][j] = parent[p.first][p.second];
            i = p.first;
            j = p.second;
        }
        return parent[i][j];
    }
};
