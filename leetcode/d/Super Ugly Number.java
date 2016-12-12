/**
 Write a program to find the nth super ugly number.

 Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k. For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence of the first 12 super ugly numbers given primes = [2, 7, 13, 19] of size 4.

 Note:
 (1) 1 is a super ugly number for any given primes.
 (2) The given numbers in primes are in ascending order.
 (3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
 **/

public class Solution {
	public int nthSuperUglyNumber(int n, int[] primes) {
		int[] indices = new int[primes.length];
		int[] uglies = new int[n];
		uglies[0] = 1;
		PriorityQueue<Pair> pq = new PriorityQueue<>(primes.length);
		for (int i = 0; i < primes.length; i++) {
			pq.add(new Pair(i, primes[i]));
		}
		int i = 1;
		while (i < n) {
			Pair smallest = pq.poll();
			uglies[i++] = smallest.val;
			int uglyToAdd;
			Pair pairToAdd;
			do {
				uglyToAdd = primes[smallest.primeIdx] * uglies[++indices[smallest.primeIdx]];
				pairToAdd = new Pair(smallest.primeIdx, uglyToAdd);
			} while (pq.contains(pairToAdd)); // need to check if pq already contains the ugly number
			pq.add(pairToAdd);
		}
		return uglies[n - 1];
	}

	static class Pair implements Comparable<Pair> {
		int primeIdx;
		int val;

		Pair(int primeIdx, int val) {
			this.primeIdx = primeIdx;
			this.val = val;
		}

		@Override
		public int compareTo(Pair that) {
			return this.val - that.val;
		}

		@Override
		public boolean equals(Object that) {
			return this.compareTo((Pair) that) == 0;
		}

		@Override
		public int hashCode() {
			return primeIdx ^ val;
		}
	}
}