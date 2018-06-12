// Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
//
// If the fractional part is repeating, enclose the repeating part in parentheses.
//
// For example,
//
// Given numerator = 1, denominator = 2, return "0.5".
// Given numerator = 2, denominator = 1, return "2".
// Given numerator = 2, denominator = 3, return "0.(6)".


class Solution {
public:
    string fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";
        string res;
        if (numerator < 0 ^ denominator < 0) res += '-';
        auto numer = abs(static_cast<long>(numerator));
        auto denom = abs(static_cast<long>(denominator));
        auto integral = numer / denom;
        res += to_string(integral);
        auto rmd = numer % denom;
        if (rmd == 0) return res;
        res += '.';
        unordered_map<long, long> mp;
        while (rmd != 0) {
            if (mp.find(rmd) != mp.end()) {
                res.insert(mp[rmd], "(");
                res += ')';
                break;
            }
            mp[rmd] = res.size();
            rmd *= 10;
            res += to_string(rmd / denom);
            rmd %= denom;
        }
        return res;
    }
};
