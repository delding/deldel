/**
 Given an integer, write a function to determine if it is a power of three.

 Follow up:
 Could you do it without using any loop / recursion?
 **/

public class Solution {
	// three is a prime, power of three only contains factor 3,
	// since input is int, max power of three in int range should module n == 0
	// make a lookup table for all powers of three within int range
	public boolean isPowerOfThree(int n) {
		// 3^19=1162261467 < Integer.MAX_INT
		return (n > 0 &&  1162261467 % n == 0);
	}
}