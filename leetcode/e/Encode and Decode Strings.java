/**
 * Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.
 * <p>
 * Machine 1 (sender) has the function:
 * <p>
 * string encode(vector<string> strs) {
 * // ... your code
 * return encoded_string;
 * }
 * Machine 2 (receiver) has the function:
 * vector<string> decode(string s) {
 * //... your code
 * return strs;
 * }
 * So Machine 1 does:
 * <p>
 * string encoded_string = encode(strs);
 * and Machine 2 does:
 * <p>
 * vector<string> strs2 = decode(encoded_string);
 * strs2 in Machine 2 should be the same as strs in Machine 1.
 * <p>
 * Implement the encode and decode methods.
 * <p>
 * Note:
 * The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be generalized enough to work on any possible characters.
 * Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
 * Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode algorithm.
 */

public class Codec {
  // [del, ding] => 3#del4#ding
  // Encodes a list of strings to a single string.
  public String encode(List<String> strs) {
    StringBuilder sb = new StringBuilder();
    for (String s : strs) {
      int len = s.length();
      sb.append(len + "#" + s);
    }
    return sb.toString();
  }

  // Decodes a single string to a list of strings.
  public List<String> decode(String s) {
    List<String> strs = new LinkedList();
    int idx = 0;
    while (s.indexOf("#", idx) != -1) {
      int pondIdx = s.indexOf("#", idx);
      int len = Integer.parseInt(s.substring(idx, pondIdx));
      idx = pondIdx + 1 + len;
      strs.add(s.substring(pondIdx + 1, idx));
    }
    return strs;
  }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));
