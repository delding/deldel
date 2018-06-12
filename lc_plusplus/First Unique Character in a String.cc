// Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
//
// Examples:
//
// s = "leetcode"
// return 0.
//
// s = "loveleetcode",
// return 2.


class Solution {
public:
    int firstUniqChar(string s) {
        // can also store pair of index and count, if s is very long, then target of second traverse becomes map not string itself
        unordered_map<char, int> counts;
        for (auto c : s) counts[c]++;
        for (auto i = 0; i < s.size(); ++i) {
            if (counts[s[i]] == 1) return i;
        }
        return -1;
    }
};
