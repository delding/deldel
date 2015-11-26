// todo
import java.io.*;
import java.util.Arrays;

public class ItzChess {
  static char[][] board = new char[8][8];

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
    BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));
    int T = Integer.parseInt(br.readLine());
    for (int t = 1; t <= T; t++) {
      for (int i = 0; i < 8; i++) {
        Arrays.fill(board[i], '*');
      }
      int N = Integer.parseInt(br.readLine());
      for (int i = 0; i < N; i++) {
        String[] line = br.readLine().split("-");
        int x = line[0].charAt(1) - '1';
        int y = 'H' - line[0].charAt(0);
        board[x][y] = line[1].charAt(0);
      }
      int kills = 0;
      for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
          switch (board[i][j]) {
            case 'K':
              for (int k = -1; k <= 1; k++) {
                for (int l = -1; l <= 1; l++) {
                  if (k == 0 && l == 0) continue;
                  int x = i +k;
                  int y = j + l;
                  if (x >= 0 && x < 8 && y >= 0 && y < 8) {
                    if (board[x][y] != '*') {
                      kills++;
                    }
                  }
                }
              }
              break;
            case 'Q':
              for (int k = 1; i + k < 8; k++) {
                if (board[i+k][j] != '*') {
                  kills++;
                  break;
                }
              }
              for (int k = 1; i - k >= 0; k++) {
                if (board[i-k][j] != '*') {
                  kills++;
                  break;
                }
              }
              for (int k = 1; j + k < 8; k++) {
                if (board[i][j+k] != '*') {
                  kills++;
                  break;
                }
              }
              for (int k = 1; j - k >= 0; k++) {
                if (board[i][j-k] != '*') {
                  kills++;
                  break;;
                }
              }
              break;
            case 'R':
              break;
            case 'B':
              break;
            case 'N':
              break;
            case 'P':
              break;
            default:
          }
        }
      }
    }
  }
}

/* very neat implementation in c++ pasted from web

char str[10],chk[8][8];
int N;

int nx[8] = {1,2,2,1,-1,-2,-2,-1};
int ny[8] = {2,1,-1,-2,-2,-1,1,2};
int dx[8] = {0,1,1,1,0,-1,-1,-1};
int dy[8] = {1,1,0,-1,-1,-1,0,1};

int main()
{
	freopen ("1.in","r",stdin);
	freopen ("1.out","w",stdout);

	int Test; scanf ("%d",&Test); for (int Case=1;Case<=Test;Case++){
		printf ("Case #%d: ",Case);

		for (int i=0;i<8;i++) for (int j=0;j<8;j++) chk[i][j] = ' ';
		scanf ("%d",&N); for (int i=0;i<N;i++){
			scanf ("%s",str);
			chk[str[0]-'A'][str[1]-'1'] = str[3];
		}
		int cnt = 0;
		for (int i=0;i<8;i++) for (int j=0;j<8;j++){
			if (chk[i][j] == 'P'){
				if (i < 7){
					if (j > 0 && chk[i+1][j-1] != ' ') cnt++;
					if (j < 7 && chk[i+1][j+1] != ' ') cnt++;
				}
			}
			else if (chk[i][j] == 'N'){
				for (int k=0;k<8;k++){
					int px = i + nx[k];
					int py = j + ny[k];
					if (px < 0 || px > 7 || py < 0 || py > 7) continue;
					if (chk[px][py] != ' ') cnt++;
				}
			}
			else if (chk[i][j] == 'B'){
				for (int k=1;k<8;k+=2){
					int px = i, py = j;
					while (1){
						px += dx[k];
						py += dy[k];
						if (px < 0 || px > 7 || py < 0 || py > 7) break;
						if (chk[px][py] != ' '){
							cnt++; break;
						}
					}
				}
			}
			else if (chk[i][j] == 'R'){
				for (int k=0;k<8;k+=2){
					int px = i, py = j;
					while (1){
						px += dx[k];
						py += dy[k];
						if (px < 0 || px > 7 || py < 0 || py > 7) break;
						if (chk[px][py] != ' '){
							cnt++; break;
						}
					}
				}
			}
			else if (chk[i][j] == 'Q'){
				for (int k=0;k<8;k++){
					int px = i, py = j;
					while (1){
						px += dx[k];
						py += dy[k];
						if (px < 0 || px > 7 || py < 0 || py > 7) break;
						if (chk[px][py] != ' '){
							cnt++; break;
						}
					}
				}
			}
			else if (chk[i][j] == 'K'){
				for (int k=0;k<8;k++){
					int px = i + dx[k];
					int py = j + dy[k];
					if (px < 0 || px > 7 || py < 0 || py > 7) continue;
					if (chk[px][py] != ' ') cnt++;
				}
			}
		}
		printf ("%d\n",cnt);

	}

	return 0;
}
 */