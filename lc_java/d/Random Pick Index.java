/**
 Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.

 Note:
 The array size can be very large. Solution that uses too much extra space will not pass the judge.

 Example:

 int[] nums = new int[] {1,2,3,3,3};
 Solution solution = new Solution(nums);

 // pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
 solution.pick(3);

 // pick(1) should return 0. Since in the array only nums[0] is equal to 1.
 solution.pick(1);
 **/

public class Solution {
	Random rand = new Random();
	Map<Integer, List<Integer>> numMap = new HashMap<>(); // solu1
	int[] nums; // reservoir sampling
	public Solution(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			List<Integer> idx = numMap.get(nums[i]);
			if (idx == null) {
				idx = new ArrayList<Integer>();
				numMap.put(nums[i], idx);
			}
			idx.add(i);
		}
		this.nums = nums;
	}

	public int pick1(int target) {
		List<Integer> idx = numMap.get(target);
		return idx.get(rand.nextInt(idx.size()));
	}

	// reservoir
	public int pick(int target) {
		int idx = -1;
		for (int i = 0, count = 0; i < nums.length; i++) {
			if (nums[i] == target) {
				count++;
				if (rand.nextInt(count) == 0) idx = i;
			}
		}
		return idx;
	}

}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */