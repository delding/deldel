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
    Map<RandomListNode, RandomListNode> copied = new HashMap();
    RandomListNode dummy = new RandomListNode(0);
    dummy.next = head;
    RandomListNode dummyClone = new RandomListNode(0); // also use a dummy for clone, clone dummy.next and its random pointer, then update dummy.next
    RandomListNode ret = dummyClone;
    while (dummy.next != null) {
      RandomListNode next = dummy.next;
      RandomListNode clone = copied.get(next);
      if (clone == null) {
        clone = new RandomListNode(next.label);
        copied.put(next, clone);
      }
      dummyClone.next = clone; // clone next pointer
      if (next.random != null) { // ERROR: must check only if random pointer is not null, clone random pointer
        RandomListNode random = copied.get(next.random);
        if (random == null) {
          random = new RandomListNode(next.random.label);
          copied.put(next.random, random);
        }
        clone.random = random; // clone random pointer
      }
      dummy = dummy.next;
      dummyClone = dummyClone.next;
    }
    return ret.next;
  }
}
