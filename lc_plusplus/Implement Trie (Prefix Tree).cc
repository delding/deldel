// Implement a trie with insert, search, and startsWith methods.
//
// Note:
// You may assume that all inputs are consist of lowercase letters a-z.


class Trie {
    struct Node {
        Node* chil[26];
        bool isWord;
        Node() : chil{{}}, isWord{false} {}
    };
    Node* root;

public:
    /** Initialize your data structure here. */
    Trie() : root{new Node{}} {

    }

    /** Inserts a word into the trie. */
    void insert(string word) {
        auto cur = root;
        for (auto c : word) {
            if (cur->chil[c - 'a'] == nullptr) cur->chil[c - 'a'] = new Node{};
            cur = cur->chil[c - 'a'];
        }
        cur->isWord = true;
    }

    /** Returns if the word is in the trie. */
    bool search(string word) {
        auto cur = root;
        for (auto c : word) {
            if (cur->chil[c - 'a'] == nullptr) return false;
            cur = cur->chil[c - 'a'];
        }
        return cur->isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    bool startsWith(string prefix) {
        auto cur = root;
        for (auto c : prefix) {
            if (cur->chil[c - 'a'] == nullptr) return false;
            cur = cur->chil[c - 'a'];
        }
        return true;
    }
};

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * bool param_2 = obj.search(word);
 * bool param_3 = obj.startsWith(prefix);
 */
