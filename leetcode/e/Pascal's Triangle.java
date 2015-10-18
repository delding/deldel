/**
 * Given numRows, generate the first numRows of Pascal's triangle.

For example, given numRows = 5,
Return

[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
**/

public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> pascal = new ArrayList<List<Integer>>();
        for (int i = 1; i <= numRows; i++) {
            List<Integer> level = new ArrayList<Integer>();
            List<Integer> prev = null;
            if (i > 2) prev = pascal.get(pascal.size() - 1);
            for (int j = 0; j < i; j++) {
                if (j == 0 || j == i - 1) level.add(1);
                else {
                    level.add(prev.get(j - 1) + prev.get(j));
                }
            }
            pascal.add(level);
        }
        return pascal;
    }
}
