/**
 You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

 Define a pair (u,v) which consists of one element from the first array and one element from the second array.

 Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

 Example 1:
 Given nums1 = [1,7,11], nums2 = [2,4,6],  k = 3

 Return: [1,2],[1,4],[1,6]

 The first 3 pairs are returned from the sequence:
 [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 Example 2:
 Given nums1 = [1,1,2], nums2 = [1,2,3],  k = 2

 Return: [1,1],[1,1]

 The first 2 pairs are returned from the sequence:
 [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 Example 3:
 Given nums1 = [1,2], nums2 = [3],  k = 3

 Return: [1,3],[2,3]

 All possible pairs are returned from the sequence:
 [1,3],[2,3]
 **/

public class Solution {
	public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
		List<int[]> res = new ArrayList<>();
		if (nums1.length == 0 || nums2.length == 0) return res;
		Queue<int[]> pq = new PriorityQueue<int[]>((p1, p2) -> {return (nums1[p1[0]] + nums2[p1[1]]) - (nums1[p2[0]] + nums2[p2[1]]);});
		for (int i = 0; i < Math.min(k, nums1.length); i++) {
			pq.add(new int[] {i, 0});
		}
		while (k-- > 0 && !pq.isEmpty()) {
			int[] pair = pq.poll();
			if (pair[1] + 1 < nums2.length) {
				pq.add(new int[] {pair[0], pair[1] + 1});
			}
			res.add(new int[]{nums1[pair[0]], nums2[pair[1]]});
		}
		return res;
	}
}