/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * <p>
 * For example,
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * <p>
 * Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
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
  public ListNode swapPairs(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode ret = null;
    ListNode dum = new ListNode(0);
    dum.next = head;
    while (dum.next != null && dum.next.next != null) {
      ListNode n1 = dum.next;
      ListNode n2 = n1.next;
      n1.next = n2.next;
      n2.next = n1;
      dum.next = n2;
      if (ret == null) ret = n2;
      dum = n1;
    }
    return ret;
  }
}