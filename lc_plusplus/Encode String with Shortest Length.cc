// Given a non-empty string, encode the string such that its encoded length is the shortest.
//
// The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
//
// Note:
// k will be a positive integer and encoded string will not be empty or have extra space.
// You may assume that the input string contains only lowercase English letters. The string's length is at most 160.
// If an encoding process does not make the string shorter, then do not encode it. If there are several solutions, return any of them is fine.
// Example 1:
//
// Input: "aaa"
// Output: "aaa"
// Explanation: There is no way to encode it such that it is shorter than the input string, so we do not encode it.
// Example 2:
//
// Input: "aaaaa"
// Output: "5[a]"
// Explanation: "5[a]" is shorter than "aaaaa" by 1 character.
// Example 3:
//
// Input: "aaaaaaaaaa"
// Output: "10[a]"
// Explanation: "a9[a]" or "9[a]a" are also valid solutions, both of them have the same length = 5, which is the same as "10[a]".
// Example 4:
//
// Input: "aabcaabcd"
// Output: "2[aabc]d"
// Explanation: "aabc" occurs twice, so one answer can be "2[aabc]d".
// Example 5:
//
// Input: "abbbabbbcabbbabbbc"
// Output: "2[2[abbb]c]"
// Explanation: "abbbabbbc" occurs twice, but "abbbabbbc" can also be encoded to "2[abbb]c", so one answer can be "2[2[abbb]c]".


class Solution {
public:
    string encode(string s) {
        int len{s.size()};
        vector<vector<string>> encoder{len, vector<string>(len)};
        for (auto l = 1; l <= len; ++l) {
            for (auto i = 0, j = i + l - 1; j < len; ++i, ++j) {
                encoder[i][j] = s.substr(i, l);   // no encoding
                auto singleEncoding = repeatingSubstr(s, i, j, encoder);  // single encoding
                encoder[i][j] = encoder[i][j].size() < singleEncoding.size() ? encoder[i][j] : singleEncoding;
                for (auto k = 1; k < l; ++k) {  //  sum of two encodings
                    auto leftEncoding = encoder[i][i + k - 1];
                    auto rightEncoding = encoder[i + k][j];
                    if (leftEncoding.size() + rightEncoding.size() < encoder[i][j].size()) {
                        encoder[i][j] = leftEncoding + rightEncoding;
                    }
                }

            }
        }
        return encoder[0][len - 1];
    }

    string repeatingSubstr(string& str, int i, int j, vector<vector<string>>& encoder) {
        string s = str.substr(i, j - i + 1);
        if (s.size() > 1) {
            string ss = s + s;
            ss.pop_back();  // thus s wouldn't equal to second part of ss
            auto pos = ss.find(s, 1);  // start from 1 s.t. s wouldn't equal to first part of ss
            if (pos != string::npos) {
                auto repeatingNum = s.size() / pos;  // pos is the length of repeating substr
                auto repeatingEncodedSubstr = encoder[i][i + pos - 1];
                return to_string(repeatingNum) + "[" + repeatingEncodedSubstr + "]";
            }
        }
        return s;
    }
};
