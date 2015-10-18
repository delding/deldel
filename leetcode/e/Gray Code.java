/**
 * The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
**/

public class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> graycodes = new ArrayList<Integer>();
        graycodes.add(0);
        if (n == 0) return graycodes;
        graycodes.add(1);
        int i = 1;
        while (++i <= n) {
            List<Integer> secondHalf = new ArrayList<Integer>(graycodes);
            Collections.reverse(secondHalf);
            int headVal = 1 << (i - 1);
            for (int j = 0; j < secondHalf.size(); j++) { // second half is reverse of first half with 1 << (i - 1) being added
                secondHalf.set(j, secondHalf.get(j) + headVal);
            }
            graycodes.addAll(secondHalf);
        }
        return graycodes;
    }
}
