/**
 * There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.
* */

public class Solution {
    public int minCost(int[][] costs) {
        int n = costs.length;
        if (n==0) return 0;
        int[][] minCosts = new int[n][3];
        for (int j=0; j < 3; j++){
            minCosts[0][j] = costs[0][j];
        }
        for (int i=1; i<n; i++) {
            for (int j=0; j<3; j++) {
                minCosts[i][j] = Math.min(minCosts[i-1][(j+1)%3], minCosts[i-1][(j+2)%3]) + costs[i][j];
            }
        }
        int rst = minCosts[n-1][0];
        rst = Math.min(rst, Math.min(minCosts[n-1][1], minCosts[n-1][2]));
        return rst;
    }
}
