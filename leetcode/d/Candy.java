/**
 * There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?
* */

public class Solution {
    public int candy(int[] ratings) {
        int len = ratings.length;
        int[] candies = new int[len];
        Arrays.fill(candies, 1);
        for (int i = 0; i < len - 1; i++) {
            if (ratings[i] < ratings[i + 1]) {
                candies[i + 1] = candies[i] + 1;
            }
        }
        for (int i = len - 1; i > 0; i--) {
            if (ratings[i] < ratings[i - 1]) {
                candies[i - 1] = Math.max(candies[i - 1], candies[i] + 1);
            }
        }
        int min = 0;
        for (int candy : candies) min += candy;
        return min;
    }
}
