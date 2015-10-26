/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * <p>
 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 * <p>
 * You may not alter the values in the nodes, only nodes itself may be changed.
 * <p>
 * Only constant memory is allowed.
 * <p>
 * For example,
 * Given this linked list: 1->2->3->4->5
 * <p>
 * For k = 2, you should return: 2->1->4->3->5
 * <p>
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
    ListNode ret = dum;
    while (dum != null) {
      dum = reverseK(dum, k);
    }
    return ret.next;
  }

  ListNode reverseK(ListNode dum, int k) {
    ListNode cur = dum;
    int kk = k;
    while (cur.next != null && kk > 0) {
      cur = cur.next;
      kk--;
    }
    if (kk > 0) return null;
    cur = dum.next;
    while (--k > 0) {
      // if (cur == null || cur.next == null) return null; // list size < k, bug: don't check k while reverse, otherwise even if len < k nodes have been reversed
      ListNode ne = cur.next;
      cur.next = ne.next;
      ne.next = dum.next;
      dum.next = ne;
    }
    return cur;
  }
}