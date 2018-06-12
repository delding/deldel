/**
 Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.

 Example:
 Given matrix = [
 [1,  0, 1],
 [0, -2, 3]
 ]
 k = 2
 The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]] is 2 and 2 is the max number no larger than k (k = 2).

 Note:
 The rectangle inside the matrix must have an area > 0.
 What if the number of rows is much larger than the number of columns?
**/

public class Solution {
	// aggregate 2d matrix into 1d array, use cumulative sum and binary search to find max sum
	// time complexity O[min(m,n)^2 * max(m,n) * log(max(m,n))]
	public int maxSumSubmatrix(int[][] matrix, int K) {
		int maxSum = Integer.MIN_VALUE;
		int m = matrix.length;
		int n = matrix[0].length;
		for (int i = 0; i < m; i++) {
			int[] rowsSum = new int[n];
			for (int j = i; j < m; j++) {
				for (int k = 0; k < n; k++) {
					rowsSum[k] += matrix[j][k];
				}
				TreeSet<Integer> bsTree = new TreeSet<Integer>(); // binary search tree
				bsTree.add(0); // so that sum (cumulative - 0) can include the first element
				int cumulative = 0;
				for (int sum : rowsSum) {
					cumulative += sum;
					int pre = cumulative - K; // cumulative - pre <= K
					Integer ceilPre = bsTree.ceiling(pre);
					if (ceilPre != null && cumulative - ceilPre > maxSum) {
						maxSum = cumulative - ceilPre;
					}
					bsTree.add(cumulative);
				}
			}
		}
		return maxSum;
	}
}