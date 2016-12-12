/**
 * Given a singly linked list, determine if it is a palindrome.
 * <p>
 * Follow up:
 * Could you do it in O(n) time and O(1) space?
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
  // reverse second half then compare
  public boolean isPalindrome(ListNode head) {
    if (head == null || head.next == null) return true; // length = 1 edge case, which makes head of second half null
    ListNode f = head, s = head;
    while (f.next != null && f.next.next != null) {
      f = f.next.next;
      s = s.next;
    }
    ListNode h = s.next; // head of second half whether length is even or odd, but length must > 1
    while (h.next != null) {
      ListNode n = h.next;
      h.next = n.next;
      n.next = s.next;
      s.next = n;
    }
    s = s.next;
    while (s != null) {
      if (head.val != s.val) return false;
      head = head.next;
      s = s.next;
    }
    return true;
  }

  // recursive method, has bug...
  public boolean isPalindrome2(ListNode head) {
    ListNode left = head;
    return recurse(head, left);
  }

  public boolean recurse(ListNode right, ListNode left) {
    if (right == null) return true;
    boolean ret = recurse(right.next, left);
    if (ret && left.val == right.val) {
      left = left.next;
      return true;
    } else return false;
  }
}
