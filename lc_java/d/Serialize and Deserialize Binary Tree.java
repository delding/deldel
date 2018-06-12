/**
 Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or
 memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization
 algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized
 to the original tree structure.
 For example, you may serialize the following tree
 1
 / \
 2   3
 / \
 4   5
 as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to follow this format,
 so please be creative and come up with different approaches yourself.
 **/

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
  // iterative, level order
  public String serialize(TreeNode root) {
    if (root == null) return "\0";
    Queue<TreeNode> q = new ArrayDeque<>();
    StringBuilder sb = new StringBuilder();
    q.add(root);
    sb.append(root.val + ",");
    while (!q.isEmpty()) {
      TreeNode n = q.poll();
      if (n.left == null) {
        sb.append("\0,");
      } else {
        sb.append(n.left.val + ",");
        q.add(n.left);
      }
      if (n.right == null) {
        sb.append("\0,");
      } else {
        sb.append(n.right.val + ",");
        q.add(n.right);
      }
    }
    return sb.toString().substring(0, sb.length() - 1);
  }

  public TreeNode deserialize(String data) {
    if (data.equals("\0")) return null;
    Queue<TreeNode> q = new ArrayDeque<>();
    Iterator<String> it = Arrays.asList(data.split(",")).iterator();
    TreeNode root = new TreeNode(Integer.parseInt(it.next()));
    q.add(root);
    while (!q.isEmpty()) {
      TreeNode n = q.poll();
      String left = it.next();
      String right = it.next();
      if (!left.equals("\0")) {
        TreeNode l = new TreeNode(Integer.parseInt(left));
        n.left = l;
        q.add(l);
      }
      if (!right.equals("\0")) {
        TreeNode r = new TreeNode(Integer.parseInt(right));
        n.right = r;
        q.add(r);
      }
    }
    return root;
  }

  // recursive
  // Encodes a tree to a single string.
  public String serialize1(TreeNode root) {
    StringBuilder sb = new StringBuilder();
    preorder(root, sb);
    return sb.toString().substring(0, sb.length() - 1);
  }
  // pre-order traversal
  void preorder(TreeNode n, StringBuilder sb) {
    if (n == null) sb.append("\0,");
    else {
      sb.append(n.val + ",");
      preorder(n.left, sb);
      preorder(n.right, sb);
    }
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize1(String data) {
    return preorder(Arrays.asList(data.split(",")).iterator());
  }

  TreeNode preorder(Iterator<String> it) {
    String val = it.next();
    if (val.equals("\0")) return null;
    else {
      TreeNode curr = new TreeNode(Integer.parseInt(val));
      curr.left = preorder(it);
      curr.right = preorder(it);
      return curr;
    }
  }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));