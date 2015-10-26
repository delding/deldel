/**
 Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or
 memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization
 algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 For example, you may serialize the following tree

 1
 / \
 2   3
 / \
 4   5
 as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to follow this format,
 so please be creative and come up with different approaches yourself.
 **/

import java.lang.StringBuilder;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    StringBuilder sb = new StringBuilder();
    serialize(root, sb);
    return sb.toString().substring(0, sb.length() - 1);
  }

  private void serialize(TreeNode root, StringBuilder sb) {
    if (root == null) {
      sb.append("#,");
      return;
    }
    sb.append(root.val + ",");
    serialize(root.left, sb);
    serialize(root.right, sb);
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    String[] s = data.split(",");
    int[] idx = {0};
    return deserialize(s, idx);
  }

  private TreeNode deserialize(String[] data, int[] idx) {
    if (data[idx[0]].equals("#")) { // bug: equals, not ==
      return null;
    }
    TreeNode root = new TreeNode(Integer.parseInt(data[idx[0]]));
    idx[0]++;
    root.left = deserialize(data, idx);
    idx[0]++;
    root.right = deserialize(data, idx);
    return root;
  }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));