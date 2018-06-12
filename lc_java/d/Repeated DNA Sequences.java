/**
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
 * <p>
 * Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
 * <p>
 * For example,
 * <p>
 * Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
 * <p>
 * Return:
 * ["AAAAACCCCC", "CCCCCAAAAA"].
 */

public class Solution {
  // base 4 hash value encoding for sliding window with length equals to 10
  public class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
      Set<String> res = new HashSet<>();
      Set<Integer> vals = new HashSet<>();
      String base4 = "ACGT";
      int num = 0;
      for (int i = 0; i < s.length(); i++) {
        num = (4 * num + base4.indexOf(s.charAt(i))) & 0xfffff; // 10 bits base 4 is 20 bits base 2
        if (i >= 9) {
          if (vals.contains(num)) res.add(s.substring(i - 9, i + 1));
          else vals.add(num);
        }
      }
      return new ArrayList<>(res);
    }
  }
}
