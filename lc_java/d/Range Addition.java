/**
 Assume you have an array of length n initialized with all 0's and are given k update operations.

 Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each element of subarray A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.

 Return the modified array after all k operations were executed.

 Example:

 Given:

 length = 5,
 updates = [
 [1,  3,  2],
 [2,  4,  3],
 [0,  2, -2]
 ]

 Output:

 [-2, 0, 3, 5, 3]
 **/

public class Solution {
	public int[] getModifiedArray(int length, int[][] updates) {
		int[] nums = new int[length];
		for (int[] update : updates) {
			nums[update[0]] += update[2];
			if (update[1] + 1 < length)
				nums[update[1] + 1] -= update[2];
		}
		int inc = 0;
		for (int i = 0; i < length; i++) {
			int updatedInc = nums[i];
			nums[i] += inc;
			inc += updatedInc;
		}
		return nums;
	}
}