// pass
import java.io.*;

public class CardGame {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
    BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));
    int T = Integer.parseInt(br.readLine());
    for (int t = 1; t <= T; t++) {
      String[] line1 = br.readLine().split(" ");
      int N = Integer.parseInt(line1[0]);
      int K = Integer.parseInt(line1[1]);
      String[] line2 = br.readLine().split(" ");
      int[] cards = new int[N];
      for (int i = 0; i < N; i++) {
        cards[i] = Integer.parseInt(line2[i]);
      }
      boolean[][] nextTo = new boolean[N + 2][N + 2]; // dummy head and tail to allow head and tail to be removed
      for (int i = 1; i < N + 2; i++) {
        nextTo[i - 1][i] = true;
      }
      for (int d = 4; d <= N + 1; d++) {
        for (int i = 0, j = i + d; j < N + 2; i++, j++) {
          for (int a = i + 1; a < j; a++) {
            if (nextTo[i][a]) {
              for (int b = a + 1; b < j; b++) {
                if (nextTo[a][b]) {
                  for (int c = b + 1; c < j; c++) {
                    if (nextTo[b][c] && nextTo[c][j]) {
                      if (cards[c-1] - cards[b-1] == K && cards[b-1] - cards[a-1] == K) {
                        nextTo[i][j] = true;
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
      int[] minLen = new int[N + 2];
      for (int i = 0; i < N + 2; i++) {
        minLen[i] = i + 1;
      }
      for (int j = 1; j < N + 2; j++) {
        for (int i = 0; i < j; i++) {
          if (nextTo[i][j]) minLen[j] = Math.min(minLen[j], minLen[i] + 1);
        }
      }
      int res = minLen[N + 1] - 2; // subtract dummy head and tail
      bw.write("Case #" + t + ": " + res);
      bw.newLine();
    }
    br.close();
    bw.close();
  }
/*
  static int minNum(int[] cards, Map<String, Integer> memo, int K) {
    if (cards.length < 3) return cards.length;
    StringBuilder sb = new StringBuilder();
    for (int c : cards) sb.append(c);
    if (memo.containsKey(sb.toString())) {
      return memo.get(sb.toString());
    }
    int min = cards.length + 1;
    for (int i = 2; i < cards.length; i++) {
      if (cards[i] - cards[i - 1] == K && cards[i - 1] - cards[i - 2] == K) {
        int[] cards1 = new int[cards.length - 3];
        int j = 0;
        for (int k = 0; k < cards.length; k++) {
          if (k != i && k != i - 1 && k != i - 2) {
            cards1[j++] = cards[k];
          }
        }
        int num = minNum(cards1, memo, K);
        min = Math.min(min, num);
      }
    }
    memo.put(sb.toString(), min);
    return min;
  }*/
}