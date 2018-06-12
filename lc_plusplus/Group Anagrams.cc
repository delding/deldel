// Given an array of strings, group anagrams together.
//
// For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
// Return:
//
// [
//   ["ate", "eat","tea"],
//   ["nat","tan"],
//   ["bat"]
// ]


class Solution {
public:
    vector<vector<string>> groupAnagrams(vector<string>& strs) {
        unordered_map<string, vector<string>> m;
        for (auto& w : strs) {
            auto root = w;
            sort(root.begin(), root.end());
            m[root].push_back(w);
        }
        vector<vector<string>> res;
        for (auto& p : m) res.push_back(p.second);
        return res;
    }
};
