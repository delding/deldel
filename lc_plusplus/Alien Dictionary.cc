// There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.
//
// Example 1:
// Given the following words in dictionary,
//
// [
//   "wrt",
//   "wrf",
//   "er",
//   "ett",
//   "rftt"
// ]
// The correct order is: "wertf".
//
// Example 2:
// Given the following words in dictionary,
//
// [
//   "z",
//   "x"
// ]
// The correct order is: "zx".
//
// Example 3:
// Given the following words in dictionary,
//
// [
//   "z",
//   "x",
//   "z"
// ]
// The order is invalid, so return "".
//
// Note:
// You may assume all letters are in lowercase.
// You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
// If the order is invalid, return an empty string.
// There may be multiple valid order of letters, return any one of them is fine.


class Solution {
public:
    string alienOrder(vector<string>& words) {
        unordered_map<char, unordered_set<char>> adjs;
        unordered_map<char, int> indegrees;
        for (auto& w : words) {
            for (auto c : w) {
                adjs[c];  // collect all vertices
                indegrees[c] = 0;
            }
        }
        for (int i{0}; i < words.size(); ++i) {
            for (int j{i + 1}; j < words.size(); ++j) {
                int k{0};
                for (; k < min(words[i].size(), words[j].size()); ++k) {
                    if (words[i][k] != words[j][k]) {
                        if (adjs[words[i][k]].count(words[j][k]) == 0) {
                            adjs[words[i][k]].insert(words[j][k]);
                            indegrees[words[j][k]]++;
                        }
                        break;
                    }
                }
                if (k == words[j].size() && words[i].size() > k) return ""; // for invalid case ["wrtkj","wrt"]
            }
        }
        string order;
        queue<char> q;
        for (auto& p : indegrees) if(p.second == 0) q.push(p.first);
        while (!q.empty()) {
            auto c = q.front();
            q.pop();
            order += c;
            for (auto ch : adjs[c]) {
                if (indegrees[ch] != 0 && --indegrees[ch] == 0) q.push(ch);
            }
        }
        for (auto& p : indegrees) if (p.second > 0) return ""; // cyclic
        return order;
    }


    string alienOrderDfs(vector<string>& words) {
        unordered_map<char, unordered_set<char>> adjs;
        for (auto& w : words) {
            for (auto c : w) {
                adjs[c];  // collect all vertices
            }
        }
        for (int i{0}; i < words.size(); ++i) {
            for (int j{i + 1}; j < words.size(); ++j) {
                int k{0};
                for (; k < min(words[i].size(), words[j].size()); ++k) {
                    if (words[i][k] != words[j][k]) {
                        adjs[words[i][k]].insert(words[j][k]);
                        break;
                    }
                }
                if (k == words[j].size() && words[i].size() > k) return ""; // for invalid case ["wrtkj","wrt"]
            }
        }
        unordered_set<char> visited;
        unordered_set<char> visiting;
        string postorder;
        for (auto v : adjs) {
            if (!toposort(adjs, v.first, visited, visiting, postorder)) return "";
        }
        reverse(postorder.begin(), postorder.end());
        return postorder;
    }

    bool toposort(unordered_map<char, unordered_set<char>>& adjs, char src, unordered_set<char>& visited, unordered_set<char>& visiting, string& postorder) {
        if (visited.count(src) == 1) return true;
        visiting.insert(src);
        for (auto& sink : adjs[src]) {
            if (visiting.count(sink) == 1 || !toposort(adjs, sink, visited, visiting, postorder)) return false;  // cyclic
        }
        visiting.erase(src);
        visited.insert(src);
        postorder += src;
        return true;
    }
};
