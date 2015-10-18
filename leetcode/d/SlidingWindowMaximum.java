/**
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Therefore, return the max sliding window as [3,3,5,5,6,7].

Note: 
You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.

Follow up:
Could you solve it in linear time?
* */

public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 0) return new int[0];
        int[] max = new int[nums.length - k + 1]; // ERROR: only take max value after window is filled up
        Deque<Value> window = new LinkedList<Value>();
        for (int i = 0; i < nums.length; i++) {
            enqueue(window, k, nums[i], i);
            if (i >= k-1) {
                max[i - k + 1] = window.peekFirst().val;
            }
        }        
        return max;
    }
    
    private void enqueue(Deque<Value> q, int k, int value, int i) {
        if (q.isEmpty()) { // ERROR: don't forget empty queue case when initializaion
            q.add(new Value(value, i));
            return;
        }
        Value max = q.peekFirst();
        if (i - max.index == k) {
            q.pollFirst();
            max = q.peekFirst();
        }
        if(max == null || max.val <= value) {
            while(!q.isEmpty()) q.poll();
            q.add(new Value(value, i));
        } else {
            while(q.peekLast().val <= value) q.pollLast();
            q.addLast(new Value(value,i));
        }
    }
    
    private class Value {
        int val;
        int index;
        
        Value(int v, int i) {
            val = v; index = i;
        }
    }
}
