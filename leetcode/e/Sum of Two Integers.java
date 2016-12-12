/**
 Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
**/

public class Solution {
	public int getSum(int a, int b) {
		int carry = a & b;
		int sumWithNoCarry = a ^ b;
		if (carry == 0) return sumWithNoCarry;
		else return getSum(sumWithNoCarry, carry << 1);
	}
}