/**
 * Given a binary tree
 * <p>
 * struct TreeLinkNode {
 * TreeLinkNode *left;
 * TreeLinkNode *right;
 * TreeLinkNode *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 * <p>
 * Initially, all next pointers are set to NULL.
 * <p>
 * Note:
 * <p>
 * You may only use constant extra space.
 * You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
 * For example,
 * Given the following perfect binary tree,
 * 1
 * /  \
 * 2    3
 * / \  / \
 * 4  5  6  7
 * After calling your function, the tree should look like:
 * 1 -> NULL
 * /  \
 * 2 -> 3 -> NULL
 * / \  / \
 * 4->5->6->7 -> NULL
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
  public void connect(TreeLinkNode root) {
    connect(root, null);
  }

  private void connect(TreeLinkNode node, TreeLinkNode parent) {
    if (node == null) return;
    if (parent == null) node.next = null;
    else if (parent.right != node) node.next = parent.right;
    else if (parent.next == null) node.next = null;
    else node.next = parent.next.left;
    connect(node.left, node);
    connect(node.right, node);
  }

  // level order
  public void connect(TreeLinkNode root) {
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
}
