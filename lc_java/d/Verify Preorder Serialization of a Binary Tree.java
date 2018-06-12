/**
 One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value.
 If it is a null node, we record using a sentinel value such as #.

 _9_
 /   \
 3     2
 / \   / \
 4   1  #  6
 / \ / \   / \
 # # # #   # #
 For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.
 Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree.
 Find an algorithm without reconstructing the tree.
 Each comma separated value in the string must be either an integer or a character '#' representing null pointer.
 You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".

 Example 1:
 "9,3,4,#,#,1,#,#,2,#,6,#,#"
 Return true

 Example 2:
 "1,#"
 Return false

 Example 3:
 "9,#,#,1"
 Return false
 **/

public class Solution {
	// the serialized tree (treat # as leaf) is a full binary tree (i.e. every node has either 0 or 2 children).
	// so each inner node provides one indegree and two outdegree, each leaf provides one indegree, root provides two outdegree
	// number of leaf = number of inner node + 1.
	// So leaves provide (n + 1) indegree,  inner nodes (except root) provide (n - 1) outdegree, root provides 2 outdegree
	// Total diff is 0. For preorder traversal diff can never >= 0 before reaching the last leaf.
	public boolean isValidSerialization(String preorder) {
		int diff = 0; // total indegree - total outdegree
		diff--; // compensate for root so that root can be treated as normal inner node
		for (String c : preorder.split(",")) {
			if (diff >= 0) return false;
			if (!c.equals("#")) diff--;
			else diff++;
		}
		return diff == 0;
	}
}