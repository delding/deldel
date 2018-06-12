// Given a string, find the length of the longest substring T that contains at most 2 distinct characters.
//
// For example, Given s = “eceba”,
//
// T is "ece" which its length is 3.


class Solution {
public:
    int lengthOfLongestSubstringTwoDistinct(string s) {
      int count{0};
      int len{0};
      int hashtable[256];  // index is key, value is count
      fill_n(hashtable, 256, 0);
      for (auto i = 0, j = 0; j < s.size(); ++j) {
        ++hashtable[s[j]];
        if (hashtable[s[j]] == 1) ++count;
        while (count > 2 && i <= j) {
          if (--hashtable[s[i++]] == 0) --count;
        }
        len = max(len, j - i + 1);
      }
      return len;
    }
};
