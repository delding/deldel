/**
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
Ensure that numbers within the set are sorted in ascending order.

Example 1:

Input: k = 3, n = 7

Output:

[[1,2,4]]

Example 2:

Input: k = 3, n = 9

Output:

[[1,2,6], [1,3,5], [2,3,4]]
**/

public class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> rst = new ArrayList<List<Integer>>();
        List<Integer> numbers = new ArrayList<Integer>();
        dfs(k, n, 1, rst, numbers);
        return rst;
    }
    
    private void dfs(int k, int n, int start, List<List<Integer>> rst, List<Integer> numbers) {
        if (k == 0) {
            if (n == 0) {
                rst.add(new ArrayList<Integer>(numbers));
            }
            return;
        }
        for (int i = start; i <=9; i++) {
            numbers.add(i);
            dfs(k-1, n-i, i + 1, rst, numbers);
            numbers.remove(numbers.size() -1);
        }
    }
}


