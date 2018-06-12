// Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
//
// "abc" -> "bcd" -> ... -> "xyz"
// Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.
//
// For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
// A solution is:
//
// [
//   ["abc","bcd","xyz"],
//   ["az","ba"],
//   ["acef"],
//   ["a","z"]
// ]


class Solution {
public:
    vector<vector<string>> groupStrings(vector<string>& strings) {
        unordered_map<string, vector<string>> groups;
        for (auto& s : strings) {
            string key;
            int offset = s[0] - 'a';
            for (auto c : s) {
                key += 'a' + (26 + c - 'a' - offset) % 26;
            }
            groups[key].push_back(s);
        }
        vector<vector<string>> res;
        for (auto& p : groups) res.push_back(move(p.second));
        return res;
    }
};
