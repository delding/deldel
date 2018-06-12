/**
 Given a sorted array of integers nums and integer values a, b and c. Apply a function of the form f(x) = ax2 + bx + c to each element x in the array.

 The returned array must be in sorted order.

 Expected time complexity: O(n)

 Example:
 nums = [-4, -2, 2, 4], a = 1, b = 3, c = 5,

 Result: [3, 9, 15, 33]

 nums = [-4, -2, 2, 4], a = -1, b = 3, c = 5

 Result: [-23, -5, 1, 7]
 **/

public class Solution {
	public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
		if (a == 0) {
			if (b < 0) {
				int i = 0;
				int j = nums.length - 1;
				while (i < j) {
					int tmp = nums[i];
					nums[i++] = nums[j];
					nums[j--] = tmp;
				}
			}
			for (int i = 0; i < nums.length; i++) {
				nums[i] = a * nums[i] * nums[i] + b * nums[i] + c;
			}
			return nums;
		}
		// can instead start from left and right ends as two pointers,
		// in this case no need to find two pointers in the middle
		double target = -b / 2 / (double) a;
		int[] mids = binarySearch(nums, 0, nums.length - 1, target);
		int start = a > 0 ? 0 : nums.length - 1;
		int step = a > 0 ? 1 : -1;
		int l = mids[0];
		int r = mids[1];
		int[] ret = new int[nums.length];
		while (l >= 0 || r < nums.length) {
			if (l < 0) {
				ret[start] = a * nums[r] * nums[r] + b * nums[r] + c;
				r++;
			} else if (r >= nums.length) {
				ret[start] = a * nums[l] * nums[l] + b * nums[l] + c;
				l--;
			} else {
				int leftTrans = a * nums[l] * nums[l] + b * nums[l] + c;
				int rightTrans = a * nums[r] * nums[r] + b * nums[r] + c;
				int selected = step > 0 ? Math.min(leftTrans, rightTrans) : Math.max(leftTrans, rightTrans);
				ret[start] = selected;
				if (selected == leftTrans) l--;
				else r++;
			}
			start += step;
		}
		return ret;
	}

	// find middle indexes to the left and right of target
	int[] binarySearch(int[] nums, int i, int j, double target) {
		if (Math.abs(i - j) == 1) {
			return new int[] {Math.min(i, j), Math.max(i, j)};
		}
		int mid = (i + j) / 2;
		if (target >= nums[mid]) return binarySearch(nums, mid, j, target);
		else return binarySearch(nums, i, mid, target);
	}
}