// Given a 2D matrix matrix, find the sum of the elements inside the rectangle
// defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
//
// Range Sum Query 2D
// The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.
//
// Example:
// Given matrix = [
//   [3, 0, 1, 4, 2],
//   [5, 6, 3, 2, 1],
//   [1, 2, 0, 1, 5],
//   [4, 1, 0, 1, 7],
//   [1, 0, 3, 0, 5]
// ]
//
// sumRegion(2, 1, 4, 3) -> 8
// update(3, 2, 2)
// sumRegion(2, 1, 4, 3) -> 10
// Note:
// The matrix is only modifiable by the update function.
// You may assume the number of calls to update and sumRegion function is distributed evenly.
// You may assume that row1 ≤ row2 and col1 ≤ col2.


class NumMatrix {
  // Quad-Tree, worst case time complexity O(max(n, m)), when querying an entire row or column
  // quadtree is slower than 2d-segment tree
  struct QuadNode {
    int sum;
    int top;
    int left;
    int bot;
    int right;
    QuadNode* children[4];
    QuadNode(int top_, int left_, int bot_, int right_) : sum{0}, top{top_}, left{left_}, bot{bot_}, right{right_}, children{} {}
  };

  QuadNode* root;
  vector<vector<int>> mat;

public:
    NumMatrix(vector<vector<int>> matrix) : mat{matrix} {
      if (matrix.empty()) return;
      root = buildQuadTree(matrix, 0, 0, matrix.size() - 1, matrix[0].size() - 1);
    }

    void update(int row, int col, int val) {
      auto diff = val - mat[row][col];
      mat[row][col] = val;
      update(root, row, col, diff);
    }

    int sumRegion(int row1, int col1, int row2, int col2) {
      return sumRegion(root, row1, col1, row2, col2);
    }

    QuadNode* buildQuadTree(const vector<vector<int>>& matrix, int top, int left, int bot, int right) {
      QuadNode* node = new QuadNode(top, left, bot, right);
      if (top == bot && left == right) {
        node->sum = matrix[top][left];
        return node;
      }
      auto midX = (top + bot) / 2;
      auto midY = (left + right) / 2;
      if (midX >= top && midY >= left) {  // top left quad
        node->children[0] = buildQuadTree(matrix, top, left, midX, midY);
      }
      if (midX >= top && midY + 1 <= right) {  // top right quad
        node->children[1] = buildQuadTree(matrix, top, midY + 1, midX, right);
      }
      if (midX + 1 <= bot && midY >= left) {  // bot left quad
        node->children[2] = buildQuadTree(matrix, midX + 1, left, bot, midY);
      }
      if (midX + 1 <= bot && midY + 1 <= right) {  // bot right quad
        node->children[3] = buildQuadTree(matrix, midX + 1, midY + 1, bot, right);
      }
      for (auto i = 0; i < 4; ++i) {
        if (node->children[i] != nullptr) {
          node->sum += node->children[i]->sum;
        }
      }
      return node;
    }

    void update(QuadNode* n, int row, int col, int diff) {
      if (n->top <= row && n->left <= col && n->bot >= row && n->right >= col) {
        n->sum += diff;
        for (auto c : n->children) {
          if (c != nullptr) {
            update(c, row, col, diff);
          }
        }
      }
    }

    int sumRegion(QuadNode* root, int row1, int col1, int row2, int col2) {
      if (max(root->top, row1) > min(root->bot, row2) || max(root->left, col1) > min(root->right, col2)) { // no overlap
        return 0;
      }
      if (root->top >= row1 && root->left >= col1 && root->bot <= row2 && root->right <= col2) {
          return root->sum;
      }
      int sum{0};
      for (auto c : root->children) {
        if (c != nullptr) {
          sum += sumRegion(c, row1, col1, row2, col2);
        }
      }
      return sum;
    }
};

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */



// immutable
 public class NumMatrix {
     int[][] culSum;
     public NumMatrix(int[][] matrix) {
         if (matrix.length == 0 || matrix[0].length == 0) return;
         culSum = new int[matrix.length][matrix[0].length];
         for (int i = 0; i < matrix.length; i++) {
             for (int j = 0; j < matrix[0].length; j++) {
                 if (i == 0 && j == 0) culSum[i][j] = matrix[i][j];
                 else if (i == 0) culSum[i][j] = culSum[i][j - 1] + matrix[i][j];
                 else if (j == 0) culSum[i][j] = culSum[i - 1][j] + matrix[i][j];
                 else culSum[i][j] = culSum[i - 1][j] + culSum[i][j - 1] - culSum[i - 1][j - 1] + matrix[i][j];
             }
         }
     }

     public int sumRegion(int row1, int col1, int row2, int col2) {
         int up = row1 > 0 ? culSum[row1 - 1][col2] : 0;
         int left = col1 > 0 ? culSum[row2][col1 - 1] : 0;
         int upLeft = row1 > 0 && col1 > 0 ? culSum[row1 - 1][col1 - 1] : 0;
         return culSum[row2][col2] + upLeft - up - left;
     }
 }
