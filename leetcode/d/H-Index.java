/**
 * Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."

For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively. Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, his h-index is 3.

Note: If there are several possible values for h, the maximum one is taken as the h-index.
* */

// count array makes sum become number of items whose value >= the slot's value, and hindex require this sum also >= slot's value
public class Solution {
    // counting sort, and all value >= n can count in a single cell
    // count the number of each value, sum is the total number of items whose value >= index of count slot, count array use value as index
    public int hIndex(int[] citations) {
       int n = citations.length;
       int[] count = new int[n+1]; // hindex could be from 0 to n, total n+1 possible values
       for(int num : citations) {
           if(num >= n) ++count[n];
           else ++count[num];
       }
       for(int i = n, sum = 0; i > 0; --i) { // sum is the number of items whose value >= i
           sum += count[i]; // i is the value i.e. the number of citations, count[i] is the number of paper whose citation is i, sum is the total number of paper whose citations >= i 
           if(sum >= i) return i; // number needs to larger than value
       }
       return 0; // all zero citations, not even one paper has citation number >= 1
    }
}
