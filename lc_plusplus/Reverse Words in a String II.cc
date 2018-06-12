// Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.
//
// The input string does not contain leading or trailing spaces and the words are always separated by a single space.
//
// For example,
// Given s = "the sky is blue",
// return "blue is sky the".
//
// Could you do it in-place without allocating extra space?
//
// Related problem: Rotate Array


class Solution {
public:
    void reverseWords(vector<char>& str) {
        reverse(str.begin(), str.end());
        for (size_t i = 0, j = 0; i <= str.size(); ++i) {
            if (i == str.size() || str[i] == ' ') {
                reverse(str.begin() + j, str.begin() + i);
                j = i + 1;
            }
        }
    }
};
