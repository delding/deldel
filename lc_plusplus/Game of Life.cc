// According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
//
// Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
//
// Any live cell with fewer than two live neighbors dies, as if caused by under-population.
// Any live cell with two or three live neighbors lives on to the next generation.
// Any live cell with more than three live neighbors dies, as if by over-population..
// Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
// Write a function to compute the next state (after one update) of the board given its current state.
//
// Follow up:
// Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
// In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?


class Solution {
public:
    void gameOfLife(vector<vector<int>>& board) {
      if (board.empty()) return;
      for (auto i = 0; i < board.size(); ++i) {
        for (auto j = 0; j < board[0].size(); ++j) {
          auto count = countAlives(board, i, j);
          if (board[i][j] == 1) {
            if (count < 2 || count > 3) {
              continue;
            }
            if (count >=2 && count <= 3) {
              board[i][j] |= 0b10;
            }
          } else {
            if (count == 3) {
              board[i][j] |= 0b10;
            }
          }
        }
      }
      for (auto i = 0; i < board.size(); ++i) {
        for (auto j = 0; j < board[0].size(); ++j) {
          board[i][j] >>= 1;
        }
      }
    }

    int countAlives(vector<vector<int>>& board, int x, int y) {
        int count{0};
        pair<int, int> dirs[]{{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
        for (auto dir : dirs) {
          auto xx = x + dir.first;
          auto yy = y + dir.second;
          if (xx >= 0 && xx < board.size() && yy >= 0 && yy < board[0].size()) {
            if ((board[xx][yy] & 1) == 1) ++count;
          }
        }
        return count;
    }
};
