// A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
//
// Find all strobogrammatic numbers that are of length = n.
//
// For example,
// Given n = 2, return ["11","69","88","96"].



class Solution {
public:
    vector<string> findStrobogrammatic(int n) {
        vector<string> res;
        dfs(res, "", n);
        dfs(res, "0", n);
        dfs(res, "8", n);
        dfs(res, "1", n);
        return res;
    }

    void dfs(vector<string>& res, string curr, int n) {
        if (curr.size() > n) return;
        if (curr.size() == n) {
            if (n == 1 || n > 0 && curr[0] != '0')
                res.push_back(curr);
        } else {
            dfs(res, "6" + curr + "9", n);
            dfs(res, "9" + curr + "6", n);
            dfs(res, "1" + curr + "1", n);
            dfs(res, "8" + curr + "8", n);
            dfs(res, "0" + curr + "0", n);
        }
    }
};
