// Given a pattern and a string str, find if str follows the same pattern.
//
// Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
//
// Examples:
// pattern = "abba", str = "dog cat cat dog" should return true.
// pattern = "abba", str = "dog cat cat fish" should return false.
// pattern = "aaaa", str = "dog cat cat dog" should return false.
// pattern = "abba", str = "dog dog dog dog" should return false.
// Notes:
// You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.


class Solution {
public:
    bool wordPattern(string pattern, string str) {
        unordered_map<char, string> mapping;
        unordered_map<string, char> rmapping;
        stringstream ss(str);
        string s;
        for (auto p : pattern) {
            if (!getline(ss, s, ' ')) return false;  // str too short
            if (mapping.count(p) == 1 && mapping[p] != s) return false;
            if (rmapping.count(s) == 1 && rmapping[s] != p) return false;
            if (mapping.count(p) == 0 && rmapping.count(s) == 0) {
                mapping[p] = s;
                rmapping[s] = p;
            }
        }
        return !getline(ss, s, ' ');  // ss should be empty otherwise str too long
    }
};
