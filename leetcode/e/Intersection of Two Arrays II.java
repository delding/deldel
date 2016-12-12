/**
 Given two arrays, write a function to compute their intersection.

 Example:
 Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].

 Note:
 Each element in the result should appear as many times as it shows in both arrays.
 The result can be in any order.
 Follow up:
 What if the given array is already sorted? How would you optimize your algorithm?
 What if nums1's size is small compared to nums2's size? Which algorithm is better?
 What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 Show Tags
 Show Similar Problems
**/

public class Solution {
	public int[] intersect(int[] nums1, int[] nums2) {
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		List<Integer> merge = new ArrayList<>();
		int i1 = 0;
		int i2 = 0;
		while (i1 < nums1.length && i2 < nums2.length) {
			if (nums1[i1] == nums2[i2]) {
				merge.add(nums1[i1++]);
				i2++;
			}
			else if (nums1[i1] < nums2[i2]) i1++;
			else i2++;
		}
		int[] ret = new int[merge.size()];
		for (int i = 0; i < merge.size(); i++) ret[i] = merge.get(i);
		return ret;
	}
	// Followup 3
	// If only nums2 cannot fit in memory, put all elements of nums1 into a HashMap, read chunks of array that
	// fit into the memory, and record the intersections.
	// If both nums1 and nums2 are so huge that neither fit into the memory, sort them individually (external sort),
	// then read 2 elements from each array at a time in memory, record intersections.
}