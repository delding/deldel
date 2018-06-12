// Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.
//
// You may assume the integer do not contain any leading zero, except the number 0 itself.
//
// The digits are stored such that the most significant digit is at the head of the list.


class Solution {
public:
    vector<int> plusOne(vector<int>& digits) {
        int carry = 1;
        for (size_t i = digits.size(); i > 0; --i) {  // size_t > 0 never do size_t >= 0 since it's always true
            auto sum = digits[i - 1] + carry;
            digits[i - 1] = sum % 10;
            carry = sum / 10;
        }
        if (carry == 1) digits.insert(digits.begin(), 1);
        return digits;
    }
};


class Solution {
public:
    vector<int> plusOne(vector<int>& digits) {
        int carry{1};
        // for (int i = digits.size() - 1; i >= 0; --i) {  // don't use auto i otherwise --i overflows when i = 0
        //     int sum = digits[i] + carry;
        //     digits[i] = sum % 10;
        //     carry = sum / 10;
        // }
        for (auto it = digits.rbegin(); it != digits.rend(); ++it) {
            int sum = *it + carry;
            *it = sum % 10;
            carry = sum / 10;
        }

        if (carry == 1) {
            digits.insert(digits.begin(), 1);
        }
        return digits;
    }
};
