/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.
 * <p>
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 **/

public class LRUCache {
  private Node head = null;
  private Node tail = null;
  private int size = 0;
  private Map<Integer, Node> table = new HashMap(); // key to node
  private int capacity;

  public LRUCache(int capacity) {
    this.capacity = capacity;
  }

  public int get(int key) {
    Node node = table.get(key);
    if (node == null) return -1;
    refresh(node);
    return node.val;
  }

  public void set(int key, int value) {
    if (capacity == 0) return;
    Node node = table.get(key);
    if (node != null) {
      node.val = value;
      refresh(node);
    } else {
      node = new Node(key, value);
      table.put(key, node);
      if (size < capacity) {
        if (size == 0) tail = node;
        if (size > 0) head.prev = node; // ERROR: MUST SET HEAD PREV
        node.next = head;
        head = node;
        size++;
      } else { // size = capacity
        table.remove(tail.key);
        if (capacity == 1) {
          head = node;
          tail = node;
        } else {
          Node secondlast = tail.prev;
          tail.prev = null; // ERROR: SET PREV
          tail = secondlast;
          tail.next = null;
          node.next = head;
          head.prev = node; // ERROR: MUST SET PREV!!!
          head = node;
        }
      }
    }
  }

  private void refresh(Node node) { // move to top
    if (head == node) return;
    if (tail == node) {
      tail = node.prev;
      tail.next = null;
    } else {
      Node next = node.next;
      Node prev = node.prev;
      prev.next = next;
      next.prev = prev; // ERROR: MUST set next node's prev
    }
    node.next = head; // ERROR: node.next = head, not node.next = head.next
    head.prev = node; // ERROR: MUST SET HEAD's PREV
    node.prev = null;
    head = node;
  }

  private class Node {
    Node prev;
    Node next;
    int val;
    int key;

    public Node(int k, int v) {
      val = v;
      key = k;
    }
  }
}
