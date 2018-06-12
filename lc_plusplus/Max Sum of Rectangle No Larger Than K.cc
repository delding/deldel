// Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.
//
// Example:
// Given matrix = [
//   [1,  0, 1],
//   [0, -2, 3]
// ]
// k = 2
// The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]] is 2 and 2 is the max number no larger than k (k = 2).
//
// Note:
// The rectangle inside the matrix must have an area > 0.
// What if the number of rows is much larger than the number of columns?


class Solution {
public:
    // aggregate 2d matrix into 1d array, use cumulative sum and binary search to find max sum
    // time complexity O[min(m,n)^2 * max(m,n) * log(max(m,n))]
    int maxSumSubmatrix(vector<vector<int>>& matrix, int k) {
        int m = matrix.size();
        int n = matrix[0].size();
        int maxSum = INT_MIN;
        for (auto i = 0; i < m; ++i) {
            vector<int> colSums(n);
            for (auto j = i; j < m; ++j) {
                for (int k = 0; k < n; ++k) {
                    colSums[k] += matrix[j][k];
                }
                set<int> cumulSums;
                cumulSums.insert(0);
                int sum = 0;
                for (auto l = 0; l < n; ++l) {
                    sum += colSums[l];
                    auto target = sum - k;
                    auto it = cumulSums.lower_bound(target);
                    if (it != cumulSums.end()) {
                        maxSum = max(maxSum, sum - *it);
                    }
                    cumulSums.insert(sum);
                }
            }
        }
        return maxSum;
    }
};
