// Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character '#'). For each character they type except '#', you need to return the top 3 historical hot sentences that have prefix the same as the part of sentence already typed. Here are the specific rules:
//
// The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
// The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
// If less than 3 hot sentences exist, then just return as many as you can.
// When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
// Your job is to implement the following functions:
//
// The constructor function:
//
// AutocompleteSystem(String[] sentences, int[] times): This is the constructor. The input is historical data. Sentences is a string array consists of previously typed sentences. Times is the corresponding times a sentence has been typed. Your system should record these historical data.
//
// Now, the user wants to input a new sentence. The following function will provide the next character the user types:
//
// List<String> input(char c): The input c is the next character typed by the user. The character will only be lower-case letters ('a' to 'z'), blank space (' ') or a special character ('#'). Also, the previously typed sentence should be recorded in your system. The output will be the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.
//
//
// Example:
// Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2])
// The system have already tracked down the following sentences and their corresponding times:
// "i love you" : 5 times
// "island" : 3 times
// "ironman" : 2 times
// "i love leetcode" : 2 times
// Now, the user begins another search:
//
// Operation: input('i')
// Output: ["i love you", "island","i love leetcode"]
// Explanation:
// There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored.
//
// Operation: input(' ')
// Output: ["i love you","i love leetcode"]
// Explanation:
// There are only two sentences that have prefix "i ".
//
// Operation: input('a')
// Output: []
// Explanation:
// There are no sentences that have prefix "i a".
//
// Operation: input('#')
// Output: []
// Explanation:
// The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search.


class AutocompleteSystem {

public:
    struct Node {  // must declare before use of Node* in findAll
        int count;
        Node* ch[27];  // 'a'->'z' and ' '
        Node() : count{0}, ch{} {}
    };

    AutocompleteSystem(vector<string> sentences, vector<int> times) : root(new Node()) {
        for (size_t i = 0; i < sentences.size(); ++i) {
            add(sentences[i], times[i]);
        }
    }

    void add(string& s, int count) {
        auto cur = root;
        for (auto c : s) {
            auto idx = c == ' ' ? 26 : (c - 'a');
            if (cur->ch[idx] == nullptr) cur->ch[idx] = new Node();
            cur = cur->ch[idx];
        }
        cur->count += count;
    }

    vector<string> input(char c) {
        vector<string> top3;
        if (c == '#') {
            add(prefix, 1);
            prefix.clear();
            return top3;
        }
        prefix += c;
        vector<pair<string, int>> res;
        auto cur = root;
        for (auto p : prefix) {
            auto idx = p == ' ' ? 26 : (p - 'a');
            if (cur->ch[idx] == nullptr) return top3;
            cur = cur->ch[idx];
        }
        auto sentence = prefix;
        findAll(res, sentence, cur);
        sort(res.begin(), res.end(), [](pair<string, int>& p1, pair<string, int>& p2) {
            return p1.second > p2.second || (p1.second == p2.second && p1.first < p2.first);});
        for (size_t i = 0; i < min(3, static_cast<int>(res.size())); ++i) top3.push_back(res[i].first);
        return top3;
    }

    void findAll(vector<pair<string, int>>& res, string& sentence, Node* cur) {
        if (cur->count > 0) res.push_back({sentence, cur->count});
        for (int i = 0; i <= 26; ++i) if (cur->ch[i] != nullptr) {
            auto c = i == 26 ? ' ' : ('a' + i);
            sentence += c;
            findAll(res, sentence, cur->ch[i]);
            sentence.pop_back();
        }

    }

private:
    Node* root;
    string prefix;

};

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * vector<string> param_1 = obj.input(c);
 */
