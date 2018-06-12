/**
 There is a list of sorted integers from 1 to n. Starting from left to right, remove the first number and every other number afterward until you reach the end of the list.

 Repeat the previous step again, but this time from right to left, remove the right most number and every other number from the remaining numbers.

 We keep repeating the steps again, alternating left to right and right to left, until a single number remains.

 Find the last number that remains starting with a list of length n.

 Example:

 Input:
 n = 9,
 1 2 3 4 5 6 7 8 9
 2 4 6 8
 2 6
 6

 Output:
 6
 **/

public class Solution {
	public int lastRemaining(int n) {
		return lastRemaining(n, true);
	}

	int lastRemaining(int n, boolean fromLeft) {
		if (n == 1) return n;
		if (fromLeft) { // remaining: 2, 4, 6, ..., divided by 2 makes a subproblem, multiply by 2 when recovery
			return 2 * lastRemaining(n / 2, false);
		} else { // remove from right
			if (n % 2 == 1) return 2 * lastRemaining(n / 2, true); // even numbers remain, same as removing from left
			else return 2 * lastRemaining(n / 2, true) - 1; // remaining: 1, 3, 5, ..., (x + 1) / 2 makes a subproblem, multiply by 2 then substract 1 when recovery
		}
	}
}