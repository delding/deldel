import java.io.*;
import java.util.*;

public class gMatrix {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
    BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));
    int T = Integer.parseInt(br.readLine());
    for (int t = 1; t <= T; t++) {
      String[] line = br.readLine().split(" ");
      int N = Integer.parseInt(line[0]);
      int K = Integer.parseInt(line[1]);
      long C = Integer.parseInt(line[2]);
      long X = Integer.parseInt(line[3]);
      line = br.readLine().split(" ");
      long[] A = new long[N];
      long[] B = new long[N];
      for (int i = 0; i < N; i++) {
        A[i] = Long.parseLong(line[i]);
      }
      line = br.readLine().split(" ");
      for (int i = 0; i < N; i++) {
        B[i] = Long.parseLong(line[i]);
      }
      long[][] matrix = new long[N][N];
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          matrix[i][j] = (A[i] * (i+1) + B[j] * (j+1) + C) % X;
        }
      }
      long[][] maxPerLine = new long[N][N];
      for (int i = 0; i < N; i++) {
        // bug: can not just put index into sliding window when perform inplace update, since the original value in matrix can be overridden
        // if use in-place update, wrap value and index in a class then put in the sliding window
        Deque<Integer> dq = new ArrayDeque<>();
        for (int j = 0; j < N; j++) {
          while (!dq.isEmpty() && dq.peekFirst() <= j - K) dq.pollFirst();
          while (!dq.isEmpty() && matrix[i][dq.peekLast()] <= matrix[i][j]) dq.pollLast();
          dq.addLast(j);
          if (j >= K - 1) maxPerLine[i][j] = matrix[i][dq.peekFirst()]; // only need values begins from K - 1
        }
      }
      long sum = 0;
      for (int j = K - 1; j < N; j++) {
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
          while (!dq.isEmpty() && dq.peekFirst() <= i - K) dq.pollFirst();
          while (!dq.isEmpty() && maxPerLine[dq.peekLast()][j] <= maxPerLine[i][j]) dq.pollLast();
          dq.addLast(i);
          if (i >= K - 1) sum += maxPerLine[dq.peekFirst()][j];
        }
      }
      bw.write("Case #" + t + ": " + sum);
      bw.newLine();
    }
    br.close();
    bw.close();
  }
}