/**
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 * <p>
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * <p>
 * For example,
 * Given 1->4->3->2->5->2 and x = 3,
 * return 1->2->2->4->3->5.
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
  public ListNode partition(ListNode head, int x) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode pre = dummy;
    while (pre.next != null) {
      if (pre.next.val >= x) break; // find the first big node
      pre = pre.next;
    }
    if (pre.next != null) {
      ListNode smallPre = pre.next;
      while (smallPre.next != null) {
        if (smallPre.next.val < x) { // move all small nodes before big node
          ListNode small = smallPre.next;
          smallPre.next = small.next;
          small.next = pre.next;
          pre.next = small;
          pre = pre.next;
        } else {
          smallPre = smallPre.next;
        }
      }
    }
    return dummy.next;
  }
}
