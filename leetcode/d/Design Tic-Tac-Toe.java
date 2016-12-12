/**
 Design a Tic-tac-toe game that is played between two players on a n x n grid.

 You may assume the following rules:

 A move is guaranteed to be valid and is placed on an empty block.
 Once a winning condition is reached, no more moves is allowed.
 A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.

 Could you trade extra space such that move() operation can be done in O(1)?
 You need two arrays: int rows[n], int cols[n], plus two variables: diagonal, anti_diagonal.
 **/

public class TicTacToe {
	int size;
	int[] rows;
	int[] cols;
	int diag = 0;
	int anti = 0;
	/** Initialize your data structure here. */
	public TicTacToe(int n) {
		size = n;
		rows = new int[n];
		cols = new int[n];
	}

	/** Player {player} makes a move at ({row}, {col}).
	 @param row The row of the board.
	 @param col The column of the board.
	 @param player The player, can be either 1 or 2.
	 @return The current winning condition, can be either:
	 0: No one wins.
	 1: Player 1 wins.
	 2: Player 2 wins. */
	public int move(int row, int col, int player) {
		if (player == 1) {
			rows[row]++;
			cols[col]++;
			if (row == col) diag++;
			if (row + col == size - 1) anti++;
		} else if (player == 2) {
			rows[row]--;
			cols[col]--;
			if (row == col) diag--;
			if (row + col == size - 1) anti--;
		}
		if (rows[row] == size || cols[col] == size || diag == size || anti == size) return 1;
		if (rows[row] == -size || cols[col] == -size || diag == -size || anti == -size) return 2;
		return 0;
	}
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */