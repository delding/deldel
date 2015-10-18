/**
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

For example,

Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",

Return:
["AAAAACCCCC", "CCCCCAAAAA"].
* */

public class Solution {
    // base 4 hash value encoding for sliding window with length equals to 10
    public List<String> findRepeatedDnaSequences(String s) {
        // Map<Integer, String> occured = new HashMap(); // ERROR: memory limited exceeded
        Map<Integer, Integer> occurCount = new HashMap(); // store hashvalue and its count
        List<String> ret = new ArrayList<String>();
        int hashvalue = 0;
        for (int i = 0; i < s.length(); i++) {
            hashvalue = (4 * hashvalue + value(s.charAt(i))) & 0xfffff; // Error: must & 0xfffff, 10 bits base 4 is 20 bits base 2
            if (i >= 9) {
                Integer count = occurCount.get(hashvalue);
                if (count == null) occurCount.put(hashvalue, 1);
                else if (count == 1) {
                    ret.add(s.substring(i - 9, i + 1));
                    occurCount.put(hashvalue, 2);
                }
            }
        }
        return ret;
    }
    
    private int value(char c) {
        switch(c) {
            case 'A': return 0;  
            case 'C': return 1;  
            case 'G': return 2;  
            default: return 3;
        }
    }
}
