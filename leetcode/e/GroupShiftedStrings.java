/**
 * Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], 
Return:

[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
Note: For the return value, each inner list's elements must follow the lexicographic order.
* */

public class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> rst = new ArrayList();
        Map<String, List<String>> map = new HashMap();
        for (String str : strings) {
            String key = computeKey(str);
            if (!map.containsKey(key)) map.put(key, new ArrayList<String>());
            map.get(key).add(str);
        }
        for (String key : map.keySet()) {
            List<String> list = map.get(key);
            Collections.sort(list);
            rst.add(list);
        }
        return rst;
    }
    
    private String computeKey(String str) { // shift to string starting with 'a' as key
        StringBuilder key = new StringBuilder();
        int offset = str.charAt(0) - 'a';
        for (char c : str.toCharArray()) {
            char cc;
            if (c - offset < 'a') cc = (char) (c - offset + 26); // ERROR: c - offset can be less than 'a'
            else cc = (char) (c - offset);
            key.append(cc);
        }
        return key.toString();
    }
}
