/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 * */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode mid = dummy;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            mid = mid.next;
        }
        ListNode rHead = mid.next; // left is longer than right when length is odd
        mid.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(rHead);
        ListNode curr = dummy;
        while (left != null && right != null) {
            if (left.val <= right.val) {
                curr.next = left;
                left = left.next; // UPDATE left before update curr
                curr = curr.next;
            } else {
                curr.next = right;
                right = right.next;
                curr = curr.next;
            }
        }
        if (left == null) curr.next = right;
        else curr.next = left;
        return dummy.next;
    }
}
