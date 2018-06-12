/**
 Your task is to calculate ab mod 1337 where a is a positive integer and b is an extremely large positive integer given in the form of an array.

 Example1:

 a = 2
 b = [3]

 Result: 8
 Example2:

 a = 2
 b = [1,0]

 Result: 1024
 **/

public class Solution {
	// a^123 % m = (a^120 % m * a^3 % m) % m, a^120 % m = (a ^12)^10 % m = (a^12 % m)^10 % m
	public int superPow(int a, int[] b) {
		return superPow(a, b, b.length - 1);
	}

	int superPow(int a, int[] b, int idx) {
		if (idx == 0) return smallPowMod(a, b[idx], 1337);
		else {
			return (smallPowMod(superPow(a, b, idx - 1), 10, 1337) * smallPowMod(a, b[idx], 1337)) % 1337;
		}
	}

	int smallPowMod(int a, int p, int m) { // a^p % m = (a % m)^p % m
		int mod = a % m;
		int pow = 1;
		while (p-- > 0) pow = pow * mod % m; // % m for each fun to prevent integer overflow
		return pow % m;
	}
}