// Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.
//
// Note:
//
// The length of both num1 and num2 is < 5100.
// Both num1 and num2 contains only digits 0-9.
// Both num1 and num2 does not contain any leading zero.
// You must not use any built-in BigInteger library or convert the inputs to integer directly.


class Solution {
public:
    string addStrings(string num1, string num2) {
        int i{num1.size() - 1};
        int j{num2.size() - 1};
        string sum;
        int carry{0};
        while (i >= 0 || j >= 0 || carry > 0) {
            int s{carry};
            if (i >= 0) {
                s += num1[i--] - '0';
            }
            if (j >= 0) {
                s += num2[j--] - '0';
            }
            sum += to_string(s % 10);
            carry = s / 10;
        }
        reverse(sum.begin(), sum.end());
        return sum;
    }
};
