// Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.
//
// Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
//
// Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
//
// Note:
// The order of returned grid coordinates does not matter.
// Both m and n are less than 150.
// Example:
//
// Given the following 5x5 matrix:
//
//   Pacific ~   ~   ~   ~   ~
//        ~  1   2   2   3  (5) *
//        ~  3   2   3  (4) (4) *
//        ~  2   4  (5)  3   1  *
//        ~ (6) (7)  1   4   5  *
//        ~ (5)  1   1   2   4  *
//           *   *   *   *   * Atlantic
//
// Return:
//
// [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).


class Solution {
public:
    vector<pair<int, int>> pacificAtlantic(vector<vector<int>>& matrix) {
        vector<pair<int, int>> ret;
        if (matrix.empty()) {
            return ret;
        }
        int m{matrix.size()};
        int n{matrix[0].size()};
        vector<vector<int>> visited{m, vector<int>(n, 0)};  // don't initialize vector<int>{n, 0} which is a vector of size 2
        queue<pair<int, int>> q;
        for (auto i = 0; i != m; ++i) {
            q.push(make_pair(i, 0));
            visited[i][0] |= 1;
        }
        for (auto j = 0; j != n; ++j) {
            q.push(make_pair(0, j));
            visited[0][j] |= 1;
        }
        bfs(q, 1, visited, matrix);
        for (auto i = 0; i != m; ++i) {
            q.push(make_pair(i, n - 1));
            visited[i][n - 1] |= 2;
        }
        for (auto j = 0; j != n; ++j) {
            q.push(make_pair(m - 1, j));
            visited[m - 1][j] |= 2;
        }
        bfs(q, 2, visited, matrix);
        for (auto i = 0; i != m; ++i) {
            for (auto j = 0; j != n; ++j) {
                if (visited[i][j] == 3) {
                    ret.push_back(make_pair(i, j));
                }
            }
        }
        return ret;
    }

    void bfs(queue<pair<int, int>>& q, int mask, vector<vector<int>>& visited, const vector<vector<int>>& matrix) {
        auto m = matrix.size();
        auto n = matrix[0].size();
        vector<pair<int, int>> dirs{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while (!q.empty()) {
            auto pos = q.front();
            q.pop();
            for (const auto& dir : dirs) {
                auto x = pos.first + dir.first;
                auto y = pos.second + dir.second;
                if (x >= 0 && x < m && y >= 0 && y < n) {
                    if (matrix[x][y] >= matrix[pos.first][pos.second] && (visited[x][y] & mask) == 0) {
                        visited[x][y] |= mask;
                        q.push(make_pair(x, y));
                    }
                }
            }
        }
    }
};
