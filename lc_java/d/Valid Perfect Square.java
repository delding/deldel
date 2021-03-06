/**
 Given a positive integer num, write a function which returns True if num is a perfect square else False.
**/

public class Solution {
	// math
	// 1 = 1
	// 4 = 1 + 3
	// 9 = 1 + 3 + 5
	// 16 = 1 + 3 + 5 + 7
	// 25 = 1 + 3 + 5 + 7 + 9
	public boolean isPerfectSquare(int num) {
		int i = 1;
		while (num > 0) {
			num -= i;
			i += 2;
		}
		return num == 0;
	}
	// binary search, find x between [1, num] so that x * x == num
}