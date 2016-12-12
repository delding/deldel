/**
 Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.

 Follow up:
 What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently without using extra space?

 Example:

 // Init a singly linked list [1,2,3].
 ListNode head = new ListNode(1);
 head.next = new ListNode(2);
 head.next.next = new ListNode(3);
 Solution solution = new Solution(head);

 // getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
 solution.getRandom();
 **/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// 1) Create an array reservoir[0..k-1] and copy first k items of stream[] to it.
// 2) Now one by one consider all items from (k+1)th item to nth item.
// 2a) Generate a random number from 0 to i where i is index of current item in stream[]. Let the generated random number is j
// 2b) If j is in range 0 to k-1, replace reservoir[j] with arr[i]
public class Solution {
	Random rand = new Random();
	ListNode head;
	/** @param head The linked list's head.
	Note that the head is guaranteed to be not null, so it contains at least one node. */
	public Solution(ListNode head) {
		this.head = head;
	}

	/** Returns a random node's value. */
	public int getRandom() {
		ListNode res = head;
		ListNode cur = head;
		int count = 1;
		while (cur.next != null) {
			cur = cur.next;
			count++;
			int r = rand.nextInt(count);
			if (r == 0) res = cur;
		}
		return res.val;
	}
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */