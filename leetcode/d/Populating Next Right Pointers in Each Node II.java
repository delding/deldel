/**
 * Follow up for problem "Populating Next Right Pointers in Each Node".
 * <p>
 * What if the given tree could be any binary tree? Would your previous solution still work?
 * <p>
 * Note:
 * <p>
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
    // ERROR: Tricky Must go right first, then go left, because next point to right, going right first makes sure next points are already set when go left
    connect(node.right, node.next);
    connect(node.left, node);
  }

  // method 2: instead of pass parent point, handle chilren when visiting parent node, also must visit right first
  public void connect2(TreeLinkNode root) {
    // Note: The Solution object is instantiated only once and is reused by each test case.
    if (root == null) {
      return;
    }
    TreeLinkNode p = root.next;

    while (p != null) {
      if (p.left != null) {
        p = p.left;
        break;
      }
      if (p.right != null) {
        p = p.right;
        break;
      }
      p = p.next;
    }

    if (root.right != null) {
      root.right.next = p;
    }

    if (root.left != null) {
      root.left.next = root.right == null ? p : root.right;
    }

    connect(root.right);
    connect(root.left);
  }
}
