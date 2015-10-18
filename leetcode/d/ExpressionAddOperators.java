/**
 * Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

Examples: 
"123", 6 -> ["1+2+3", "1*2*3"] 
"232", 8 -> ["2*3+2", "2+3*2"]
"105", 5 -> ["1*0+5","10-5"]
"00", 0 -> ["0+0", "0-0", "0*0"]
"3456237490", 9191 -> []
* */

public class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> rst = new ArrayList<String>();
        if(num == null || num.length() == 0) return rst;
        helper(rst, "", num, target, 0, 0, 0);
        return rst;
    }
    public void helper(List<String> rst, String path, String num, int target, int pos, long eval, long multed){
        if(pos == num.length()){
            if(target == eval)
                rst.add(path);
            return;
        }
        for(int i = pos; i < num.length(); i++){
            if(i != pos && num.charAt(pos) == '0') break;
            long cur = Long.parseLong(num.substring(pos, i + 1));
            if(pos == 0){
                helper(rst, path + cur, num, target, i + 1, cur, cur);
            }
            else{
                helper(rst, path + "+" + cur, num, target, i + 1, eval + cur , cur);

                helper(rst, path + "-" + cur, num, target, i + 1, eval -cur, -cur);

                helper(rst, path + "*" + cur, num, target, i + 1, eval - multed + multed * cur, multed * cur );
            }
        }
    }
}

/*
public class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> results = new ArrayList<String>();
        if(num == null || num.length() == 0) return results;
        StringBuilder result = new StringBuilder();
        addOperators(num, 0, target, result, results, 0, -1, true);
        return results;
    }
    
    // idx is the first index of suffix string, value is the current evaluation of string equation
    // multiple != -1 means previous operator is '*', and multiple equals to previous value that needs to multple 
    private void addOperators(String num, int idx, int target, StringBuilder result, List<String> results, long value, long multiple, boolean isAdd) {
       if (idx == num.length()) {
           if ((int)value == target) {
               results.add(result.toString().substring(0, result.length()-1)); // remove last operator
           }
           return;
       }
       
       for (int i = idx; i < num.length(); i++) {
           if (num.charAt(idx) == '0' && i > idx) break; // same as use continue;
           
           String prefix = num.substring(idx, i+1);
           long prefixVal = Long.parseLong(prefix);
           
           result.append(prefix);
           if (multiple != -1) {
               prefixVal *= multiple;
               if (isAdd) {
                   value -= multiple;
               }
               else value += multiple;
           }
           
           result.append("+");
           if (isAdd) {
               addOperators(num, i+1, target, result, results, value + prefixVal, -1, true);
           } else {
               addOperators(num, i+1, target, result, results, value - prefixVal, -1, true);
           }
           result.deleteCharAt(result.length()-1);
               
           result.append("-");
           if (isAdd) {
               addOperators(num, i+1, target, result, results, value + prefixVal, -1, false);
           } else {
               addOperators(num, i+1, target, result, results, value - prefixVal, -1, false);
           }
           result.deleteCharAt(result.length()-1);
               
           result.append("*");
           if (isAdd) {
               addOperators(num, i+1, target, result, results, value + prefixVal, prefixVal, true);
           } else {
               addOperators(num, i+1, target, result, results, value - prefixVal, prefixVal, false);
           }
           result.deleteCharAt(result.length()-1);
           
           result.deleteCharAt(result.length()-1);
       }
    }
}
*/
