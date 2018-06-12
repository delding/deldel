// Implement a magic directory with buildDict, and search methods.
//
// For the method buildDict, you'll be given a list of non-repetitive words to build a dictionary.
//
// For the method search, you'll be given a word, and judge whether if you modify exactly one character into another character in this word, the modified word is in the dictionary you just built.
//
// Example 1:
// Input: buildDict(["hello", "leetcode"]), Output: Null
// Input: search("hello"), Output: False
// Input: search("hhllo"), Output: True
// Input: search("hell"), Output: False
// Input: search("leetcoded"), Output: False


class MagicDictionary {
    struct Node {
        bool isWord = false;
        Node* child[26] = {};
    };
    Node* root = new Node();
public:
    /** Initialize your data structure here. */
    MagicDictionary() {

    }

    /** Build a dictionary through a list of words */
    void buildDict(vector<string> dict) {
        for (auto& w : dict) {
            auto cur = root;
            for (auto c : w) {
                if (cur->child[c - 'a'] == nullptr) cur->child[c - 'a'] = new Node();
                cur = cur->child[c - 'a'];
            }
            cur->isWord = true;
        }
    }

    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    bool search(string word) {
        for (int i = 0; i < word.size(); ++i) {
            auto c = word[i];
            for (char j = 'a'; j <= 'z'; ++j) {
                if (j != c) {
                    word[i] = j;
                    auto cur = root;
                    bool broke = false;
                    for (auto cc : word) {
                        if (cur->child[cc - 'a'] != nullptr) cur = cur->child[cc - 'a'];
                        else {
                            broke = true;
                            break;
                        }
                    }
                    if (!broke && cur->isWord) return true;
                }
            }
            word[i] = c;
        }
        return false;
    }
};

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dict);
 * bool param_2 = obj.search(word);
 */
