// Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.
//
// Example:
// Input:
// [
//  [ 1, 2, 3 ],
//  [ 4, 5, 6 ],
//  [ 7, 8, 9 ]
// ]
// Output:  [1,2,4,7,5,3,6,8,9]


class Solution {
public:
    vector<int> findDiagonalOrder(vector<vector<int>>& matrix) {
        if (matrix.empty()) {
            return vector<int>{};
        }
        int m{matrix.size()};  // convert to int
        int n{matrix[0].size()};
        vector<int> ret;
        pair<int, int> dirs[2]{{-1, 1}, {1, -1}};
        int dirIdx{0};
        for (int i = 0, j = 0, k = 0; k < m * n; ++k) {
            ret.push_back(matrix[i][j]);
            i += dirs[dirIdx].first;
            j += dirs[dirIdx].second;
            if (i == m) {  // don't do i >= matrix.size(), as i is treated as unsigned_int and if i = -1 i will be very big
                i -= 1;
                j += 2;
                dirIdx = 1 - dirIdx;
            }
            if (j == n) {
                i += 2;
                j -= 1;
                dirIdx = 1 - dirIdx;
            }
            if (i == -1) {
                i = 0;
                dirIdx = 1 - dirIdx;
            }
            if (j == -1) {
                j = 0;
                dirIdx = 1 - dirIdx;
            }
        }
        return ret;
    }
};
