// Given a pattern and a string str, find if str follows the same pattern.
//
// Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.
//
// Examples:
// pattern = "abab", str = "redblueredblue" should return true.
// pattern = "aaaa", str = "asdasdasdasd" should return true.
// pattern = "aabb", str = "xyzabcxzyabc" should return false.
// Notes:
// You may assume both pattern and str contains only lowercase letters.


class Solution {
public:
    bool wordPatternMatch(string pattern, string str) {
        unordered_map<string, string> mapping, rmapping;
        return match(pattern, 0, str, 0, mapping, rmapping);
    }

    bool match(string& pattern, int i, string& str, int j, unordered_map<string, string>& mapping, unordered_map<string, string>& rmapping) {
        if (i == pattern.size() || j == str.size()) return i == pattern.size() && j == str.size();
        auto p = pattern.substr(i, 1);
        if (mapping.count(p) == 1) {
            auto pp = mapping[p];
            if (str.substr(j, pp.size()) != pp) return false;
            return match(pattern, i + 1, str, j + pp.size(), mapping, rmapping);
        }
        for (int k = j + 1; k <= static_cast<int>(str.size()); ++k) {
            auto pp = str.substr(j, k - j);
            if (rmapping.count(pp) == 0) {
                mapping[p] = pp;
                rmapping[pp] = p;
                if (match(pattern, i + 1, str, k, mapping, rmapping)) return true;
                mapping.erase(p);
                rmapping.erase(pp);
            }
        }
        return false;
    }
};
