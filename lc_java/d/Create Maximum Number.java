/**
 Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length
 k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return
 an array of the k digits. You should try to optimize your time and space complexity.

 Example 1:
 nums1 = [3, 4, 6, 5]
 nums2 = [9, 1, 2, 5, 8, 3]
 k = 5
 return [9, 8, 6, 5, 3]

 Example 2:
 nums1 = [6, 7]
 nums2 = [6, 0, 4]
 k = 5
 return [6, 7, 6, 0, 4]

 Example 3:
 nums1 = [3, 9]
 nums2 = [8, 9]
 k = 3
 return [9, 8, 9]
 **/

public class Solution {
	// To create max number of length k from two arrays, you need to create max number of length i from array one and max
	// number of length k-i from array two, then combine them together. After trying all possible i, you will get the max
	// number created from two arrays.
	public int[] maxNumber(int[] nums1, int[] nums2, int k) {
		int[] max = null;
		// k - len1 <= nums2.length, so len1 >= k - nums2.length
		for (int len1 = Math.max(0, k - nums2.length); len1 <= Math.min(k, nums1.length); len1++) {
			int len2 = k - len1;
			int[] maxNums1 = maxNum(nums1, len1);
			int[] maxNums2 = maxNum(nums2, len2);
			int[] merge = merge(maxNums1, maxNums2, k);
			if (max == null || biggerThan(0, merge, 0, max)) max = merge;
		}
		return max;
	}

	int[] maxNum(int[] nums, int k) { // can instead use an array to simulate stack
		Deque<Integer> stack = new ArrayDeque<>();
		for (int i = 0; i < nums.length; i++) {
			while (!stack.isEmpty() && stack.peek() < nums[i] && stack.size() + nums.length - i - 1 >= k) stack.pop();
			if (stack.size() < k) stack.push(nums[i]);
		}
		int[] res = new int[stack.size()]; // nums.length could be less than k
		for (int i = 0; i < res.length; i++) res[i] = stack.pollLast();
		return res;
	}

	boolean biggerThan(int i, int[] nums1, int j, int[] nums2) {
		while(i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
			i++;
			j++;
		}
		return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]); // if equal the array with longer length wins
	}

	int[] merge(int[] nums1, int[] nums2, int k) {
		int[] res = new int[k];
		for (int i = 0, i1 = 0, i2 = 0; i < k; i++) {
			if (biggerThan(i1, nums1, i2, nums2)) res[i] = nums1[i1++];
			else res[i] = nums2[i2++];
		}
		return res;
	}
}
