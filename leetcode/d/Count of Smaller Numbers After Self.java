/**
 You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

 Example:

 Given nums = [5, 2, 6, 1]

 To the right of 5 there are 2 smaller elements (2 and 1).
 To the right of 2 there is only 1 smaller element (1).
 To the right of 6 there is 1 smaller element (1).
 To the right of 1 there is 0 smaller element.
 Return the array [2, 1, 1, 0].
 **/

public class Solution {

	// solution1: merge sort, counting inversion when merging
	static class Node {
		int idx;
		int count = 0;

		Node(int idx) {
			this.idx = idx;
		}

		// no need to define copy contructor for Arrays.copyOfRange
//		Node(Node n) {
//			idx = n.idx;
//			count = n.count;
//		}
	}
	public List<Integer> countSmallerMergeSort(int[] nums) {
		Node[] nodes = new Node[nums.length];
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = new Node(i);
		}
		mergeCount(nodes, nums);
		int[] counts = new int[nums.length];
		for (Node n : nodes) {
			counts[n.idx] = n.count;
		}
		List<Integer> res = new ArrayList<>();
		for (int cnt : counts) res.add(cnt);
		return res;
	}

	Node[] mergeCount(Node[] nodes, int[] nums) {
		if (nodes.length <= 1) return nodes;
		Node[] left = Arrays.copyOfRange(nodes, 0, nodes.length / 2);
		Node[] right = Arrays.copyOfRange(nodes, nodes.length / 2, nodes.length);
		left = mergeCount(left, nums);
		right = mergeCount(right, nums);
		merge(left, right, nodes, nums);
		return nodes;
	}

	void merge(Node[] left, Node[] right, Node[] res, int[] nums) {
		int count = 0;
		int l = 0;
		int r = 0;
		for (int i = 0; i < res.length; i++) {
			if (l == left.length) {
				res[i] = right[r++];
			} else if (r == right.length) {
				left[l].count += count;
				res[i] = left[l++];
			} else if (nums[right[r].idx] < nums[left[l].idx]) {
				count++;
				res[i] = right[r++]; // put a smaller right one into res, add one to count of all the remaining nodes in the left
			} else {
				left[l].count += count; // put left one when equal
				res[i] = left[l++];
			}
		}
	}

	// Solution2: build a binary tree for sorting inserting nodes by traversing array from right to left
	// TreeNode maintain a count of number of TreeNodes on left subtree.
	// Since the binary tree is not self balanced, O(n^2) for the worst case of the input: 6 5 4 3 2 1
	static class TreeNode {
		int val;
		int count; // count of nodes in left subtree
		TreeNode left;
		TreeNode right;

		TreeNode(int val, int count) {
			this.val = val;
			this.count = count;
		}
	}
	public List<Integer> countSmallerBinrayTree(int[] nums) {
		List<Integer> res = new ArrayList<>();
		TreeNode root = null;
		for (int i = nums.length - 1; i >= 0; i--) {
			int count = 0;
			if (root == null) {
				root = new TreeNode(nums[i], 0);
			} else {
				TreeNode n = root;
				while (true) {
					if (nums[i] > n.val) {
						count += (1 + n.count);
						if (n.right == null) {
							n.right = new TreeNode(nums[i], 0);
							break;
						} else {
							n = n.right;
						}
					} else { // nums[i] <= n.val
						n.count++; // node goes to left, so add one to current node's left subtree count
						if (n.left == null) {
							n.left = new TreeNode(nums[i], 0);
							break;
						} else {
							n = n.left;
						}
					}
				}
			}
			res.add(count);
		}
		Collections.reverse(res);
		return res;
	}
}