// Given an encoded string, return it's decoded string.
//
// The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
//
// You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
//
// Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
//
// Examples:
//
// s = "3[a]2[bc]", return "aaabcbc".
// s = "3[a2[c]]", return "accaccacc".
// s = "2[abc]3[cd]ef", return "abcabccdcdcdef".


class Solution {
public:
    string decodeString(string s) {
        string ret;
        stack<int, vector<int>> repeat;
        string num;
        for (auto c : s) {
            if (isdigit(c)) num += c;
            else if (isalpha(c)) ret += c;
            else if (c == '[') {
                repeat.push(stoi(num));
                num = "";
                ret += c;
            } else { // c == ']'
                string tmp;
                while (ret.back() != '[') {
                    tmp = ret.back() + tmp;
                    ret.pop_back();
                }
                ret.pop_back();
                auto rep = repeat.top();
                repeat.pop();
                while (rep-- > 0) {
                    ret += tmp;
                }
            }
        }
        return ret;
    }
};
