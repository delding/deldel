// not pass test todo
import java.io.*;
import java.util.*;

// tricky part is after taking tunnel to a station, can take another tunnel instead of wait and take the metro at this station

// better solution, not yet implemented
// use two vertex represent each station, tunnelStop and trainStop
// tunnelStop has an edge to trainStop with length equal to the wait time of this station, and also has edges to tunnelStop at stations of other lines if there is a tunnel between them
// trainStop has an edge to the tunnelStop with length equal to 0 since no need to wait, and has edges to trainStop at prev and next stations of the same line

// not pass test data
// when adding tunnel, double the station at each end of this tunnel, the extra station has a tunnel distance without wait time, and remove
// its prev and next stations of the same line from its neighbor list
public class TakingMetro {

  static class Station implements Comparable<Station>{
    int lineNo;
    int stationNo;
    int waitTime;
    Map<Station, Integer> neighbors; // neighbor and distance
    int dist;

    public Station(int l, int s, int w) {
      lineNo = l;
      stationNo = s;
      waitTime = w;
      neighbors = new HashMap<>();
      dist = Integer.MAX_VALUE;
    }

    public Station cloneForTunnel() {
      Station s = new Station(this.lineNo, this.stationNo, this.waitTime);
      for (Station st : this.neighbors.keySet()) {
        if (st.lineNo != this.lineNo) { // station of other lines, i.e. connected through tunnel
          s.neighbors.put(st, this.neighbors.get(st));
        }
      }
      return s;
    }

    public int compareTo(Station that) {
      return this.dist - that.dist;
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
    BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));
    int T = Integer.parseInt(br.readLine());
    for (int t = 1; t <= T; t++) {
      br.readLine();
      List<List<Station>> graph = new ArrayList<>();
      int N = Integer.parseInt(br.readLine());
      for (int i = 0; i < N; i++) {
        graph.add(new ArrayList<>());
        String[] line1 = br.readLine().split(" ");
        int SN = Integer.parseInt(line1[0]);
        int W = Integer.parseInt(line1[1]);
        for (int j = 0; j < SN; j++) {
          graph.get(i).add(new Station(i, j, W));
        }
        String[] dist = br.readLine().split(" ");
        for (int j = 0; j < dist.length; j++) {
          int d = Integer.parseInt(dist[j]);
          Station s1 = graph.get(i).get(j);
          Station s2 = graph.get(i).get(j + 1);
          s1.neighbors.put(s2, d);
          s2.neighbors.put(s1, d);
        }
      }
      int tunnelNum = Integer.parseInt(br.readLine());
      for (int i = 0; i < tunnelNum; i++) {
        String[] tunnel = br.readLine().split(" ");
        int l1 = Integer.parseInt(tunnel[0]) - 1;
        int s1 = Integer.parseInt(tunnel[1]) - 1;
        int l2 = Integer.parseInt(tunnel[2]) - 1;
        int s2 = Integer.parseInt(tunnel[3]) - 1;
        int d = Integer.parseInt(tunnel[4]);
        Station sta1 = graph.get(l1).get(s1);
        Station sta2 = graph.get(l2).get(s2);
        sta1.neighbors.put(sta2, d + sta2.waitTime); // add wait time to distance
        sta2.neighbors.put(sta1, d + sta1.waitTime);
        Station sta11 = sta1.cloneForTunnel();
        Station sta22 = sta2.cloneForTunnel();
        graph.get(sta1.lineNo).add(sta11);
        graph.get(sta2.lineNo).add(sta22);
        for (Station st : sta11.neighbors.keySet()) {
          sta2.neighbors.put(st, d + sta11.neighbors.get(st)); // don't wait, sta11 means go xsfrom sta2 to sta1 than go to other tunnel
        }
        for (Station st : sta22.neighbors.keySet()) {
          sta1.neighbors.put(st, d + sta22.neighbors.get(st));
        }
      }
      int queryNum = Integer.parseInt(br.readLine());
      bw.write("Case: #" + t + ":");
      bw.newLine();
      for (int i = 0; i < queryNum; i++) {
        String[] query = br.readLine().split(" ");
        int l1 = Integer.parseInt(query[0]) - 1;
        int s1 = Integer.parseInt(query[1]) - 1;
        int l2 = Integer.parseInt(query[2]) - 1;
        int s2 = Integer.parseInt(query[3]) - 1;
        Station sta1 = graph.get(l1).get(s1);
        Station sta2 = graph.get(l2).get(s2);
        PriorityQueue<Station> pq = new PriorityQueue<>();
        sta1.dist = sta1.waitTime;
        for (List<Station> line : graph) {
          for (Station st : line) {
            pq.offer(st);
          }
        }
        Set<Station> visited = new HashSet<>();
        visited.add(sta1);
        boolean done = false;
        while (!done && !pq.isEmpty()) {
          Station st = pq.poll();
          for (Station neb : st.neighbors.keySet()) {
            if (!visited.contains(neb)) {
              neb.dist = st.dist + st.neighbors.get(neb);
              if (neb == sta2) { // reference to same object
                done = true;
                break;
              }
              visited.add(neb);
              pq.remove(neb); // because need to let pq update this item's priority level
              pq.offer(neb);
            }
          }
        }
        int ret = sta2.dist == Integer.MAX_VALUE ? -1 : sta2.dist;
        bw.write(""+ ret);
        bw.newLine();
      }
    }
    bw.close();
    br.close();
  }
}
