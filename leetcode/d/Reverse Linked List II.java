/**
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list.
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
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode dummyCur = dummy; // ERROR: MUST keep dummy for return
        int k = n - m; // ERROR: n - m is the times of swaps, and must put here before m is changed
        while (--m > 0) dummyCur = dummyCur.next;
        ListNode curr = dummyCur.next;
        while (k-- > 0) {
            swap(dummyCur, curr);
        }
        return dummy.next;
    }
    
    private void swap(ListNode dummy, ListNode curr) { // swap curr.next to dummy.next
        if (curr.next == null) return;
        ListNode next = curr.next;
        curr.next = next.next;
        next.next = dummy.next;
        dummy.next = next;
    }
}
