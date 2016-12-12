/**
 Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

 Note:
 The number of people is less than 1,100.

 Example

 Input:
 [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

 Output:
 [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 Hide Company Tags
 **/

public class Solution {
	// greedy algo
	public int[][] reconstructQueue(int[][] people) {
		Arrays.sort(people, (p1, p2) -> { // sort by primarily on h secondarily on k in increasing order
			if (p1[0] == p2[0]) {
				return p1[1] - p2[1];
			} else return p1[0] - p2[0];
		});
		int[][] res = new int[people.length][2];
		for (int[] r : res) Arrays.fill(r, -1);
		for (int[] p : people) {
			int count = p[1];
			for (int i = 0; i < res.length; i++) {
				if (res[i][0] == -1 && count == 0) { // if it's an empty slot and count = 0, put the person in
					res[i][0] = p[0];
					res[i][1] = p[1];
					break;
					// if an empty slot it will be filled by a higher person make count--
					// or if non-empty and height equals current person make count--
				} else if (res[i][0] == -1 || res[i][0] == p[0]) count--;
			}
		}
		return res;
	}
}