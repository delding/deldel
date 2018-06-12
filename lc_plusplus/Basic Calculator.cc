// Implement a basic calculator to evaluate a simple expression string.
//
// The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces.
//
// You may assume that the given expression is always valid.
//
// Some examples:
// "1 + 1" = 2
// " 2-1 + 2 " = 3
// "(1+(4+5+2)-3)+(6+8)" = 23


class Solution {
public:
    int calculate1(string s) {
        stack<string> st;
        for (int i = 0, num = 0; i < s.size(); ++i) {
            if (isdigit(s[i])) {
                num = 10 * num + (s[i] - '0');
                if (i + 1 == s.size() || !isdigit(s[i + 1])) {
                    st.push(to_string(num));
                    num = 0;
                }
            }
            if (s[i] == '(' || s[i] == '+' || s[i] == '-') st.push(string{s[i]});
            if (s[i] == ')') {
                int val = 0;
                while (true) {
                    auto n = stoi(st.top());
                    st.pop();
                    auto op = st.top();
                    st.pop();
                    val += n * (op == "-" ? -1 : 1);  // note type is string, "(" or "+" cause positive sign
                    if (op == "(") break;
                }
                st.push(to_string(val));
            }
            // skip white space
        }
        int ret = 0;
        while (!st.empty()) {
            auto n = stoi(st.top());
            st.pop();
            auto sign = (st.empty() || st.top() == "+") ? 1 : -1;
            if (!st.empty()) st.pop();
            ret += sign * n;
        }
        return ret;
    }

    // "8 + -3 * 5 / 2 – (2 * -2 ) + 3"
    int calculate(string s) {
        stack<string> st;
        string num;
        string ops = "+-*/(";
        for (size_t i = 0; i < s.size(); ++i) {
            if (s[i] == '-' && i + 1 < s.size() && isdigit(s[i + 1]) && (st.empty() || ops.find(st.top()) != string::npos)) {
                num += '-';
            } else if (isdigit(s[i])) {
                num += s[i];
                if (i + 1 == s.size() || !isdigit(s[i + 1])) {
                    if (!st.empty() && (st.top() == "*" || st.top() == "/")) {
                        auto op = st.top(); st.pop();
                        auto lval = st.top(); st.pop();
                        auto val = op == "*" ? stoi(lval) * stoi(num) : stoi(lval) / stoi(num);
                        num = to_string(val);
                    }
                    st.push(move(num));
                    num = "";
                }
            } else if (ops.find(s[i]) != string::npos) st.push(string{s[i]});
            else if (s[i] == ')') {
                auto val = 0;
                while (true) {
                    auto v = stoi(st.top()); st.pop();
                    auto op = st.top(); st.pop();
                    val += op == "-" ? -1 * v : v;
                    if (op == "(") break;
                }
                st.push(to_string(val));
            }
        }
        int res = 0;
        while (!st.empty()) {
            auto v = stoi(st.top()); st.pop();
            auto sign = st.empty() || st.top() == "+" ? 1 : -1;
            if (!st.empty()) st.pop();
            res += sign * v;
        }
        return res;
    }
};
