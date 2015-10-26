/**
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 * <p>
 * For example,
 * Given 1->1->2, return 1->2.
 * Given 1->1->2->3->3, return 1->2->3.
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
  public ListNode deleteDuplicates(ListNode head) {
    if (head == null) return null;
    ListNode curr = head;
    ListNode next = head.next;
    while (next != null) {
      if (next.val != curr.val) {
        curr.next = next;
        curr = curr.next;
      }
      next = next.next;
    }
    curr.next = null; // ERROR: must set curr.next in the case of list end with duplicates
    return head;
  }
}
