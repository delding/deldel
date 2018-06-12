/**
 Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

 Note that it is the kth smallest element in the sorted order, not the kth distinct element.

 Example:

 matrix = [
 [ 1,  5,  9],
 [10, 11, 13],
 [12, 13, 15]
 ],
 k = 8,

 return 13.
 **/

public class Solution {
	// n way merge
	public int kthSmallest(int[][] matrix, int k) {
		int[] cols = new int[matrix.length];
		PriorityQueue<Integer> pq = new PriorityQueue<>((i1, i2) -> matrix[i1][cols[i1]] - matrix[i2][cols[i2]]);
		for (int row = 0; row < matrix.length; row++) pq.offer(row);
		int ret = matrix[0][0];
		while (k-- > 0) {
			int r = pq.poll();
			ret = matrix[r][cols[r]];
			if (cols[r] + 1 < matrix[0].length) {
				cols[r]++;
				pq.offer(r);
			}
		}
		return ret;
	}
}

public class Solution {
	public int kthSmallest(int[][] matrix, int k) {
		Queue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[0] - e2[0]);
		int n = matrix.length;
		for (int i = 0; i < n; i++) {
			pq.add(new int[] {matrix[i][0], i, 0});
		}
		while (--k > 0) {
			int[] e = pq.poll();
			if (++e[2] < n) pq.add(new int[] {matrix[e[1]][e[2]], e[1], e[2]});
		}
		return pq.poll()[0];
	}
}