// todo
import java.io.*;

public class Tetris {

}

/* C++ solution pasted from web
#include <iostream>
using namespace std;

int board[100][100];

int block[7][4][4][4] = {
	{
		{
			0, 0, 0, 0,
			1, 0, 0, 0,
			1, 1, 0, 0,
			0, 1, 0, 0
		},
		{
			0, 0, 0, 0,
			0, 0, 0, 0,
			0, 1, 1, 0,
			1, 1, 0, 0
		},
		{
			0, 0, 0, 0,
			1, 0, 0, 0,
			1, 1, 0, 0,
			0, 1, 0, 0
		},
		{
			0, 0, 0, 0,
			0, 0, 0, 0,
			0, 1, 1, 0,
			1, 1, 0, 0
		},
	},
	{
		{
			0, 0, 0, 0,
			0, 1, 0, 0,
			1, 1, 0, 0,
			1, 0, 0, 0
		},
		{
			0, 0, 0, 0,
			0, 0, 0, 0,
			1, 1, 0, 0,
			0, 1, 1, 0
		},
		{
			0, 0, 0, 0,
			0, 1, 0, 0,
			1, 1, 0, 0,
			1, 0, 0, 0
		},
		{
			0, 0, 0, 0,
			0, 0, 0, 0,
			1, 1, 0, 0,
			0, 1, 1, 0
		},
	},
	{
		{
			0, 0, 0, 0,
			1, 0, 0, 0,
			1, 0, 0, 0,
			1, 1, 0, 0
		},
		{
			0, 0, 0, 0,
			0, 0, 0, 0,
			0, 0, 1, 0,
			1, 1, 1, 0
		},
		{
			0, 0, 0, 0,
			1, 1, 0, 0,
			0, 1, 0, 0,
			0, 1, 0, 0
		},
		{
			0, 0, 0, 0,
			0, 0, 0, 0,
			1, 1, 1, 0,
			1, 0, 0, 0
		},
	},
	{
		{
			0, 0, 0, 0,
			0, 1, 0, 0,
			0, 1, 0, 0,
			1, 1, 0, 0
		},
		{
			0, 0, 0, 0,
			0, 0, 0, 0,
			1, 1, 1, 0,
			0, 0, 1, 0
		},
		{
			0, 0, 0, 0,
			1, 1, 0, 0,
			1, 0, 0, 0,
			1, 0, 0, 0
		},
		{
			0, 0, 0, 0,
			0, 0, 0, 0,
			1, 0, 0, 0,
			1, 1, 1, 0
		},
	},
	{
		{
			0, 0, 0, 0,
			0, 0, 0, 0,
			1, 1, 0, 0,
			1, 1, 0, 0
		},
		{
			0, 0, 0, 0,
			0, 0, 0, 0,
			1, 1, 0, 0,
			1, 1, 0, 0
		},
		{
			0, 0, 0, 0,
			0, 0, 0, 0,
			1, 1, 0, 0,
			1, 1, 0, 0
		},
		{
			0, 0, 0, 0,
			0, 0, 0, 0,
			1, 1, 0, 0,
			1, 1, 0, 0
		},
	},
	{
		{
			1, 0, 0, 0,
			1, 0, 0, 0,
			1, 0, 0, 0,
			1, 0, 0, 0
		},
		{
			0, 0, 0, 0,
			0, 0, 0, 0,
			0, 0, 0, 0,
			1, 1, 1, 1
		},
		{
			1, 0, 0, 0,
			1, 0, 0, 0,
			1, 0, 0, 0,
			1, 0, 0, 0
		},
		{
			0, 0, 0, 0,
			0, 0, 0, 0,
			0, 0, 0, 0,
			1, 1, 1, 1
		},
	},
	{
		{
			0, 0, 0, 0,
			0, 0, 0, 0,
			0, 1, 0, 0,
			1, 1, 1, 0
		},
		{
			0, 0, 0, 0,
			0, 1, 0, 0,
			1, 1, 0, 0,
			0, 1, 0, 0
		},
		{
			0, 0, 0, 0,
			0, 0, 0, 0,
			1, 1, 1, 0,
			0, 1, 0, 0
		},
		{
			0, 0, 0, 0,
			1, 0, 0, 0,
			1, 1, 0, 0,
			1, 0, 0, 0
		},
	},
};

int main() {
	int T;
	cin >> T;
	for (int cas = 1; cas <= T; cas++) {
		int W, H, N;
		cin >> W >> H >> N;
		memset(board, 0, sizeof(board));
		bool endflag = false;
		while (N--) {
			int t, r, x;
			cin >> t >> r >> x;
			if (endflag) continue;
			t--;
			int h = -4;
			bool flag = true;
			while (flag) {
				h++;
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 4; j++) {
						int newi = h + i;
						int newj = x + j;
						if (newi < 0 || newj >= W) {
							continue;
						}
						if (newi >= H) {
							flag = false;
							break;
						}
						if (board[newi][newj] && block[t][r][i][j])
						{
							flag = false;
							break;
						}
					}
				}
			}
			h--;
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					int newi = h + i;
					int newj = x + j;
					if (block[t][r][i][j] && newi < 0) endflag = true;
					if (newi < 0 || newj >= W) continue;
					board[newi][newj] |= block[t][r][i][j];
				}
			}
			for (int i = H - 1; i >= 0;) {
				bool full = true;
				for (int j = 0; j < W; j++) {
					if (board[i][j] == 0) {
						full = false;
						break;
					}
				}
				if (!full) {
					i--;
					continue;
				}
				else {
					for (int j = i; j > 0; j--) {
						memcpy(board[j], board[j - 1], sizeof(board[j]));
					}
					memset(board[0], 0, sizeof(board[0]));
				}
			}
		}
		cout << "Case #" << cas << ":" << endl;
		if (endflag) {
			cout << "Game Over!" << endl;
		}
		else {
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (board[i][j]) {
						cout << 'x';
					}
					else {
						cout << '.';
					}
				}
				cout << endl;
			}
		}
	}
}
 */