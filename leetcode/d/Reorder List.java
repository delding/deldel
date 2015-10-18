/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.
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
    
    // take second half, reverse and interleave
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode rHead = slow.next;
        while (rHead.next != null) { // reverse
            ListNode newHead = rHead.next;
            rHead.next = newHead.next;
            newHead.next = slow.next;
            slow.next = newHead;
        }
        rHead = slow.next;
        slow.next = null; // cut left half
        ListNode curr = dummy;
        while (rHead != null) {
            curr.next = head;
            head = head.next; // ERROR: MUST update head immediately, if curr.next.next = rHead will make head.next = rHead, then head = head.next will make head = rHead
            curr.next.next = rHead;
            rHead = rHead.next;
            curr = curr.next.next; // ERROR: must update current node 
        }
        curr.next = head; // need this step if length is odd
        head = dummy.next;
    }
    
    
    
    public void reorderList2(ListNode head) {
        head = reorder(head);
    }
    // TLE: every recursion has a while loop  to find the last node, its O(n^2)
    // use memoization to remember all of second last nodes when running first recursion, use stack
    private ListNode reorder(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return head;
        ListNode secondlast = head;
        while (secondlast.next.next != null) secondlast = secondlast.next;
        ListNode last = secondlast.next;
        secondlast.next = null;
        last.next = head.next; // ERROR: I forget this
        head.next = last;
        
        last.next = reorder(last.next);
        return head;
    }
}
