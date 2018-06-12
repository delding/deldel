// In English, we have a concept called root, which can be followed by some other words to form another longer word - let's call this word successor. For example, the root an, followed by other, which can form another word another.
//
// Now, given a dictionary consisting of many roots and a sentence. You need to replace all the successor in the sentence with the root forming it. If a successor has many roots can form it, replace it with the root with the shortest length.
//
// You need to output the sentence after the replacement.
//
// Example 1:
// Input: dict = ["cat", "bat", "rat"]
// sentence = "the cattle was rattled by the battery"
// Output: "the cat was rat by the bat"
// Note:
// The input will only have lower-case letters.
// 1 <= dict words number <= 1000
// 1 <= sentence words number <= 1000
// 1 <= root length <= 100
// 1 <= sentence words length <= 1000


class Solution {
public:
    string replaceWords(vector<string>& dict, string sentence) {
        auto root = new Node();
        for (auto& w : dict) {
            auto cur = root;
            for (auto c : w) {
                if (cur->isWord) break;  // optimization, if a word's prefix is a root, no need to store this word
                if (cur->chil[c - 'a'] == nullptr) cur->chil[c - 'a'] = new Node{};
                cur = cur->chil[c - 'a'];
            }
            cur->isWord = true;
        }
        string res;
        string w;
        stringstream ss{sentence};
        while (getline(ss, w, ' ')) {
            auto cur = root;
            for (size_t i = 0; i < w.size(); ++i) {
                if (cur->chil[w[i] - 'a'] != nullptr) {
                    cur = cur->chil[w[i] - 'a'];
                    if (cur->isWord) {
                        w = w.substr(0, i + 1);
                        break;
                    }
                } else break;
            }
            res += w + " ";
        }
        if (!res.empty()) res.pop_back();
        return res;
    }

private:
    struct Node {
        bool isWord;
        Node* chil[26];
        Node() : isWord{false}, chil{} {}
    };
};
