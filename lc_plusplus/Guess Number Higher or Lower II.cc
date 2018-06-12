// We are playing the Guess Game. The game is as follows:
//
// I pick a number from 1 to n. You have to guess which number I picked.
//
// Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.
//
// However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the number I picked.
//
// Example:
//
// n = 10, I pick 8.
//
// First round:  You guess 5, I tell you that it's higher. You pay $5.
// Second round: You guess 7, I tell you that it's higher. You pay $7.
// Third round:  You guess 9, I tell you that it's lower. You pay $9.
//
// Game over. 8 is the number I picked.
//
// You end up paying $5 + $7 + $9 = $21.
// Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.


class Solution {
public:
    // min-max, minimize the maximum loss, minimize: use optimal strategy, max loss: correct at last guess and always lies in the higher pay range
    int getMoneyAmount(int n) {
        vector<vector<int>> dp{n + 1, vector<int>(n + 1)};  // dp[i][j] money needed for guessing at range [i, j]
        for (int r = 0; r < n; ++r) {
            for (int i = 1, j = i + r; j <= n; ++i, ++j) {
                if (i == j) dp[i][j] = 0;
                else {
                    dp[i][j] = INT_MAX;
                    for (int k = i; k <= j; ++k) {
                        if (k == i) dp[i][j] = min(dp[i][j], k + dp[i + 1][j]);
                        else if (k == j) dp[i][j] = min(dp[i][j], k + dp[i][j - 1]);
                        else dp[i][j] = min(dp[i][j], k + max(dp[i][k - 1], dp[k + 1][j]));  // correct always lies in high cost range
                    }
                }
            }
        }
        return dp[1][n];
    }
};
