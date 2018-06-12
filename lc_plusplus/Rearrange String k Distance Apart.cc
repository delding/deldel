// Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance k from each other.
//
// All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".
//
// Example 1:
// s = "aabbcc", k = 3
//
// Result: "abcabc"
//
// The same letters are at least distance 3 from each other.
// Example 2:
// s = "aaabc", k = 3
//
// Answer: ""
//
// It is not possible to rearrange the string.
// Example 3:
// s = "aaadbbcc", k = 2
//
// Answer: "abacabcd"
//
// Another possible answer is: "abcabcda"
//
// The same letters are at least distance 2 from each other.


class Solution {
public:
    string rearrangeString(string s, int k) {
        if (k == 0) return s;
        // unordered_multiset<char> counts;  // don't use multiset, size of {'a', 'a'} is 2 not 1
        unordered_map<char, int> counts;
        for (auto c : s) ++counts[c];
        auto less = [](pair<char, int>& p1, pair<char, int>& p2) {
            if (p1.second == p2.second) return p1.first < p2.first;  // so that "abcdabcd" not e.g. "dbcadcba"
            return p1.second < p2.second;  // get larger count first to maintain the largest char variaty
        };
        priority_queue<pair<char, int>, vector<pair<char, int>>, decltype(less)> pq(counts.begin(), counts.end(), less);
        string ret;
        while (s.size() - ret.size() > 0) {
            int rem = s.size() - ret.size();
            auto runs = min(k, rem);
            vector<pair<char, int>> memo;
            while (runs-- > 0) {
                if (pq.empty()) return "";
                auto e = pq.top();
                pq.pop();
                ret += e.first;
                if (--e.second > 0) memo.push_back(move(e));
            }
            for (auto e : memo) pq.push(move(e));
        }
        return ret;
    }
};
