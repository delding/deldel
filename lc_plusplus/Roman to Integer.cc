// Given a roman numeral, convert it to an integer.
//
// Input is guaranteed to be within the range from 1 to 3999.


class Solution {
public:
    int romanToInt(string s) {
        unordered_map<char, int> roman{{'I', 1}, {'V', 5}, {'X', 10}, {'L', 50}, {'C', 100}, {'D', 500}, {'M', 1000}};
        int ret = 0;
        for (size_t i = 0; i < s.size(); i++) {
            auto curr = roman[s[i]];
            if (i + 1 < s.size() && roman[s[i + 1]] > curr) ret -= curr;
            else ret += curr;
        }
        return ret;
    }
};
