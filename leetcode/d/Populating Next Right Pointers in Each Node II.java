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

    connect2(root.right);
    connect2(root.left);
  }

  // todo, iterative solution, constant space
  public void connect(TreeLinkNode root) {
    if(root == null)
      return;

    TreeLinkNode lastHead = root;//prevous level's head
    TreeLinkNode lastCurrent = null;//previous level's pointer
    TreeLinkNode currentHead = null;//currnet level's head
    TreeLinkNode current = null;//current level's pointer

    while(lastHead!=null){
      lastCurrent = lastHead;

      while(lastCurrent!=null){
        //left child is not null
        if(lastCurrent.left!=null)    {
          if(currentHead == null){
            currentHead = lastCurrent.left;
            current = lastCurrent.left;
          }else{
            current.next = lastCurrent.left;
            current = current.next;
          }
        }

        //right child is not null
        if(lastCurrent.right!=null){
          if(currentHead == null){
            currentHead = lastCurrent.right;
            current = lastCurrent.right;
          }else{
            current.next = lastCurrent.right;
            current = current.next;
          }
        }

        lastCurrent = lastCurrent.next;
      }

      //update last head
      lastHead = currentHead;
      currentHead = null;
    }
  }
}
