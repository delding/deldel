/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
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
  public ListNode mergeKLists(ListNode[] lists) {
    if (lists.length == 0) return null;
    ListNode d = new ListNode(0);
    ListNode curr = d;
    PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
      public int compare(ListNode l1, ListNode l2) {
        return l1.val - l2.val;
      }
    });
    for (ListNode l : lists) {
      if (l != null) pq.offer(l);
    }
    while (!pq.isEmpty()) {
      ListNode l = pq.poll();
      curr.next = l;
      curr = curr.next;
      if (l.next != null) pq.offer(l.next);
    }
    return d.next;
  }
}