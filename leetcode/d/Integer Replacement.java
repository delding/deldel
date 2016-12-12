/**
 Given a positive integer n and you can do operations as follow:

 If n is even, replace n with n/2.
 If n is odd, you can replace n with either n + 1 or n - 1.
 What is the minimum number of replacements needed for n to become 1?

 Example 1:

 Input:
 8

 Output:
 3

 Explanation:
 8 -> 4 -> 2 -> 1
 Example 2:

 Input:
 7

 Output:
 4

 Explanation:
 7 -> 8 -> 4 -> 2 -> 1
 or
 7 -> 6 -> 3 -> 2 -> 1
 Show Company Tags
 Show Tags
**/

public class Solution {
	public int integerReplacement(int n) {
		Queue<Long> q = new ArrayDeque<>();
		int count = 0;
		q.offer((long) n);
		while (!q.isEmpty()) {
			int size = q.size(); // must copy q.size() to local variable, since q.size() will change afterwards
			for (int i = 0; i < size; i++) {
				long num = q.poll();
				if (num == 1) return count;
				if (num % 2 == 0) q.offer(num / 2);
				else {
					q.offer(num + 1); // long because num+1, num-1 can overflow
					q.offer(num - 1);
				}
			}
			count++;
		}
		return -1;
	}
}