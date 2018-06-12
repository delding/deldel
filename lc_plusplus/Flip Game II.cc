// You are playing the following Flip Game with your friend: Given a string that
// contains only these two characters: + and -, you and your friend take turns to
// flip two consecutive "++" into "--". The game ends when a person can no longer
// make a move and therefore the other person will be the winner.
//
// Write a function to determine if the starting player can guarantee a win.
//
// For example, given s = "++++", return true. The starting player can guarantee a
// win by flipping the middle "++" to become "+--+".
//
// Follow up:
// Derive your algorithm's runtime complexity.


class Solution {
public:
    bool canWin(string s) {
        unordered_map<string, bool> memo;
        return canWin(s, memo);
    }

    bool canWin(string s, unordered_map<string, bool> memo) {
        if (s.empty()) return false;
        if (memo.count(s) == 1) return memo[s];
        bool win{false};
        for (auto i = 0; i + 1 < s.size(); ++i) {
            if (s[i] == '+' && s[i + 1] == '+') {
                s[i] = '-';
                s[i + 1] = '-';
                if (!canWin(s, memo)) {
                    win = true;
                    break;
                }
                s[i] = '+';
                s[i + 1] = '+';
            }
        }
        memo[s] = win;
        return win;
    }
};
