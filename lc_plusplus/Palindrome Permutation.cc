// Given a string, determine if a permutation of the string could form a palindrome.
//
// For example,
// "code" -> False, "aab" -> True, "carerac" -> True.


class Solution {
public:
    bool canPermutePalindrome(string s) {
        int counts[256]{};
        for (auto c : s) ++counts[c];
        int odd{0};
        for (auto cnt : counts) {
            if (cnt % 2 == 1) ++odd;
        }
        return odd < 2;
    }
};
