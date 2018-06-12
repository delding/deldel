// Write a function that takes a string as input and reverse only the vowels of a string.
//
// Example 1:
// Given s = "hello", return "holle".
//
// Example 2:
// Given s = "leetcode", return "leotcede".
//
// Note:
// The vowels does not include the letter "y".
//


class Solution {
public:
    string reverseVowels(string s) {
        string vowels{"aeiouAEIOU"};
        for (size_t i = 0, j = s.size(); i + 1 < j;) {
            if (vowels.find(s[i]) == string::npos) ++i;
            else if (vowels.find(s[j - 1]) == string::npos) --j;
            else swap(s[i++], s[j-- - 1]);
        }
        return s;
    }
};
