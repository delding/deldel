// Assume you have an array of length n initialized with all 0's and are given k update operations.
//
// Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each element of subarray A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.
//
// Return the modified array after all k operations were executed.
//
// Example:
//
// Given:
//
//     length = 5,
//     updates = [
//         [1,  3,  2],
//         [2,  4,  3],
//         [0,  2, -2]
//     ]
//
// Output:
//
//     [-2, 0, 3, 5, 3]
// Explanation:
//
// Initial state:
// [ 0, 0, 0, 0, 0 ]
//
// After applying operation [1, 3, 2]:
// [ 0, 2, 2, 2, 0 ]
//
// After applying operation [2, 4, 3]:
// [ 0, 2, 5, 5, 3 ]
//
// After applying operation [0, 2, -2]:
// [-2, 0, 3, 5, 3 ]


class Solution {
public:
    vector<int> getModifiedArray(int length, vector<vector<int>>& updates) {
        vector<int> ret(length);
        for (auto& u : updates) {
            ret[u[0]] += u[2];
            if (u[1] + 1 < length) ret[u[1] + 1] -= u[2];
        }
        int aggr = 0;
        for (int i = 0; i < length; ++i) {
            auto tmp = ret[i];
            ret[i] += aggr;
            aggr += tmp;
        }
        return ret;
    }
};
