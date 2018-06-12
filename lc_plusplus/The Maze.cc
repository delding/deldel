// There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
//
// Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.
//
// The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.
//
// Example 1
//
// Input 1: a maze represented by a 2D array
//
// 0 0 1 0 0
// 0 0 0 0 0
// 0 0 0 1 0
// 1 1 0 1 1
// 0 0 0 0 0
//
// Input 2: start coordinate (rowStart, colStart) = (0, 4)
// Input 3: destination coordinate (rowDest, colDest) = (4, 4)
//
// Output: true
// Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
//
// Example 2
//
// Input 1: a maze represented by a 2D array
//
// 0 0 1 0 0
// 0 0 0 0 0
// 0 0 0 1 0
// 1 1 0 1 1
// 0 0 0 0 0
//
// Input 2: start coordinate (rowStart, colStart) = (0, 4)
// Input 3: destination coordinate (rowDest, colDest) = (3, 2)
//
// Output: false
// Explanation: There is no way for the ball to stop at the destination.
//
// Note:
// There is only one ball and one destination in the maze.
// Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
// The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
// The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.


class Solution {
public:
    bool hasPath(vector<vector<int>>& maze, vector<int>& start, vector<int>& destination) {
        vector<vector<bool>> visited{maze.size(), vector<bool>(maze[0].size())};
        return dfs(maze, start, destination, visited);
    }

    bool dfs(vector<vector<int>>& maze, vector<int>& curr, vector<int>& dest, vector<vector<bool>>& visited) {
        visited[curr[0]][curr[1]] = true;
        if (curr == dest) {
            return true;
        }
        for (auto nei : getNeighbors(curr, maze)) {
            if (!visited[nei[0]][nei[1]]) {
                if (dfs(maze, nei, dest, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    vector<vector<int>> getNeighbors(vector<int>& curr, vector<vector<int>>& maze) {
        vector<vector<int>> neighbors;
        vector<vector<int>> dirs{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (auto dir : dirs) {
            auto next = curr;
            while (next[0] + dir[0] >= 0 && next[0] + dir[0] < maze.size() && next[1] + dir[1] >= 0 && next[1] + dir[1] < maze[0].size() && maze[next[0] + dir[0]][next[1] + dir[1]] == 0) {
                next[0] += dir[0];
                next[1] += dir[1];
            }
            if (next != curr) {
                neighbors.push_back(next);
            }
        }
        return neighbors;
    }
};
