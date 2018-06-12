/**
 Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
 Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.

 Note:
 A naive algorithm of O(n2) is trivial. You MUST do better than that.

 Example:
 Given nums = [-2, 5, -1], lower = -2, upper = 2,
 Return 3.
 The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.
 **/

public class Solution {
	// Do a preprocessing to compute prefix sums. Then the problem is similar to inversion count.
	// Inversion count: count[i] = count of nums[j] - nums[i] < 0 with j > i
	// Range sum count: count[i] = count of a <= S[j] - S[i] <= b with j > i
	// Solution1 sort prefix sums first then run binary search for each element and finding the qualifying range and its size
	// Solution2 merge sort or build binary search tree while counting range size for each element
	// Solution3 binary index tree or segment tree

	// binary search solution
	// use TreeMap for binary search, key is prefix sum
	// since TreeMap don't allow duplicate key, use value as duplicate count for each element in the tree
	public int countRangeSum(int[] nums, int lower, int upper) {
		long[] prefixSum = new long[nums.length + 1]; // use long to avoid overflow of sums
		prefixSum[0] = 0;
		for (int i = 1; i <= nums.length; i++) {
			prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
		}
		int totalCount = 0;
		TreeMap<Long, Integer> bsTree = new TreeMap<>();
		for (long sum : prefixSum) {
			int count = bsTree.getOrDefault(sum, 0) + 1;
			bsTree.put(sum, count);
		}
		for (int i = 0; i < prefixSum.length - 1; i++) {
			long sum = prefixSum[i];
			int count = bsTree.get(sum); // duplication count for a prefix sum
			// traverse from left to right and always remove current element from tree, so the elements found in the tree
			// will always have larger indices, i.e., appears to the right of current element
			if (count == 1) {
				bsTree.remove(sum);
			} else {
				bsTree.put(sum, count - 1);
			}
			long lowVal = lower + sum; // lowVal - sum >= lower
			long highVal = upper + sum; // highVal - sum <= upper
			Long start = bsTree.ceilingKey(lowVal);
			Long end = bsTree.floorKey(highVal);
			if (start != null && end != null && start <= end) {
				// this step can be O(1) if write a binary search method to find most left and most right duplicate values
				for (int dupCount : bsTree.subMap(start, true, end, true).values()) { // true means inclusive
					totalCount += dupCount;
				}
			}
		}
		return totalCount;
	}

	// Need to write binary search to find most left and most right values in case of duplicate values occurs
	// Wrong: if prefixSum has duplicate values, builtin binarySearch doesn't help with finding the most left value
	// for start and finding the most right val for end
	public int countRangeSumWrongBinarySearch(int[] nums, int lower, int upper) {
		Integer[] cumulative = new Integer[nums.length + 1];
		cumulative[0] = 0;
		for (int i = 1; i <= nums.length; i++) {
			cumulative[i] = cumulative[i - 1] + nums[i - 1];
		}
		int count = 0;
		List<Integer> bsTree = new ArrayList<>(Arrays.asList(cumulative)); // must declare to be Integer[] for Arrays.asList to apply
		Collections.sort(bsTree); // ArrayList can contain duplicate
		for (int i = 0; i < cumulative.length - 1; i++) {
			int cum = cumulative[i];
			bsTree.remove((Integer)cum); // must remove
			int lowVal = lower + cum; // lowVal - cum >= lower
			int highVal = upper + cum; // highVal - cum <= upper
			int start = Collections.binarySearch(bsTree, lowVal);
			int end = Collections.binarySearch(bsTree, highVal);
			start = start >= 0 ? start : -(start + 1); // if not found, insertion index is ceiling
			end = end >= 0 ? end : -(end + 1) - 1; // if not found, insertion index - 1 is floor
			if (start < bsTree.size() && end >=0 && start <= end) {
				count += end - start + 1;
			}
		}
		return count;
	}
}