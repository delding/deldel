// Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it. If no such solution, return -1.
//
// For example, with A = "abcd" and B = "cdabcdab".
//
// Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it; and B is not a substring of A repeated two times ("abcdabcd").
//
// Note:
// The length of A and B will be between 1 and 10000.


class Solution {
public:
    int repeatedStringMatch(string A, string B) {
        string As;
        for (int num = 1; As.size() < 2 * A.size() + B.size(); ++num) {  // As needs to be longer than A + B to include all substrings
            As += A;
            if (As.find(B) != string::npos) {
                return num;
            }
        }
        return -1;
    }
};
