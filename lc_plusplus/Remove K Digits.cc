// Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.
//
// Note:
// The length of num is less than 10002 and will be ≥ k.
// The given num does not contain any leading zero.
// Example 1:
//
// Input: num = "1432219", k = 3
// Output: "1219"
// Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
// Example 2:
//
// Input: num = "10200", k = 1
// Output: "200"
// Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
// Example 3:
//
// Input: num = "10", k = 2
// Output: "0"
// Explanation: Remove all the digits from the number and it is left with nothing which is 0.


class Solution {
public:
    string removeKdigits(string num, int k) {
        string ret;
        for (auto c : num) {
            while (k != 0 && !ret.empty() && ret.back() > c) {
                ret.pop_back();
                --k;
            }
            ret += c;
        }
        while (k-- > 0) ret.pop_back();
        while (!ret.empty() && ret.front() == '0') ret.erase(ret.begin());
        return ret.empty() ? "0" : ret;
    }

    // the smallest digit in first k + 1 digits must be kept to make the remaining number smallest
    string removeKdigits2(string num, int k) {
        auto ret = remove(num, k, 0);
        auto it = ret.begin();
        while (*it == '0') ++it;
        ret.erase(ret.begin(), it);
        return ret.empty() ? "0" : ret;
    }

    string remove(string num, int k, int start) {
        if (num.size() - start == k) return "";
        int minVal = 10, idx;
        for (int i = start; i <= start + k; ++i) {
            if (num[i] - 'a' < minVal) {  // remove the first min, because if equal the latter min could be larger in next recursion
                idx = i;
                minVal = num[i] - 'a';
            }
        }
        return num[idx] + remove(num, k - (idx - start), idx + 1);
    }
};
