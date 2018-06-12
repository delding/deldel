// Given a sequence of words, check whether it forms a valid word square.
//
// A sequence of words forms a valid word square if the kth row and column read
// the exact same string, where 0 ≤ k < max(numRows, numColumns).

class Solution {
public:
    bool validWordSquare(vector<string>& words) {
        for (auto i = 0; i != words.size(); ++i) {
            for (auto j = 0; j != words[i].size(); ++j) {
                // too long, too short (words[j][i] needs to be not out of range), not equal
                if (j >= words.size() || i >= words[j].size() || words[i][j] != words[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }
};
