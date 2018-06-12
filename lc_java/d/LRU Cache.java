/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.
 * <p>
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 **/

public class LRUCache {

  Node<Integer, Integer> head;
  Node<Integer, Integer> tail;
  int cap;
  Map<Integer, Node<Integer, Integer>> cache = new HashMap<>();

  public LRUCache(int capacity) {
    cap = capacity;
  }

  public int get(int key) {
    Node<Integer, Integer> n = cache.get(key);
    if (n == null) return -1;
    else {
      moveToHead(n);
      return n.val;
    }
  }

  public void set(int key, int value) {
    Node<Integer, Integer> n = cache.get(key);
    if (n != null) {
      n.val = value;
      moveToHead(n);
    } else {
      n = new Node<>(key, value);
      if (cache.size() == 0) {
        tail = n;
      } else {
        head.prev = n;
        n.next = head;
      }
      head = n;
      cache.put(key, n); //
      if (cache.size() > cap) {
        cache.remove(tail.key);
        tail = tail.prev;
        tail.next = null;
      }
    }
  }

  void moveToHead(Node<Integer, Integer> n) {
    if (head != n) {
      n.prev.next = n.next;
      if (n.next != null) {
        n.next.prev = n.prev;
      } else {
        tail = n.prev;
      }
      n.prev = null;
      n.next = head;
      head.prev = n;
      head = n; //
    }
  }

  static final class Node<K, V> {
    K key;
    V val;
    Node<K, V> prev = null;
    Node<K, V> next = null;

    public Node(K k, V v) {
      key = k;
      val = v;
    }
  }
}

// LFU cache
public class LFUCache {
  class Node{
    int key;
    int value;
    Node prev;
    Node next;
    long frequency = 0;

    public Node(int key, int value){
      this.key = key;
      this.value = value;
    }
  }
  private HashMap<Integer, Node> map = new HashMap<>();
  private HashMap<Long, Node> frequencies = new HashMap<>();
  private int count = 0;
  private int capacity = 10;
  private long lowestFreq = 0;

  public LFUCache(int capacity) {
    this.capacity = capacity;
  }

  public Integer get(int key){
    Node node = getNode(key);
    if(node == null) return null;
    else return node.value;
  }

  private Node getNode(int key){
    if(!map.containsKey(key)){
      return null;
    }
    Node node = map.get(key);
    if(node.prev == null && node.next == null){ //only one node
      frequencies.remove(node.frequency);
      if(node.frequency == lowestFreq){
        lowestFreq++;
      }
    } else  {
      if(node.prev != null) node.prev.next = node.next;
      else frequencies.put(node.frequency, node.next);
      if(node.next != null) node.next.prev = node.prev;
    }
    node.frequency++;
    if(!frequencies.containsKey(node.frequency)){
      frequencies.put(node.frequency, node);
    } else {
      Node head = frequencies.get(node.frequency);
      node.next = head.next;
      if(head.next != null) head.next.prev = node;
      head.next = node;
      node.prev = head;
    }
    return node;
  }

  public void set(int key, int value){
    Node node = getNode(key);
    if(node != null){
      node.value = value;
      return;
    }
    node = new Node(key, value);
    if(count == capacity){
      //clean lowest frequency node;
      Node head = frequencies.get(lowestFreq);
      if(head != null){
        map.remove(head.key);
        head = head.next;
        count--;
      }
      if(head == null) frequencies.remove(lowestFreq);
      else frequencies.put(lowestFreq, head);
    }
    lowestFreq = 0;
    map.put(key, node);
    frequencies.put(node.frequency, node);
    count++;
  }
}