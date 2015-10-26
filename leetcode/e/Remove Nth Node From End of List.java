/**
 * Given a linked list, remove the nth node from the end of list and return its head.
 * <p>
 * For example,
 * <p>
 * Given linked list: 1->2->3->4->5, and n = 2.
 * <p>
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 * Given n will always be valid.
 * Try to do this in one pass.
 **/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
  public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dum = new ListNode(0);
    dum.next = head;
    ListNode l = dum;
    ListNode r = dum;
    int k = n + 1;
    while (k-- > 0) r = r.next;
    while (r != null) {
      r = r.next;
      l = l.next;
    }
    l.next = l.next.next;
    return dum.next;
  }
}