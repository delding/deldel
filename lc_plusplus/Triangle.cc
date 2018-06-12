// Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
//
// For example, given the following triangle
// [
//      [2],
//     [3,4],
//    [6,5,7],
//   [4,1,8,3]
// ]
// The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
//


class Solution {
public:
    int minimumTotal(vector<vector<int>>& triangle) {
        assert(!triangle.empty());
        for (size_t r = triangle.size() - 1; r > 0; --r) {
            for (size_t c = 0; c < triangle[r - 1].size(); ++c) {
                triangle[r - 1][c] = triangle[r - 1][c] + min(triangle[r][c], triangle[r][c + 1]);
            }
        }
        return triangle[0][0];
    }
};
