/**
 * Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm?
 * */

public class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        if (n==0) return 0;
        int lo = 0;
        int hi = citations.length - 1;
        while (lo <= hi) { // the number of paper to the right of hi, is the valid number of paper that contribute to hindex, i.e. n - hi - 1
            int mid = lo + (hi - lo) / 2;
            if (citations[mid] == n - mid) return n - mid; 
            else if (citations[mid] > n - mid) hi = mid - 1; // all paper from mid to n -1 contribute to the hindex
            else lo = mid + 1; 
        }
       return n - hi - 1;
    }
}
