// There is a box protected by a password. The password is n digits, where each letter can be one of the first k digits 0, 1, ..., k-1.
//
// You can keep inputting the password, the password will automatically be matched against the last n digits entered.
//
// For example, assuming the password is "345", I can open it when I type "012345", but I enter a total of 6 digits.
//
// Please return any string of minimum length that is guaranteed to open the box after the entire string is inputted.
//
// Example 1:
// Input: n = 1, k = 2
// Output: "01"
// Note: "10" will be accepted too.
// Example 2:
// Input: n = 2, k = 2
// Output: "00110"
// Note: "01100", "10011", "11001" will be accepted too.
// Note:
// n will be in the range [1, 4].
// k will be in the range [1, 10].
// k^n will be at most 4096.


class Solution {
public:
    string crackSafe(int n, int k) {
        string res(n, '0');
        unordered_set<string> visited{res};
        dfs(n, k, res, visited);
        return res;
    }

    bool dfs(int n, int k, string& res, unordered_set<string>& visited) {
        if (visited.size() == pow(k, n)) return true;
        auto suffix = res.substr(res.size() - n + 1);
        for (int i = 0; i < k; ++i) {
            suffix += to_string(i);
            if (visited.count(suffix) == 0) {
                visited.insert(suffix);
                res += to_string(i);
                if (dfs(n, k, res, visited)) return true;
                visited.erase(suffix);
                res.pop_back();
            }
            suffix.pop_back();
        }
        return false;
    }
};
