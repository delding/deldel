import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value.
 * So the median is the mean of the two middle value.
 * <p>
 * Examples:
 * [2,3,4] , the median is 3
 * <p>
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * <p>
 * Design a data structure that supports the following two operations:
 * <p>
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 * For example:
 * <p>
 * add(1)
 * add(2)
 * findMedian() -> 1.5
 * add(3)
 * findMedian() -> 2
 **/

class MedianFinder {

  PriorityQueue<Integer> maxheap = new PriorityQueue<>(1, Collections.reverseOrder());
  PriorityQueue<Integer> minheap = new PriorityQueue<>();

  // Adds a number into the data structure.
  public void addNum(int num) {
    if (maxheap.isEmpty()) maxheap.offer(num);
    else {
      if (maxheap.size() > minheap.size()) {
        int top = maxheap.poll();
        if (top >= num) {
          minheap.offer(top);
          maxheap.offer(num);
        } else {
          minheap.offer(num);
          maxheap.offer(top);
        }
      } else {
        int top = minheap.poll();
        if (top >= num) {
          minheap.offer(top);
          maxheap.offer(num);
        } else {
          maxheap.offer(top);
          minheap.offer(num);
        }
      }
    }
  }

  // Returns the median of current data stream
  public double findMedian() {
    if (maxheap.size() > minheap.size()) return (double) maxheap.peek();
    else {
      return ((double) maxheap.peek() + (double) minheap.peek()) / 2;
    }
  }
}

// Your MedianFinder object will be instantiated and called as such:
// MedianFinder mf = new MedianFinder();
// mf.addNum(1);
// mf.findMedian();