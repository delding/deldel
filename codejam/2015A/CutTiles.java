// pass test
import java.io.*;
import java.util.*;

public class CutTiles {

  static class Rect implements Comparable<Rect> {
    int longside;
    int shortside;

    Rect(int l, int s) {
      longside = l >= s ? l : s;
      shortside = l < s ? l : s;
    }

    public int compareTo(Rect other) {
      if (this.shortside == other.shortside) return this.longside - other.longside;
      else return this.shortside - other.shortside;
    }

    public String toString() {
      return shortside + " " + longside;
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
      Queue<Integer> tiles = new PriorityQueue<>(N, Collections.reverseOrder());
      for (int i = 0; i < N; i++) {
        tiles.offer(Integer.parseInt(line[i + 2]));
      }
      int count = 0;
      // TreeSet<Rect> available = new TreeSet<>();
      TreeMap<Rect, Integer> available = new TreeMap<>();
      while (!tiles.isEmpty()) {
        Integer side = 1 << tiles.peek();
        Rect tile = new Rect(side, side);
        if (available.isEmpty() || available.lastKey().compareTo(tile) < 0) {
          count++;
          available.put(new Rect(M, M), 1);
        } else {
          // BUG: due to I implement the compareTo i.e. equals, so different instance of logically equal object of Rect are same in TreeSet
          // MUST: count for each object in the TreeSet, so use TreeMap
          Rect big = available.ceilingKey(tile);
          if (available.get(big) == 1) available.remove(big);
          else available.put(big, available.get(big) - 1);
          tiles.poll();
          Integer remain = big.longside - side; // make the remaining part as large as possible
          Rect rec1 = new Rect(remain, big.shortside); // the bigger remain
          Rect rec2 = new Rect(big.shortside - side, side); // the smaller one
          if (!available.containsKey(rec1)) {
            available.put(rec1, 1);
          } else available.put(rec1, available.get(rec1) + 1);
          if (!available.containsKey(rec2)) {
            available.put(rec2, 1);
          } else available.put(rec2, available.get(rec2) + 1);
        }
      }
      bw.write("Case #" + t + ": " + count);
      bw.newLine();
    }
    br.close();
    bw.close();
  }
}