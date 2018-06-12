// Given a digit string, return all possible letter combinations that the number could represent.
//
// A mapping of digit to letters (just like on the telephone buttons) is given below.
//
//
//
// Input:Digit string "23"
// Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].


class Solution {
public:
    vector<string> letterCombinations(string digits) {
        vector<string> letter{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        vector<string> ret;
        if (digits.empty()) return ret;
        ret.push_back("");
        for (auto c : digits) {
            vector<string> tmp;
            for (auto& e : ret) {
                for (auto l : letter[c - '0']) tmp.push_back(e + l);
            }
            ret.swap(tmp);
        }
        return ret;
    }
};
