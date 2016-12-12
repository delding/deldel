/**
 Shuffle a set of numbers without duplicates.

 Example:

 // Init an array with set 1, 2, and 3.
 int[] nums = {1,2,3};
 Solution solution = new Solution(nums);

 // Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
 solution.shuffle();

 // Resets the array back to its original configuration [1,2,3].
 solution.reset();

 // Returns the random shuffling of array [1,2,3].
 solution.shuffle();
 **/

public class Solution {
	int[] nums;
	Random rand = new Random();
	public Solution(int[] nums) {
		this.nums = nums;
	}

	/** Resets the array to its original configuration and return it. */
	public int[] reset() {
		return nums;
	}

	/** Returns a random shuffling of the array. */
	public int[] shuffle() {
		int[] res = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			int r = rand.nextInt(i + 1);
			if (r != i) {
				res[i] = res[r];
				res[r] = nums[i];
			} else {
				res[i] = nums[i];
			}
		}
		return res;
	}
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */