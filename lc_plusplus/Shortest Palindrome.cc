// Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.
//
// For example:
//
// Given "aacecaaa", return "aaacecaaa".
//
// Given "abcd", return "dcbabcd".


class Solution {
public:
    // find longest prefix which is a palindrome
    string shortestPalindrome(string s) {
        auto r = s;
        reverse(r.begin(), r.end());
        int l = s.size();
        for (; l >= 0; --l) {
            if (r.substr(s.size() - l) == s.substr(0, l)) break;
        }
        auto t = s.substr(l);
        reverse(t.begin(), t.end());
        t += s;
        return t;
    }
};
