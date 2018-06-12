// Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0'
// (the number zero), return the maximum enemies you can kill using one bomb.
// The bomb kills all the enemies in the same row and column from the planted
// point until it hits the wall since the wall is too strong to be destroyed.
// Note that you can only put the bomb at an empty cell.
//
// Example:
// For the given grid
//
// 0 E 0 0
// E 0 W E
// 0 E 0 0
//
// return 3. (Placing a bomb at (1,1) kills 3 enemies)


class Solution {
public:
    // only count enemies once for each continuous 0s (vertical or horizontal)
    int maxKilledEnemies(vector<vector<char>>& grid) {
        if (grid.empty()) return 0;
        int maxEnemies{0};
        int rowEnemies{0};
        vector<int> colEnemies(grid[0].size());
        for (auto i = 0; i < grid.size(); ++i) {
            for (auto j = 0; j < grid[0].size(); ++j) {
                if (j == 0 || grid[i][j - 1] == 'W') {
                    rowEnemies = 0;
                    for (auto k = j; k < grid[0].size() && grid[i][k] != 'W'; ++k) {
                        if (grid[i][k] == 'E') ++rowEnemies;
                    }
                }
                if (i == 0 || grid[i - 1][j] == 'W') {
                    colEnemies[j] = 0;
                    for (auto k = i; k < grid.size() && grid[k][j] != 'W'; ++k) {
                        if (grid[k][j] == 'E') ++colEnemies[j];
                    }
                }
                if (grid[i][j] == '0') maxEnemies = max(maxEnemies, rowEnemies + colEnemies[j]);
            }
        }
        return maxEnemies;
    }
};
