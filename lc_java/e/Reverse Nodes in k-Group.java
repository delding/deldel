/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 * You may not alter the values in the nodes, only nodes itself may be changed.
 * Only constant memory is allowed.
 * For example,
 * Given this linked list: 1->2->3->4->5
 * For k = 2, you should return: 2->1->4->3->5
 * For k = 3, you should return: 3->2->1->4->5
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
  public ListNode reverseKGroup(ListNode head, int k) {
    ListNode dum = new ListNode(0);
    dum.next = head;
    ListNode pre = dum;
    while ((dum = reverse(dum, k)) != null) {}
    return pre.next;
  }

  ListNode reverse(ListNode dum, int k) {
    ListNode n = dum.next;
    for (int i = 0; i < k; i++) {
      if (n == null) return null;
      n = n.next;
    }
    n = dum.next;
    for (int i = 0; i < k - 1; i++) {
      ListNode next = n.next;
      n.next = next.next;
      next.next = dum.next;
      dum.next = next;
    }
    return n;
  }
}