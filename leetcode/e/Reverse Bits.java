/**
 * Reverse bits of a given 32 bits unsigned integer.

For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), return 964176192 (represented in binary as 00111001011110000010100101000000).

Follow up:
If this function is called many times, how would you optimize it?
* */

public class Solution {
    // you need treat n as an unsigned value
    
    static char[] table = new char[] {0,8,4,12,2,10,6,14,1,9,5,13,3,11,7,15}; // reverse table for all 4 bits int, here use char for short int
    
    public int reverseBits(int n) {
        return divideConquer(n , 32);
    }
    
    public int divideConquer(int n, int m) { // m is the number of digits for n
        if (m == 4) { // check looup table
            return table[n];
        }
        int hmask = 0;
        for (int i = 0; i < m / 4 - 1; i++) {
            if (i < m / 2 / 4)
                hmask |= 0xf;
            hmask = hmask << 4;
        }
        int lmask = 0;
        for (int i = 0; i < m / 2 / 4; i++) {
            lmask = lmask << 4;
            lmask |= 0xf;
        }
        int hi = divideConquer((n & hmask) >>> (m / 2), m / 2);
        int lo = divideConquer(n & lmask, m / 2); 
        return lo << (m / 2) + hi; // merge step, reverse low and hi
    }
    
    
    // solu 2
    public int reverseBits1(int n) {
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            int bit = (n >> i) & 1;
            ret <<= 1;
            ret |= bit;
        }
        return ret;
    }
    
    // solu 3
    public int swapBits(int n, int i, int j) {
	    int a = (n >> i) & 1;
	    int b = (n >> j) & 1;
 
	    if ((a ^ b) != 0) {
		    return n ^= (1 << i) | (1 << j); // flip, xor 0 no change, xor 1 flip itself
	    }
 
	    return n;
    }
}
