// Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
//
// For example,
// Given the following matrix:
//
// [
//  [ 1, 2, 3 ],
//  [ 4, 5, 6 ],
//  [ 7, 8, 9 ]
// ]
// You should return [1,2,3,6,9,8,7,4,5].


class Solution {
public:
    vector<int> spiralOrder(vector<vector<int>>& matrix) {
        vector<int> ret;
        if (matrix.empty()) return ret;
        int top = 0, bot = matrix.size() - 1, left = 0, right = matrix[0].size() - 1;
        while (true) {
            for (auto j = left; j <= right; ++j) {
                ret.push_back(matrix[top][j]);
            }
            if (++top > bot) break;
            for (auto j = top; j <= bot; ++j) {
                ret.push_back(matrix[j][right]);
            }
            if (--right < left) break;
            for (auto j = right; j >= left; --j) {
                ret.push_back(matrix[bot][j]);
            }
            if (--bot < top) break;
            for (auto j = bot; j >= top; --j) {
                ret.push_back(matrix[j][left]);
            }
            if (++left > right) break;
        }
        return ret;
    }
};
