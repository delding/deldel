// Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
//
// Only one letter can be changed at a time
// Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
// For example,
//
// Given:
// beginWord = "hit"
// endWord = "cog"
// wordList = ["hot","dot","dog","lot","log","cog"]
// Return
//   [
//     ["hit","hot","dot","dog","cog"],
//     ["hit","hot","lot","log","cog"]
//   ]
// Note:
// Return an empty list if there is no such transformation sequence.
// All words have the same length.
// All words contain only lowercase alphabetic characters.
// You may assume no duplicates in the word list.
// You may assume beginWord and endWord are non-empty and are not the same.


// runtime error for a test case!!
class Solution {
public:
    vector<vector<string>> findLadders(string beginWord, string endWord, vector<string>& wordList) {
        vector<vector<string>> ladders;
        if (beginWord == endWord) {
            ladders.push_back(vector<string>{beginWord});
            return ladders;
        }
        unordered_set<string> wordDict{wordList.begin(), wordList.end()};
        unordered_map<string, unordered_set<string>> backtraces;
        unordered_set<string> visited;
        queue<string> q;
        q.push(beginWord);
        auto found = false;
        while (!found && !q.empty()) {
            auto size = q.size();
            for (size_t i = 0; i < size; ++i) {
                auto& w = q.front();
                q.pop();
                visited.insert(w);
                q.push(move(w));
            }
            unordered_set<string> inQueue;
            for (size_t i = 0; i < size; ++i) {
                auto& w = q.front();
                q.pop();
                for (auto& nei : neighbors(w, wordDict)) {
                    if (nei == endWord) found = true;
                    if (visited.count(nei) == 0) {
                        backtraces[nei].insert(w);
                        if (inQueue.count(nei) == 0) {
                            inQueue.insert(nei);
                            q.push(nei);
                        }
                    }
                }
            }
        }
        vector<string> ladder;
        if (found) dfs(ladders, endWord, beginWord, backtraces, ladder);
        return ladders;
    }

    unordered_set<string> neighbors(string& w, unordered_set<string>& dict) {
        unordered_set<string> res;
        for (size_t i = 0; i < w.size(); ++i) {
            for (char c = 'a'; c <= 'z'; ++c) {
                if (c != w[i]) {
                    swap(c, w[i]);
                    if (dict.count(w) == 1) res.insert(w);
                    swap(c, w[i]);
                }
            }
        }
        return res;
    }

    void dfs(vector<vector<string>>& ladders, const string& cur, string& begin, unordered_map<string, unordered_set<string>>& backtraces, vector<string>& ladder) {
        ladder.push_back(cur);
        if (cur == begin) {
            ladders.push_back(ladder);
            reverse(ladders.back().begin(), ladders.back().end());
        } else {
            for (auto& parent : backtraces[cur]) {
                dfs(ladders, parent, begin, backtraces, ladder);
            }
        }
        ladder.pop_back();
    }
};
