/**
 You are given two linked lists representing two non-negative numbers. The digits are stored in reverse
 order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

 Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 0 -> 8
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
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    int carry = 0;
    ListNode dum = new ListNode(0);
    ListNode h = dum;
    while(l1 != null || l2 != null) {
      int v1 = l1 == null ? 0 : l1.val;
      int v2 = l2 == null ? 0 : l2.val;
      int v = v1 + v2 + carry;
      carry = v / 10;
      v %= 10;
      ListNode cur = new ListNode(v);
      h.next = cur;
      h = h.next;
      l1 = l1 == null ? null : l1.next; // bug: update l1 and l2
      l2 = l2 == null ? null : l2.next;
    }
    if (carry != 0) {
      h.next = new ListNode(carry);
    }
    return dum.next;
  }
}