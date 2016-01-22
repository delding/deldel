// pass
import java.io.*;

public class gCampus {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
    BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));
    int T = Integer.parseInt(br.readLine());
    for (int t = 1; t <= T; t++) {
      String[] line = br.readLine().split(" ");
      int N = Integer.parseInt(line[0]);
      int M = Integer.parseInt(line[1]);
      int[][] dist = new int[N][N];
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (i == j) dist[i][j] = 0;
          else dist[i][j] = 1e8; // max input is 10^6
        }
      }
      int[][] edges = new int[M][3];
      for (int i = 0; i < M; i++) {
        line = br.readLine().split(" ");
        int u = Integer.parseInt(line[0]);
        int v = Integer.parseInt(line[1]);
        int w = Integer.parseInt(line[2]);
        // BUG: need to take min because input can have data u = v but w is positive, i.e. a non zero road from office to itself, which should not be used to initialize Floyd–Warshall
        dist[u][v] = Math.min(dist[u][v], w);
        dist[v][u] =  Math.min(dist[u][v], w);
        edges[i] = new int[]{u, v, w};
      }
      // Floyd–Warshall
      for (int k = 0; k < N; k++) {
        for (int i = 0; i < N; i++) {
          for (int j = 0; j < N; j++) {
            dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
          }
        }
      }
      bw.write("Case #" + t + ":");
      bw.newLine();
      for (int k = 0; k < M; k++) {
        int[] road = edges[k];
        int u = road[0];
        int v = road[1];
        int w = road[2];
        boolean useless = true;
        for (int i = 0; i < N; i++) {
          for (int j = 0; j < N; j++) {
            // BUG: after Floyd–Warshall a long edge can be updated by a shorter path, so need to use original edge weight to check if the edge is useless
            if (dist[i][j] == dist[i][u] + w + dist[v][j]) {
              useless = false;
              break;
            }
          }
          if (!useless) break;
        }
        if (useless) {
          bw.write("" + k);
          bw.newLine();
        }
      }
    }
    bw.close();
    br.close();
  }
}
