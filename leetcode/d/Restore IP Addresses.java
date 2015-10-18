/**
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.

For example:
Given "25525511135",

return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
* */

public class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> ips = new ArrayList<String>();
        dfs(s, ips, "", 1);
        return ips;
    }
    
    private void dfs(String s, List<String> ips, String ip, int part) {
        if (part == 4) {
            if (isValid(s)) ips.add(ip + s);
        } else {
            for (int i = 1; i <= Math.min(3, s.length()); i++) {
                String head = s.substring(0, i);
                if(isValid(head)) {
                    dfs(s.substring(i), ips, ip + head + ".", part + 1);
                }
            }
        }
    }
    
    private boolean isValid(String s) {
        if (s.length() > 3 || s.length() == 0) return false;
        if (s.charAt(0) == '0') return s.length() == 1;
        else {
            int val = Integer.parseInt(s);
            return (1 <= val && val <= 255);
        }
    }
} 
