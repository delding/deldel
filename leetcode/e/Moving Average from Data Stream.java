/**
 Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

 For example,
 MovingAverage m = new MovingAverage(3);
 m.next(1) = 1
 m.next(10) = (1 + 10) / 2
 m.next(3) = (1 + 10 + 3) / 3
 m.next(5) = (10 + 3 + 5) / 3
 **/

public class MovingAverage {

	Queue<Integer> window = new ArrayDeque<>();
	int size;
	int sum = 0;
	/** Initialize your data structure here. */
	public MovingAverage(int size) {
		this.size = size;
	}

	public double next(int val) {
		sum += val;
		window.add(val);
		if (window.size() > size) sum -= window.poll();
		return (double) sum / window.size();
	}
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */