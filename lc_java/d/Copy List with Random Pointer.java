/**
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
 * Return a deep copy of the list.
 */

/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
  public RandomListNode copyRandomList(RandomListNode head) {
    RandomListNode dum = new RandomListNode(0);
    RandomListNode pre = new RandomListNode(0);
    dum.next = head;
    Map<RandomListNode, RandomListNode> copied = new HashMap<>();
    while (dum.next != null) { // each iteraton clone next node: its label and its random
      dum = dum.next;
      RandomListNode n = copied.get(dum);
      if (n == null) {
        n = new RandomListNode(dum.label);
        copied.put(dum, n);
      }
      pre.next = n;
      pre = pre.next;
      if (dum.random != null) {
        RandomListNode r = copied.get(dum.random);
        if (r == null) {
          r = new RandomListNode(dum.random.label);
          copied.put(dum.random, r);
        }
        pre.random = r;
      }
    }
    return copied.get(head);
  }
}
