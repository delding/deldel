// Given a string, find the length of the longest substring T that contains at most k distinct characters.
//
// For example, Given s = “eceba” and k = 2,
//
// T is "ece" which its length is 3.


class Solution {
public:
    int lengthOfLongestSubstringKDistinct(string s, int k) {
        int len{0};
        unordered_map<char, int> counts;
        for (auto l = 0, r = 0; r < s.size(); ++r) {
            auto c = s[r];
            ++counts[c];
            while (counts.size() > k) {
                auto cc = s[l++];
                if (--counts[cc] == 0) {
                    counts.erase(cc);
                }
            }
            len = max(len, r - l + 1);
        }
        return len;
    }
};
