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

  public boolean isPalindrome(ListNode head) {
    if (head == null || head.next == null)
      return true; // ERROR: len must bigger than 1, otherwise reverse method will call null
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode fast = dummy;
    ListNode slow = dummy; // dummy enable slow at one node before middle when odd length, and less node of middle when even length
    while (fast.next != null && fast.next.next != null) { // fast.next != null means odd length, fast.next==null means even length
      fast = fast.next.next;
      slow = slow.next;
    }
    if (fast.next != null) slow = slow.next.next;
    else slow = slow.next;
    ListNode reverse = reverse(slow);
    while (reverse != null) {
      if (reverse.val != head.val) return false;
      reverse = reverse.next;
      head = head.next;
    }
    return true;
  }

  private ListNode reverse(ListNode head) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    while (head.next != null) {
      ListNode next = head.next;
      head.next = next.next;
      next.next = dummy.next;
      dummy.next = next;
    }
    return dummy.next;
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
