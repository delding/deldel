// There is a ball in a maze with empty spaces and walls. The ball can go through
// empty spaces by rolling up, down, left or right, but it won't stop rolling until
// hitting a wall. When the ball stops, it could choose the next direction.
//
// Given the ball's start position, the destination and the maze, find the shortest
// distance for the ball to stop at the destination. The distance is defined by the
// number of empty spaces traveled by the ball from the start position (excluded)
// to the destination (included). If the ball cannot stop at the destination, return -1.
//
// The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space.
// You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.
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
// Output: 12
// Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
//              The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.
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
// Output: -1
// Explanation: There is no way for the ball to stop at the destination.


class Solution {
public:
    int shortestDistance(vector<vector<int>>& maze, vector<int>& start, vector<int>& destination) {
        vector<vector<int>> dists{maze.size(), vector<int>(maze[0].size(), INT_MAX)};
        dists[start[0]][start[1]] = 0;
        queue<vector<int>> q; // can have a priority queue that pop the node with shortest distance
        q.push(vector<int>{start[0], start[1], 0});
        while (!q.empty()) {
            auto curr = q.front();
            q.pop();
            for (auto nei : getNeighbors(curr, maze)) {
                if (nei[2] < dists[nei[0]][nei[1]]) {  // only consider node whose dist is updated
                    dists[nei[0]][nei[1]] = nei[2];
                    q.push(nei);
                }
            }
        }
        return dists[destination[0]][destination[1]] == INT_MAX ? -1 : dists[destination[0]][destination[1]];
    }

    vector<vector<int>> getNeighbors(vector<int>& curr, vector<vector<int>>& maze) {
        vector<vector<int>> neighbors;
        vector<vector<int>> dirs{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (auto& dir : dirs) {
            auto next = curr;
            int dist = 0;
            while (next[0] + dir[0] >= 0 && next[0] + dir[0] < maze.size() && next[1] + dir[1] >= 0 && next[1] + dir[1] < maze[0].size() && maze[next[0] + dir[0]][next[1] + dir[1]] == 0) {
                next[0] += dir[0];
                next[1] += dir[1];
                ++dist;
            }
            if (dist > 0) {
                next[2] += dist;
                neighbors.push_back(next);
            }
        }
        return neighbors;
    }
};
