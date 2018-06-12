// Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
//
// If possible, output any possible result.  If not possible, return the empty string.
//
// Example 1:
//
// Input: S = "aab"
// Output: "aba"
// Example 2:
//
// Input: S = "aaab"
// Output: ""


class Solution {
public:
    string reorganizeString(string S) {
        unordered_map<char, int> counts;
        for (auto c : S) counts[c]++;
        auto less = [](pair<char, int>& p1, pair<char, int>& p2) {
            return p1.second < p2.second;
        };
        priority_queue<pair<char, int>, vector<pair<char, int>>, decltype(less)> q(less);
        for (auto& p : counts) q.push(move(p));
        string res;
        while (!q.empty()) {
            auto c1 = q.top(); q.pop();
            if (res.empty() || res.back() != c1.first) {
                res += c1.first;
                if (--c1.second > 0) q.push(c1);
            } else if (q.empty()) return "";
            else {
                auto c2 = q.top(); q.pop();
                res += c2.first;
                if (--c2.second > 0) q.push(c2);
                q.push(c1);
            }
        }
        return res;
    }
};
