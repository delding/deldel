// Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
//
// For example, given n = 3, a solution set is:
//
// [
//   "((()))",
//   "(()())",
//   "(())()",
//   "()(())",
//   "()()()"
// ]


class Solution {
public:
    vector<string> generateParenthesis(int n) {
        vector<string> res;
        string parens;
        gen(0, 0, n, res, parens);
        return res;
    }

    void gen(int l, int r, int n, vector<string>& res, string& parens) {
        if (parens.size() == 2 * n) {
            res.push_back(parens);
            return;
        }
        if (l < n) {
            parens += "(";
            gen(l + 1, r, n, res, parens);
            parens.pop_back();
        }
        if (l > r) {
            parens += ")";
            gen(l, r + 1, n, res, parens);
            parens.pop_back();
        }
    }
};
