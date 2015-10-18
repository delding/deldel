/**
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

For example,

Given numerator = 1, denominator = 2, return "0.5".
Given numerator = 2, denominator = 1, return "2".
Given numerator = 2, denominator = 3, return "0.(6)".
* */

public class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        boolean isMinus = false;
        long numer = (long)numerator;
        long denom = (long)denominator;
        if (numer * denom < 0) isMinus = true;
        numer = Math.abs(numer);
        denom = Math.abs(denom);
        long quot = numer / denom;
        long remain = numer % denom;
        String ret = isMinus ? "-" + quot : "" + quot;
        if (remain == 0) return ret;
        ret = ret + ".";
        Map<Long, Integer> index = new HashMap<Long, Integer>();
        String residual = "";
        while (remain != 0) {
            if (index.containsKey(remain)) {
                int idx = index.get(remain);
                residual = residual.substring(0, idx) + "(" + residual.substring(idx) + ")";
                return ret + residual;
            }
            index.put(remain, residual.length());
            remain *= 10;
            quot = remain / denom;
            residual += quot;
            remain = remain % denom;
        }
        return ret + residual;
    }
}
