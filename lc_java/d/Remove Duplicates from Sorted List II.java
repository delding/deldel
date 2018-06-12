/**
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
 * <p>
 * For example,
 * Given 1->2->3->3->4->4->5, return 1->2->5.
 * Given 1->1->1->2->3, return 2->3.
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
  public ListNode deleteDuplicates1(ListNode head) {
    if (head == null) return null;
    ListNode dummy = new ListNode(0);
    ListNode dummy1 = dummy;
    int val = head.val;
    while (head != null && head.next != null) {
      if (head.next.val != val) {
        dummy1.next = head;
        dummy1 = dummy1.next;

      } else {
        while (head.next != null && head.next.val == val) {
          head = head.next;
        }
      }
      head = head.next;
      if (head != null) // ERROR: must check
        val = head.val;
    }
    dummy1.next = head; // ERROR: MUST set to head, head could be the last distinct node
    return dummy.next;
  }

  // more concise
  public ListNode deleteDuplicates(ListNode head) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode pp = dummy;
    ListNode p = head;
    while (p != null) {
      ListNode q = p.next;
      while (q != null && q.val == p.val) q = q.next;
      if (p.next == q) { // means p.val is distinct
        pp = p;
      } else {
        pp.next = q; // p.val is not distinct, modify pp.next from p to q
      }
      p = q;
    }
    return dummy.next;
  }
}
