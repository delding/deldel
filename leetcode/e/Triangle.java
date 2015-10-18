/**
Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
**/

public class Solution {
    // from bottom to top, parent choose the smaller of two children and add the value to parent self
    public int minimumTotal(List<List<Integer>> triangle) {
        int height = triangle.size();
        for (int i = height - 2; i >= 0; i--) {
            List<Integer> parents = triangle.get(i);
            List<Integer> children = triangle.get(i + 1);
            for (int j = 0; j < parents.size(); j++) {
                int val = parents.get(j) + Math.min(children.get(j), children.get(j + 1));
                parents.set(j, val);
            }
        }
        return triangle.get(0).get(0);
    }
}
