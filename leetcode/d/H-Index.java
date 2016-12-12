/**
 * Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.
 * According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each,
 * and the other N − h papers have no more than h citations each."
 * For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them had
 * received 3, 0, 6, 1, 5 citations respectively. Since the researcher has 3 papers with at least 3 citations each and the remaining two
 * with no more than 3 citations each, his h-index is 3.
 * Note: If there are several possible values for h, the maximum one is taken as the h-index.
 */


// count array makes sum become number of items whose value >= the slot's value, and hindex require this sum also >= slot's value
public class Solution {

	// nlogn
	public int hIndex(int[] citations) {
		Arrays.sort(citations);
		int h = 0;
		for (int i = citations.length - 1; i >= 0; i--) {
			if (citations[i] >= h + 1) {
				h++;
			}
		}
		return h;
	}

	// count
	public int hIndex(int[] citations) {
		int n = citations.length;
		int[] count = new int[n + 1];
		for (int c : citations) {
			if (c >= n) {
				count[n]++;
			} else {
				count[c]++;
			}
		}
		int h = 0;
		for (int i = n; i >= 0; i--) {
			while (i > h && count[i]-- > 0) { // while citation bigger than h, increase h
				h++;
			}
		}
		return h;
	}
}
