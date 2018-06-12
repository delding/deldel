// Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.
//
// For example, words1 = ["great", "acting", "skills"] and words2 = ["fine", "drama", "talent"] are similar, if the similar word pairs are pairs = [["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].
//
// Note that the similarity relation is transitive. For example, if "great" and "good" are similar, and "fine" and "good" are similar, then "great" and "fine" are similar.
//
// Similarity is also symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.
//
// Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.
//
// Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].
//
// Note:
//
// The length of words1 and words2 will not exceed 1000.
// The length of pairs will not exceed 2000.
// The length of each pairs[i] will be 2.
// The length of each words[i] and pairs[i][j] will be in the range [1, 20].


class Solution {
public:
    bool areSentencesSimilarTwo(vector<string>& words1, vector<string>& words2, vector<pair<string, string>> pairs) {
        if (words1.size() != words2.size()) return false;
        unordered_map<string, string> parents;
        for (auto& p : pairs) {
            auto p1 = find(p.first, parents);
            auto p2 = find(p.second, parents);
            if (p1 != p2) parents[p1] = p2;
        }
        for (int i{0}; i < words1.size(); ++i) {
            if (find(words1[i], parents) != find(words2[i], parents)) return false;
        }
        return true;
    }

    string find(string w, unordered_map<string, string>& parents) {
        if (parents.count(w) == 0) parents[w] = w;
        while (w != parents[w]) {
            auto p = parents[w];
            parents[w] = parents[p];
            w = p;
        }
        return w;
    }

    bool areSentencesSimilarTwo2(vector<string>& words1, vector<string>& words2, vector<pair<string, string>> pairs) {
        if (words1.size() != words2.size()) return false;
        unordered_map<string, unordered_set<string>> similars;
        for (auto& p : pairs) {
            similars[p.first].insert(p.second);
            similars[p.second].insert(p.first);
        }
        unordered_set<string> visited;
        for (int i{0}; i < words1.size(); ++i) {
            if (!dfs(words1[i], words2[i], similars, visited)) return false;
        }
        return true;
    }

    bool dfs(const string& w1, string& w2, unordered_map<string, unordered_set<string>>& adjs, unordered_set<string>& visited) {
        if (visited.count(w1) == 1) return false;
        visited.insert(w1);
        auto res = adjs[w1].count(w2) == 1 || w1 == w2;
        for (auto& w : adjs[w1]) {  // w is type of const string&, so the declare type of w1 need to be const string&
            res = res || dfs(w, w2, adjs, visited);
            if (res) break;
        }
        visited.erase(w1);
        return res;
    }
};
