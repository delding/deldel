// Given a string s and a list of strings dict, you need to add a closed pair of
// bold tag <b> and </b> to wrap the substrings in s that exist in dict. If two
// such substrings overlap, you need to wrap them together by only one pair of
// closed bold tag. Also, if two substrings wrapped by bold tags are consecutive,
// you need to combine them.
//
// Example 1:
// Input:
// s = "abcxyz123"
// dict = ["abc","123"]
// Output:
// "<b>abc</b>xyz<b>123</b>"
// Example 2:
// Input:
// s = "aaabbcc"
// dict = ["aaa","aab","bc"]
// Output:
// "<b>aaabbc</b>c"
// Note:
// The given dict won't contain duplicates, and its length won't exceed 100.
// All the strings in input have length in range [1, 1000].


class Solution {
public:
    string addBoldTag(string s, vector<string>& dict) {
        vector<bool> bold(s.size());  // don't use {} to initialize which will treat int as a bool value
        for (size_t i = 0, end = 0; i < s.size(); ++i) {
            for (auto& w : dict) {  // iterate dict since dict size is smaller than string length
                if (s.substr(i, w.size()) == w) end = max(end, i + w.size());
            }
            if (i < end) bold[i] = true;
        }
        string ret;
        for (size_t i = 0; i < bold.size();) {
            if (bold[i]) {
                ret += "<b>";
                while (i < bold.size() && bold[i]) ret += s[i++];
                ret += "</b>";
            } else ret += s[i++];
        }
        return ret;
    }
};
