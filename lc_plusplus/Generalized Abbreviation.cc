// Write a function to generate the generalized abbreviations of a word.
//
// Example:
// Given word = "word", return the following list (order does not matter):
// ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]


class Solution {
public:
    vector<string> generateAbbreviations(string word) {
        vector<string> abbrs;
        dfs(abbrs, word, 0);
        return abbrs;
    }

    void dfs(vector<string>& abbrs, string& word, int idx) {
        string abbr;
        for (int count = 0, i = 0; i <= word.size(); ++i) {
            if (i == word.size() || isalpha(word[i])) {
                if (count > 0) abbr += to_string(count);
                if (i < word.size()) abbr += word[i];
                count = 0;
            } else count++;
        }
        abbrs.push_back(abbr);
        for (int i{idx}; i < word.size(); ++i) {  // get all combinations, picking chars replaced by '*'
            auto tmp = '*';
            swap(tmp, word[i]);
            dfs(abbrs, word, i + 1);
            swap(tmp, word[i]);
        }
    }
};
