// A string such as "word" contains the following abbreviations:
//
// ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
// Given a target string and a set of strings in a dictionary, find an abbreviation of this target string with the smallest possible length such that it does not conflict with abbreviations of the strings in the dictionary.
//
// Each number or letter in the abbreviation is considered length = 1. For example, the abbreviation "a32bc" has length = 4.
//
// Note:
// In the case of multiple answers as shown in the second example below, you may return any one of them.
// Assume length of target string = m, and dictionary size = n. You may assume that m ≤ 21, n ≤ 1000, and log2(n) + m ≤ 20.
// Examples:
// "apple", ["blade"] -> "a4" (because "5" or "4e" conflicts with "blade")
//
// "apple", ["plain", "amber", "blade"] -> "1p3" (other valid answers include "ap3", "a3e", "2p2", "3le", "3l1").


class Solution {
    // convert word to bit, if word and target has different char the corresponding bit is set to 1
    // to differentiate target and a word, the mask for target needs to have at least one bit set to 1 whose word counterpart is 1
    // dfs to get 1 bit from each word to compose mask for target, and find the mask that makes  target's abbr the shortest
public:
    string minAbbreviation(string target, vector<string>& dictionary) {
        vector<int> bitDict;
        for (auto& s : dictionary) {
            if (s.size() != target.size()) continue;
            int bit = 0;
            for (size_t i = 0; i < s.size(); ++i) {
                if (target[i] != s[i]) bit |= 1 << i;
            }
            bitDict.push_back(bit);
        }
        int size = target.size();
        int res = (1 << size) - 1, len = size;
        dfs(size, bitDict, 0, 0, res, len);
        string abbr;
        for (int i = 0, count = 0; i <= size; ++i) {
            if (i == size || (res >> i) & 1 == 1) {
                if (count > 0) abbr += to_string(count);
                count = 0;
                if (i < size) abbr += target[i];
            } else count++;
        }
        return abbr;
    }

    void dfs(int size, vector<int>& bitDict, int mask, int idx, int& res, int& len) {
        if (idx == bitDict.size()) {
            int l = 0;
            for (int i = 0; i < size; ++i) {
                if ((mask >> i) & 1 == 1) {
                    ++l;
                } else if (i + 1 == size || ((mask >> (i + 1)) & 1 == 1)) ++l;
            }
            if (l < len) {
                len = l;
                res = mask;
            }
        } else {
            for (int i = 0; i < size; ++i) {
                if ((bitDict[idx] >> i) & 1 == 1) {
                    auto m = mask | (1 << i);
                    dfs(size, bitDict, m, idx + 1, res, len);
                }
            }
        }
    }
};



class Solution2 {
// treat abbreviation number as wild card that can match all the characters in the trie.
public:
    struct TrieNode {
        TrieNode* children[26] {};
        bool isWord{false};
    };

    string minAbbreviation(string target, vector<string>& dictionary) {
        TrieNode* root = new TrieNode();
        for (auto w : dictionary) {  // skip word with length not equal to target
            if (w.size() != target.size()) continue;
            auto curr = root;
            for (auto c : w) {
                if (curr->children[c - 'a'] == nullptr) {
                    curr->children[c - 'a'] = new TrieNode();
                }
                curr = curr->children[c - 'a'];
            }
            curr->isWord = true;
        }

        map<int, vector<string>> abbrs;
        allAbbrs(target, abbrs, 0);
        for (auto& kv : abbrs) {
            for (auto& abbr : kv.second) {
                if (!find(root, abbr)) {
                    return abbr;
                }
            }
        }
        return target;
    }

    bool find(TrieNode* root, string abbr) {
        if (abbr.size() == 0) {
            return root->isWord;
        }
        if (isalpha(abbr[0])) {
            if (root->children[abbr[0] - 'a'] == nullptr) return false;
            return find(root->children[abbr[0] - 'a'], abbr.substr(1));
        }
        string d;
        for (int i = 0; isdigit(abbr[i]); ++i) d += abbr[i];
        int num = stoi(d);
        abbr = abbr.substr(d.size());
        if (num > 1) abbr = to_string(num - 1) + abbr;
        for (int i = 0; i < 26; i++) {
            if (root->children[i] != nullptr && find(root->children[i], abbr)) return true;
        }
        return false;
    }

    void allAbbrs(string target, map<int, vector<string>>& abbrs, int idx) {
        string abbr;
        int len = 0;
        for (int count = 0, i = 0; i <= target.size(); ++i) {
            if (i == target.size() || isalpha(target[i])) {
                if (count != 0) {
                    abbr += to_string(count);
                    len++;
                }
                if (i < target.size()) {
                    abbr += target[i];
                    len++;
                }
                count = 0;
            } else ++count;
        }
        abbrs[len].push_back(abbr);
        for (int i = idx; i < target.size(); ++i) {
            auto tmp = '*';
            swap(tmp, target[i]);
            allAbbrs(target, abbrs, i + 1);
            target[i] = tmp;
        }
    }
};



class Solution {
    int n, cand, bn, minlen, minab;
    vector<int> dict;

    // Return the length of abbreviation given bit sequence
    int abbrLen(int mask) {
        int count = 0;
        for (int b = 1; b < bn;) {
            if ((mask & b) == 0)
                for (; b < bn and (mask & b) == 0; b <<= 1);
            else b <<= 1;
            count ++;
        }
        return count;
    }

    // DFS backtracking
    void dfs(int bit, int mask) {
        int len = abbrLen(mask);
        if (len >= minlen) return;
        bool match = true;
        for (auto d : dict) {
            if ((mask & d) == 0) {
                match = false;
                break;
            }
        }
        if (match) {
            minlen = len;
            minab = mask;
        }
        else
            for (int b = bit; b < bn; b <<= 1)
                if (cand & b) dfs(b << 1, mask + b);
    }

public:
    string minAbbreviation(string target, vector<string>& dictionary) {
        n = target.size(), bn = 1 << n, cand = 0, minlen = INT_MAX;
        string res;

        // Preprocessing with bit manipulation
        for (auto w : dictionary) {
            int word = 0;
            if (w.size() != n) continue;
            for (int i = n-1, bit = 1; i >= 0; --i, bit <<= 1)
                if (target[i] != w[i]) word += bit;
            dict.push_back(word);
            cand |= word;
        }
        dfs(1, 0);

        // Reconstruct abbreviation from bit sequence
        for (int i = n-1, pre = i; i >= 0; --i, minab >>= 1) {
            if (minab & 1) {
                if (pre-i > 0) res = to_string(pre-i) + res;
                pre = i - 1;
                res = target[i] + res;
            }
            else if (i == 0) res = to_string(pre-i+1) + res;
        }
        return res;
    }
};
