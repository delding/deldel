// Given an integer matrix, find the length of the longest increasing path.
//
// From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
//
// Example 1:
//
// nums = [
//   [9,9,4],
//   [6,6,8],
//   [2,1,1]
// ]
// Return 4
// The longest increasing path is [1, 2, 6, 9].
//
// Example 2:
//
// nums = [
//   [3,4,5],
//   [3,2,6],
//   [2,2,1]
// ]
// Return 4
// The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.


class Solution {
public:
    int longestIncreasingPath(vector<vector<int>>& matrix) {
        if (matrix.empty()) return 0;
        vector<vector<int>> memo{matrix.size(), vector<int>(matrix[0].size())};
        int longest{0};
        for (auto x = 0; x < matrix.size(); ++x) {
            for (auto y = 0; y < matrix[0].size(); ++y) {
                longest = max(longest, dfs(x, y, matrix, memo));
            }
        }
        return longest;
    }

    int dfs(int x, int y, vector<vector<int>>& matrix, vector<vector<int>>& memo) {
      if (memo[x][y] == 0) {
          int len = 0;
          for (auto dir : vector<pair<int, int>>{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}) {
            auto xx = x + dir.first;
            auto yy = y + dir.second;
            if (xx >= 0 && xx < matrix.size() && yy >= 0 && yy < matrix[0].size() && matrix[xx][yy] > matrix[x][y]) {
              len = max(len, dfs(xx, yy, matrix, memo));
            }
          }
          memo[x][y] = 1 + len;
      }
      return memo[x][y];
    }
};
