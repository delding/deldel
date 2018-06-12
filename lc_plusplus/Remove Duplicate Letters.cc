// Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.
//
// Example:
// Given "bcabc"
// Return "abc"
//
// Given "cbacdcbc"
// Return "acdb"


class Solution {
public:
    string removeDuplicateLetters(string s) {
        int counts[26]{};
        for (auto c : s) counts[c - 'a']++;
        string ret;
        bool contains[26]{};
        for (auto c : s) {
            counts[c - 'a']--;
            if (contains[c - 'a']) continue;
            while (!ret.empty() && ret.back() > c && counts[ret.back() - 'a'] > 0) {
                contains[ret.back() - 'a'] = false;
                ret.pop_back();
            }
            ret += c;
            contains[c - 'a'] = true;
        }
        return ret;
    }
};
