/*
 * According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
Write a function to compute the next state (after one update) of the board given its current state.

Follow up: 
Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?
*/

// encode int as 2 bit binary digit, lower bit denote current state, higher bit denote next state
public class Solution {
  public void gameOfLife(int[][] board) {
    int m = board.length;
    if (m == 0) return;
    int n = board[0].length;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        int count = 0;
        int currentState = board[i][j] & 1;
        for (int x = -1; x <= 1; x++) {
          for (int y = -1; y <= 1; y++) {
            if (x == 0 && y == 0) continue;
            int ix = i + x;
            int iy = j + y;
            if (ix >= 0 && ix < m && iy >= 0 && iy < n && (board[ix][iy] & 1) == 1) count++;
          }
        }
        if (count < 2 || count > 3) board[i][j] = (0 << 1) + currentState;
        else if (count == 2 && board[i][j] == 1) board[i][j] = (1 << 1) + currentState;
        else if (count == 2 && board[i][j] == 0) board[i][j] = (0 << 1) + currentState;
        else board[i][j] = (1 << 1) + currentState;
      }
    }
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        board[i][j] = board[i][j] >> 1;
      }
    }
    return;
  }
}
