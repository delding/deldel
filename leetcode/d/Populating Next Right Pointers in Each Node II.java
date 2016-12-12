/**
 * Follow up for problem "Populating Next Right Pointers in Each Node".
 * What if the given tree could be any binary tree? Would your previous solution still work?
 * Note:
 * You may only use constant extra space.
 * For example,
 * Given the following binary tree,
 * 1
 * /  \
 * 2    3
 * / \    \
 * 4   5    7
 * After calling your function, the tree should look like:
 * 1 -> NULL
 * /  \
 * 2 -> 3 -> NULL
 * / \    \
 * 4-> 5 -> 7 -> NULL
 **/

/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */

public class Solution {

  // level order, O(1) space
  public void connect(TreeLinkNode root) {
    TreeLinkNode dummy = new TreeLinkNode(0);
    TreeLinkNode pre = dummy;
    while (root != null) {
      if (root.left != null) {
        pre.next = root.left;
        pre = pre.next;
      }
      if (root.right != null) {
        pre.next = root.right;
        pre = pre.next;
      }
      root = root.next;
      if (root == null) {
        root = dummy.next; // go next level
        dummy.next = null;
        pre = dummy;
      }
    }
  }

  // level order O(n)
  public void connect1(TreeLinkNode root) {
    if (root == null) return;
    Queue<TreeLinkNode> q = new ArrayDeque<>();
    q.add(root);
    while (!q.isEmpty()) {
      int size = q.size();
      while (size-- > 0) {
        TreeLinkNode n = q.poll();
        if (size == 0) n.next = null; // last node at current level
        else n.next = q.peek();
        if (n.left != null) q.add(n.left);
        if (n.right != null) q.add(n.right);
      }
    }
  }

  // dfs
  public void connect(TreeLinkNode root) {
    connect(root, null);
  }

  private void connect(TreeLinkNode node, TreeLinkNode parent) {
    if (node == null) return;
    while (parent != null) {
      // make sure left child doesn't assign next to itself, right child doesn't deal with its own parent
      if (parent.left != null && parent.left != node) {
        node.next = parent.left;
        break;
      } else if (parent.right != null) {
        node.next = parent.right;
        break;
      }
      parent = parent.next;
    }
    // must go right first, then go left, because next point to right, going right first makes sure next points are already set when go left
    connect(node.right, node.next);
    connect(node.left, node);
  }
}