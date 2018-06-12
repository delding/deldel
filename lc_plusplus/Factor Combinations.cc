// Numbers can be regarded as product of its factors. For example,
//
// 8 = 2 x 2 x 2;
//   = 2 x 4.
// Write a function that takes an integer n and return all possible combinations of its factors.
//
// Note:
// You may assume that n is always positive.
// Factors should be greater than 1 and less than n.


class Solution {
public:
    vector<vector<int>> getFactors(int n) {
        vector<vector<int>> res;
        if (n == 1) return res;
        vector<int> f;
        factors(2, n, f, res, n);
        return res;
    }

    void factors(int i, int n, vector<int>& f, vector<vector<int>>& res, int num) {
        if (n == 1) res.push_back(f);
        else {
            for (int j = i; j < num; ++j) {
                if (j > n) break;
                if (n % j == 0) {
                    f.push_back(j);
                    factors(j, n / j, f, res, num);
                    f.pop_back();
                }
            }
        }
    }
};
