/**
 * Given an index k, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space?
* */

public class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> ret = new ArrayList<Integer>();
        if (rowIndex == 0) {
            ret.add(1);
            return ret;
        }
        int[] pascal = new int[rowIndex + 1];
        pascal[0] = 1;
        pascal[1] = 1;
        for (int i = 1; i < rowIndex; i++) {
            for (int j = i - 1; j >= 0; j--) { // ERROR: Must j from i to 0, thus value in the array will not be overrided before use
                pascal[j + 1] = pascal[j] + pascal[j + 1];
            }
            pascal[i + 1] = 1;
        }
        
        for (int i : pascal) ret.add(i);
        return ret;
    }
}
