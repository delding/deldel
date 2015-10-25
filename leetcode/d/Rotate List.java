/**
 * Given a list, rotate the list to the right by k places, where k is non-negative.
 * <p>
 * For example:
 * Given 1->2->3->4->5->NULL and k = 2,
 * return 4->5->1->2->3->NULL.
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
  public ListNode rotateRight(ListNode head, int k) {
    if (head == null) return head;
    ListNode dum = new ListNode(0);
    dum.next = head;
    ListNode cur = dum;
    int len = 0;
    while (cur.next != null) {
      len++;
      cur = cur.next;
    }
    k %= len;
    k = (len - k) % len; // bug: should skip len - k nodes, not k nodes, and must mode len again in case len - k = len
    cur = dum;
    while (k-- > 0) cur = cur.next;
    ListNode h = cur.next;
    cur.next = null;
    cur = h;
    while (cur.next != null) cur = cur.next;
    cur.next = dum.next;
    return h;
  }
}

// another solution, use two pointers
public class Solution {
  public ListNode rotateRight(ListNode head, int k) {
    if (head == null) return head;
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode tmp = head;
    int len = 0;
    while (tmp != null) {
      len++;
      tmp = tmp.next;
    }
    k = k % len;
    if (k == 0) return head; // note this edge case
    ListNode tail = head;
    while (k > 1) {
      tail = tail.next;
      k--;
    }
    while (tail.next != null) {
      tail = tail.next;
      dummy = dummy.next;
    }
    tail.next = head;
    ListNode newHead = dummy.next;
    dummy.next = null;
    return newHead;
  }
}