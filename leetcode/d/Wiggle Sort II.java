/**
 Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

 Example:
 (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
 (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].

 Note:
 You may assume all input has valid answer.

 Follow Up:
 Can you do it in O(n) time and/or in-place with O(1) extra space?
 **/

public class Solution {
	// find median, than three way partition by index mapping
	// Doesn't matter if change the order of elements in array when finding median,
	// because the final three way partition will always partition array into high value half and low value half
	public void wiggleSort(int[] nums) {
		// int[] tmp = Arrays.copyOf(nums, nums.length);
		int n = nums.length;
		int half = (n + 1) / 2;
		// int median = quickselect(0, n - 1, tmp, half);
		int median = quickselect(0, n - 1, nums, half);
		int l = 0;
		int h = n - 1;
		for (int i = 0; i <= h;) {
			if (nums[idx(i, n)] > median) swap(nums, idx(i++, n), idx(l++, n));
			else if (nums[idx(i, n)] == median) i++;
			else swap(nums, idx(i, n), idx(h--, n));
		}
	}

	int idx(int idx, int n) {
		return (2 * idx + 1) % (n | 1); // n | 1 means smallest odd number that is larger than or equal to n
	}

	void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

	int quickselect(int lo, int hi, int[] nums, int k) {
		int pivotVal = nums[lo];
		int pivotIdx = partition(lo, hi, nums, pivotVal);
		if (pivotIdx - lo + 1 == k) return pivotVal;
		else if (pivotIdx - lo + 1 > k) return quickselect(lo, pivotIdx - 1, nums, k);
		else return quickselect(pivotIdx + 1, hi, nums, k - (pivotIdx - lo + 1));
	}

	// three way partition
	int partition(int lo, int hi, int[] nums, int pivotVal) {
		int mid = lo;
		while (mid <= hi) {
			if (nums[mid] > pivotVal) { // large value on left half, because odd slot must be larger than even slot and left half is mapped to odd slot
				swap(nums, lo++, mid++);
			} else if (nums[mid] == pivotVal) mid++;
			else {
				swap(nums, mid, hi--);
			}
		}
		return lo;
	}
}