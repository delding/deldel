// Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
//
// Example 1:
// Given words = ["bat", "tab", "cat"]
// Return [[0, 1], [1, 0]]
// The palindromes are ["battab", "tabbat"]
// Example 2:
// Given words = ["abcd", "dcba", "lls", "s", "sssll"]
// Return [[0, 1], [1, 0], [3, 2], [2, 4]]
// The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]


class Solution {
public:
    // w1 + w2 is palindrome then the longer one will have a prefix or suffix which is itself a palindrome, or w1 and w2 are reverse if they have equal length
    vector<vector<int>> palindromePairs(vector<string>& words) {
        vector<vector<int>> ret;
        unordered_map<string, int> wi;
        for (int i = 0; i < words.size(); ++i) {
            wi[words[i]] = i;
        }
        for (auto& w : words) {
            for (int len = 1; len < w.size(); ++len) {
                auto prefix = w.substr(0, len);
                if (isPalin(prefix)) {
                    auto suf = w.substr(len, w.size() - len);
                    reverse(suf.begin(), suf.end());
                    if (wi.count(suf) == 1) ret.push_back(vector<int>{wi[suf], wi[w]});
                }
                auto suffix = w.substr(w.size() - len, len);
                if (isPalin(suffix)) {
                    auto pre = w.substr(0, w.size() - len);
                    reverse(pre.begin(), pre.end());
                    if (wi.count(pre) == 1) ret.push_back(vector<int>{wi[w], wi[pre]});
                }
            }
            auto rev = w;
            reverse(rev.begin(), rev.end());
            if (rev == w) { // if w is itself a palindrome
                if (w != "" && wi.count("") == 1) {
                    ret.push_back(vector<int>{wi[w], wi[""]});
                    ret.push_back(vector<int>{wi[""], wi[w]});
                }
            } else if (wi.count(rev) == 1) ret.push_back(vector<int>{wi[w], wi[rev]});
        }
        return ret;
    }

    bool isPalin(string& word) {
        for (auto l = word.begin(), r = --word.end(); l != r && l != r + 1; ++l, --r) {
            if (*l != *r) return false;
        }
        return true;
    }
};
