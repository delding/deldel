/**
 Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

 Example:
 For num = 5 you should return [0,1,1,2,1,2].
**/

public class Solution {
	// The last set bit always contributes one in the counter, then we can unset the bit, and solve the problem recursively.
	// f[i] = f[i / 2] + i % 2
	public int[] countBits(int num) {
		int[] f = new int[num + 1];
		for (int i=1; i<=num; i++) f[i] = f[i >> 1] + (i & 1);
		return f;
	}
}