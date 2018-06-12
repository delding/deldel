/**
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
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
  public ListNode reverseList(ListNode head) {
    if (head == null) return null;

    if (head.next == null) return head;
    ListNode tail = head.next;
    ListNode first = reverseList(tail);
    tail.next = head;
    head.next = null;
    return first;
  }
}
