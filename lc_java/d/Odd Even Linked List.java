/**
 given a singly linked list, group all odd nodes together followed by the even nodes. please note here we are talking
 about the node number and not the value in the nodes.
 you should try to do it in place. the program should run in o(1) space complexity and o(nodes) time complexity.

 example:
 given 1->2->3->4->5->null,
 return 1->3->5->2->4->null.

 note:
 the relative order inside both the even and odd groups should remain as it was in the input.
 the first node is considered odd, the second node even and so on ...
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
	public ListNode oddEvenList(ListNode head) {
		if (head == null) return null;
		ListNode odd = head;
		ListNode even = head.next;
		ListNode evenHead = even;
		while (even != null && even.next != null) {
			odd.next = odd.next.next;
			even.next = even.next.next;
			odd = odd.next;
			even = even.next;
		}
		odd.next = evenHead;
		return head;
	}
}

// memory limit exceeded
public class Solution {
	public ListNode oddEvenList(ListNode head) {
		ListNode oddDummy = new ListNode(0);
		ListNode odd = oddDummy;
		ListNode evenDummy = new ListNode(0);
		ListNode even = evenDummy;
		boolean isOdd = true;
		while (head != null) {
			if (isOdd) {
				odd.next = head;
				odd = odd.next;
			} else {
				even.next = head;
				even = even.next;
			}
			isOdd = !isOdd;
			head = head.next;
		}
		odd.next = evenDummy.next;
		return oddDummy.next;
	}
}