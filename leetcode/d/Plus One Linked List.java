/**
 Given a non-negative number represented as a singly linked list of digits, plus one to the number.

 The digits are stored such that the most significant digit is at the head of the list.

 Example:
 Input:
 1->2->3

 Output:
 1->2->4
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
	public ListNode plusOne(ListNode head) {
		int carry = plusone(head);
		if (carry != 0) {
			ListNode newHead = new ListNode(carry);
			newHead.next = head;
			head = newHead;
		}
		return head;
	}

	int plusone(ListNode head) {
		if (head == null) {
			return 1;
		} else {
			int carry = plusone(head.next);
			int val = (head.val + carry) % 10;
			carry = (head.val + carry) / 10;
			head.val = val;
			return carry;
		}
	}
}