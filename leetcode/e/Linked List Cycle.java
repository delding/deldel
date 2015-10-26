/**
 * Given a linked list, determine if it has a cycle in it.
 */

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
  public boolean hasCycle(ListNode head) {
    ListNode fast = head;
    ListNode slow = head;
    do {
      if (fast == null || fast.next == null) return false;
      fast = fast.next.next;
      slow = slow.next;
    } while (fast != slow);
    return true;
  }
}
