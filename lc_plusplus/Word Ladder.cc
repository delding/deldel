// Given two words (beginWord and endWord), and a dictionary's word list, find the
// length of shortest transformation sequence from beginWord to endWord, such that:
//
// Only one letter can be changed at a time.
// Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
// For example,
//
// Given:
// beginWord = "hit"
// endWord = "cog"
// wordList = ["hot","dot","dog","lot","log","cog"]
// As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
// return its length 5.
//
// Note:
// Return 0 if there is no such transformation sequence.
// All words have the same length.
// All words contain only lowercase alphabetic characters.
// You may assume no duplicates in the word list.
// You may assume beginWord and endWord are non-empty and are not the same.


class Solution {
public:
    int ladderLength(string beginWord, string endWord, vector<string>& wordList) {
        int len = 0;
        unordered_set<string> dict{wordList.begin(), wordList.end()};
        queue<string> q;
        q.push(beginWord);
        dict.erase(beginWord);
        while (!q.empty()) {
            ++len;
            for (auto s = q.size(); s > 0; --s) {
                auto w = q.front(); q.pop();
                if (w == endWord) return len;
                for (auto ww : neighbors(w, dict)) q.push(ww);
            }
        }
        return 0;
    }

    vector<string> neighbors(string& w, unordered_set<string>& dict) {
        vector<string> ret;
        for (size_t i = 0; i < w.size(); ++i) {
            auto ori = w[i];
            for (char c = 'a'; c <= 'z'; ++c) {
                if (c != ori) {
                    w[i] = c;
                    if (dict.count(w) == 1) {
                        ret.push_back(w);
                        dict.erase(w);
                    }
                }
            }
            w[i] = ori;
        }
        return ret;
    }
};
