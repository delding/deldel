// A group of friends went on holiday and sometimes lent each other money. For example, Alice paid for Bill's lunch for $10. Then later Chris gave Alice $5 for a taxi ride. We can model each transaction as a tuple (x, y, z) which means person x gave person y $z. Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID), the transactions can be represented as [[0, 1, 10], [2, 0, 5]].
//
// Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.
//
// Note:
//
// A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
// Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.
// Example 1:
//
// Input:
// [[0,1,10], [2,0,5]]
//
// Output:
// 2
//
// Explanation:
// Person #0 gave person #1 $10.
// Person #2 gave person #0 $5.
//
// Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
// Example 2:
//
// Input:
// [[0,1,10], [1,0,1], [1,2,5], [2,0,5]]
//
// Output:
// 1
//
// Explanation:
// Person #0 gave person #1 $10.
// Person #1 gave person #0 $1.
// Person #1 gave person #2 $5.
// Person #2 gave person #0 $5.
//
// Therefore, person #1 only need to give person #0 $4, and all debt is settled.


class Solution {
public:
    int minTransfers(vector<vector<int>>& transactions) {
        unordered_map<int, int> idToBal;
        for (auto tra : transactions) {
            idToBal[tra[0]] -= tra[2];
            idToBal[tra[1]] += tra[2];
        }
        vector<int> bals;
        for (auto p : idToBal) {
            if (p.second != 0) {
                bals.push_back(p.second);
            }
        }
        return dfs(bals, 0);
    }

    int dfs(vector<int>& bals, int idx) {
        if (idx == bals.size()) return 0;
        if (bals[idx] == 0) return dfs(bals, idx + 1);
        int minTrans{INT_MAX};
        for (auto i = idx + 1; i < bals.size(); ++i) {
            if (bals[i] * bals[idx] < 0) {  // find balance with opposite sign, this is an optimization to avoid TLE
                bals[i] += bals[idx];
                minTrans = min(minTrans, 1 + dfs(bals, idx + 1));
                bals[i] -= bals[idx];
            }
        }
        return minTrans;
    }
};
