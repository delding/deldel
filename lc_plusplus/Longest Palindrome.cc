// Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.
//
// This is case sensitive, for example "Aa" is not considered a palindrome here.
//
// Note:
// Assume the length of given string will not exceed 1,010.
//
// Example:
//
// Input:
// "abccccdd"
//
// Output:
// 7
//
// Explanation:
// One longest palindrome that can be built is "dccaccd", whose length is 7.


class Solution {
public:
    int longestPalindrome(string s) {
        unordered_map<char, int> counts;
        for (char c : s) counts[c]++;
        int len = 0;
        bool odd = false;
        for (auto& e : counts) {
            if (e.second % 2 == 1) {
              odd = true;
              len += e.second - 1;
            } else len += e.second;
        }
        return odd ? len + 1 : len;
    }
};
