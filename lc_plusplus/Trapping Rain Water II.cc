// Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, compute the volume of water it is able to trap after raining.
//
// Note:
// Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.
//
// Example:
//
// Given the following 3x6 height map:
// [
//   [1,4,3,1,3,2],
//   [3,2,1,3,2,4],
//   [2,3,3,2,3,1]
// ]
//
// Return 4.


class Solution {
public:
    // heap and bfs
    // trapped water can be as high as the lowest height of the border
    using cell = tuple<int, int, int>;
    int trapRainWater(vector<vector<int>>& heightMap) {
        if (heightMap.empty()) return 0;

        auto greater = [](const cell& c1, const cell& c2) {
            return get<2>(c1) > get<2>(c2);  // contrary to java, greater makes a minheap
        };
        priority_queue<cell, vector<cell>, decltype(greater)> pq{greater};
        int m{heightMap.size()};
        int n{heightMap[0].size()};
        for (int i{0}; i < m; ++i) {
            pq.emplace(i, 0, heightMap[i][0]);
            heightMap[i][0] = -1; //visited
            pq.emplace(i, n - 1, heightMap[i][n - 1]);
            heightMap[i][n - 1] = -1;
        }
        for (int j{0}; j < n; ++j) {
            pq.emplace(0, j, heightMap[0][j]);
            heightMap[0][j] = -1;
            pq.emplace(m - 1, j, heightMap[m - 1][j]);
            heightMap[m - 1][j] = -1;
        }
        int vol{0};
        while (!pq.empty()) {
            auto c = pq.top();
            pq.pop();
            auto h = get<2>(c);
            for (auto& dir : vector<pair<int, int>>{{0, -1}, {0, 1}, {1, 0}, {-1, 0}}) {
                auto x = get<0>(c) + dir.first;
                auto y = get<1>(c) + dir.second;
                if (x >= 0 && x < m && y >= 0 && y < n && heightMap[x][y] != -1) {
                    if (h > heightMap[x][y]) {
                        vol += h - heightMap[x][y];
                    }
                    pq.emplace(x, y, max(h, heightMap[x][y]));
                    heightMap[x][y] = -1;
                }
            }
        }
        return vol;
    }
};
