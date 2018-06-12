// The count-and-say sequence is the sequence of integers with the first five terms as following:
//
// 1.     1
// 2.     11
// 3.     21
// 4.     1211
// 5.     111221
// 1 is read off as "one 1" or 11.
// 11 is read off as "two 1s" or 21.
// 21 is read off as "one 2, then one 1" or 1211.
// Given an integer n, generate the nth term of the count-and-say sequence.
//
// Note: Each term of the sequence of integers will be represented as a string.
//
// Example 1:
//
// Input: 1
// Output: "1"
// Example 2:
//
// Input: 4
// Output: "1211"


class Solution {
    // only 1, 2, 3 can appear
    // proof: in order to have 4, must have 1111,
    // if first 1 is at odd position, then it is interpreted from 11, which should be 21 instead
    // if fisrt 1 is at even position, a1111b, then it is interpreted from a one, one one, one b, which should instead be (a+1) one, one b
    // so 1111 is not possible
public:
    string countAndSay(int n) {
        string curr = "1";
        while (--n > 0) {
            string next;
            int count = 1;
            for (size_t i = 1; i <= curr.size(); i++) {
                if (i == curr.size() || curr[i] != curr[i - 1]) {
                    next += to_string(count) + curr[i - 1];
                    count = 1;
                } else count++;
            }
            curr = move(next);
        }
        return curr;
    }
};
