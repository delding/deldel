/**
 Given an array of integers A and let n to be its length.

 Assume Bk to be an array obtained by rotating the array A k positions clock-wise, we define a "rotation function" F on A as follow:

 F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1].

 Calculate the maximum value of F(0), F(1), ..., F(n-1).

 Note:
 n is guaranteed to be less than 105.

 Example:

 A = [4, 3, 2, 6]

 F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
 F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
 F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
 F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26

 So the maximum value of F(0), F(1), F(2), F(3) is F(3) = 26.
 **/

public class Solution {
	public int maxRotateFunctionSlow(int[] A) {
		if (A.length == 0) return 0;
		int max = Integer.MIN_VALUE;
		for (int k = 0; k < A.length; k++) {
			int val = 0;
			int radix = 0;
			for (int a : A) {
				val += radix * a;
				radix++;
			}
			max = Math.max(max, val);
			rotate(A, 1);
		}
		return max;
	}

	void rotate(int[] A, int k) {
		int ll = 0;
		int lr = A.length - k - 1;
		int hl = A.length - k;
		int hr = A.length - 1;
		while (ll < lr) swap(A, ll++, lr--);
		while(hl < hr) swap(A, hl++, hr--);
		int l = 0;
		int h = A.length - 1;
		while(l < h) swap(A, l++, h--);
	}

	void swap(int[] A, int l, int r) {
		int tmp = A[l];
		A[l] = A[r];
		A[r] = tmp;
	}

	// O(n)
	public int maxRotateFunction(int[] A) {
		if (A == null || A.length == 0) {
			return 0;
		}
		int n = A.length;
		int sum = 0;
		int f0 = 0;
		for (int i = 0; i < n; i++) {
			sum += A[i];
			f0 += i * A[i];
		}
		int max = f0;
		for (int k = 1, fkminus1 = f0; k < n; k++) {
			int fk = fkminus1 + sum - n * A[n - k];
			max = Math.max(max, fk);
			fkminus1 = fk;
		}
		return max;
	}
}