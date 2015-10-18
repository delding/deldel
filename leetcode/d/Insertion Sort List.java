/**
 * Sort a linked list using insertion sort.
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
    public ListNode insertionSortList(ListNode head) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = head;
        while (curr.next != null) {
            ListNode prev = dummy;
            while (prev != curr && prev.next.val <= curr.next.val) { // ERROR: prev != curr not prev.next != curr
                prev = prev.next;
            }
            if (prev != curr) {
                ListNode small = curr.next;
                curr.next = small.next; // ERROR: if curr.next needs to be inserted, curr.next will update to next.next, so don't update curr again
                small.next = prev.next;
                prev.next = small;
            } else { 
                curr = curr.next;
            }
        }
        return dummy.next;
    }
}
