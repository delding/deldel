// Implement a basic calculator to evaluate a simple expression string.
//
// The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.
//
// You may assume that the given expression is always valid.
//
// Some examples:
// "3+2*2" = 7
// " 3/2 " = 1
// " 3+5 / 2 " = 5


class Solution {
public:
    int calculate(string s) {
        stack<string> st;
        string num;
        for (size_t i = 0; i < s.size(); ++i) {
            if (isdigit(s[i])) {
                num += s[i];
                if (i + 1 == s.size() || !isdigit(s[i + 1])) {
                    if (!st.empty() && (st.top() == "*" || st.top() == "/")) {
                        auto op = st.top();
                        st.pop();
                        auto lnum = st.top();
                        st.pop();
                        auto res = op == "*" ? (stoi(lnum) * stoi(num)) : (stoi(lnum) / stoi(num));
                        st.push(to_string(res));
                    } else st.push(num);
                    num.clear();
                }
            }
            if (s[i] == '+' || s[i] == '-' || s[i] == '*' || s[i] == '/') st.push(string{s[i]});
        }
        int res = 0;
        while (!st.empty()) {
            auto v = stoi(st.top());
            st.pop();
            auto sign = (st.empty() || st.top() == "+") ? 1 : -1;
            if (!st.empty()) st.pop();
            res += sign * v;
        }
        return res;
    }
};
