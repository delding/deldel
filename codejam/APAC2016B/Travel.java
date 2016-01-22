// pass
import java.io.*;
import java.util.*;

// use bellman-ford compute shortest paths from source to all destinations for all 24 hours
// constraint Cost[t] ≤ Cost[t+1]+1 guarantee that late travel will not surpass previous travel on the same road
public class Travel {

  static class Edge {
    int src;
    int dest;
    int[] costs = new int[24];

    Edge(int s, int d) {
      src = s;
      dest = d;
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
    BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));
    int T = Integer.parseInt(br.readLine());
    for (int t = 1; t <= T; t++) {
      String[] line = br.readLine().split(" ");
      int N = Integer.parseInt(line[0]);
      int M = Integer.parseInt(line[1]);
      int K = Integer.parseInt(line[2]);
      int[][] dist = new int[24][N]; // dist of all nodes to source for all 24 hours
      for (int s = 0; s < 24; s++) {
        for (int i = 0; i < N; i++) {
          if (i == 0) dist[s][i] = 0; // i = 0 is source node and its dist = 0
          else dist[s][i] = Integer.MAX_VALUE;
        }
      }
      List<Edge> edges = new ArrayList<>();
      for (int i = 0; i < M; i++) {
        line = br.readLine().split(" ");
        int c1 = Integer.parseInt(line[0]) - 1;
        int c2 = Integer.parseInt(line[1]) - 1;
        Edge edge1 = new Edge(c1, c2); // two edges for each road
        Edge edge2 = new Edge(c2, c1);
        edges.add(edge1);
        edges.add(edge2);
        line = br.readLine().split(" ");
        for (int j = 0; j < 24; j++) {
          edge1.costs[j] = Integer.parseInt(line[j]);
          edge2.costs[j] = Integer.parseInt(line[j]);
        }
      }
      for (int s = 0; s < 24; s++) {
        // bellman-ford
        for (int v = 0; v < N - 1; v++) {
          for (Edge e : edges) {
            int src = e.src;
            int dest = e.dest;
            if (dist[s][src] != Integer.MAX_VALUE) { // reachable
              int hour = dist[s][src];
              int d = dist[s][src] + e.costs[(s + hour) % 24]; // must mod 24
              if (d < dist[s][dest]) dist[s][dest] = d;
            }
          }
        }
      }
      bw.write("Case #" + t + ":");
      for (int i = 0; i < K; i++) {
        line = br.readLine().split(" ");
        int D = Integer.parseInt(line[0]) - 1;
        int S = Integer.parseInt(line[1]);
        if (dist[S][D] == Integer.MAX_VALUE){
          dist[S][D] = -1;
        }
        bw.write(" " + dist[S][D]);
      }
      bw.newLine();
    }
    bw.close();
    br.close();
  }
}


/*
public class Travel {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
    BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));
    int T = Integer.parseInt(br.readLine());
    for (int t = 1; t <= T; t++) {
      String[] line = br.readLine().split(" ");
      int N = Integer.parseInt(line[0]);
      int M = Integer.parseInt(line[1]);
      int K = Integer.parseInt(line[2]);
      Map<Integer, City> cities = new HashMap<>();
      for (int i = 0; i < N; i++) {
        cities.put(i, new City(i));
      }
      for (int i = 0; i < M; i++) {
        line = br.readLine().split(" ");
        City c1 = cities.get(Integer.parseInt(line[0]) - 1);
        City c2 = cities.get(Integer.parseInt(line[1]) - 1);

        if (!c1.neighbors.contains(c2.id)) {
          c1.neighbors.add(c2.id);
          c1.cost.put(c2.id, new ArrayList<>());
        }
        if (!c2.neighbors.contains(c1.id)) {
          c2.neighbors.add(c1.id);
          c2.cost.put(c1.id, new ArrayList<>());
        }
        List<Integer> cost12 = new ArrayList<>();
        c1.cost.get(c2.id).add(cost12); // works even if there already a cost list between c1 and c2
        c2.cost.get(c1.id).add(cost12);
        line = br.readLine().split(" ");
        for (int j = 0; j < 24; j++) {
          int cost = Integer.parseInt(line[j]);
          cost12.add(cost);
        }
      }
      bw.write("Case #" + t + ":");
      for (int i = 0; i < K; i++) {
        line = br.readLine().split(" ");
        int D = Integer.parseInt(line[0])  - 1;
        int S = Integer.parseInt(line[1]);
        boolean[] visited = new boolean[N];
        int[] minhours = new int[1];
        dfs(0, D, S, 0, cities, visited, minhours);
        bw.write(" " + minhours[0]);
      }
      bw.newLine();
    }
    bw.close();
    br.close();
  }

  static void dfs(int start, int end, int time, int hours, Map<Integer, City> cities, boolean[] visited, int[] minhours) {
    time %= 24; // BUG: must mod time to keep range in [0, 23]
    if (start == end) {
      minhours[0] = Math.min(minhours[0], hours);
      return;
    }
    visited[start] = true;
    City city = cities.get(start);
    for (int nei : city.neighbors) {
      if (!visited[nei]) {
        List<List<Integer>> costs = city.cost.get(nei);
        for (List<Integer> cost : costs) {
          int curCost = cost.get(time);
          dfs(nei, end, time + curCost, hours + curCost, cities, visited, minhours);
        }
      }
    }
    visited[start] = false;
  }

  static class City {
    int id;
    Set<Integer> neighbors; // city id
    Map<Integer, List<List<Integer>>> cost; // city id -> costs, out layer list for storing costs of multiple roads between two city

    City(int id) {
      this.id = id;
      neighbors = new HashSet<>();
      cost = new HashMap<>();
    }
  }
}
*/
