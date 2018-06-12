// Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...
//
// Note:
// n is positive and will fit within the range of a 32-bit signed integer (n < 231).
//
// Example 1:
//
// Input:
// 3
//
// Output:
// 3
// Example 2:
//
// Input:
// 11
//
// Output:
// 0
//
// Explanation:
// The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.


class Solution {
public:
    // find the length of the number where the nth digit is from
    // find the actual number where the nth digit is from
    // find the nth digit
    int findNthDigit(int n) {
        int digN = 1;
        while (n - 9 * pow(10, digN - 1) * digN > 0) {
            n -= 9 * pow(10, digN - 1) * digN++;
        }
        n--;  // starting from pow(10, digN - 1) the 0th is the first
        int nthNum = n / digN;
        int nthDig = n % digN;
        return (int)(pow(10, digN - 1) + nthNum) / (int)pow(10, digN - 1 - nthDig) % 10;
    }
};
