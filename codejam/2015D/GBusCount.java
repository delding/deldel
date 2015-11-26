// pass test
import java.io.*;
import java.util.PriorityQueue;
import java.util.TreeMap;

// Much better solution: only need to make an array, each slot for a city, upon an interval, add one to each cities within the interval

// interval overlap count, use treemap store lower bound of each interval and its overlap count
// for each query search its floor in the treemap
public class GBusCount {

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
    BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));
    int T = Integer.parseInt(br.readLine());
    for (int t = 1; t <= T; t++) {
      int N = Integer.parseInt(br.readLine());
      PriorityQueue<Bound> pq = new PriorityQueue<>();
      boolean isLeft = true;
      for (String b : br.readLine().split(" ")) {
        Bound bound = new Bound(Integer.parseInt(b), isLeft);
        pq.offer(bound);
        isLeft = !isLeft;
      }
      TreeMap<Integer, Integer> bounds = new TreeMap<>(); // query lower bound of a interval for overlap values
      int count = 1;
      int lower = pq.poll().val;
      bounds.put(lower, count); // BUG, forget put the first one
      while (!pq.isEmpty()) {
        Bound b = pq.poll();
        if (!b.isLeft) {
          bounds.put(lower, count);
          count--;
          lower= b.val + 1;
        } else {
          bounds.put(lower, count);
          count++;
          lower = b.val;
        }
      }
      bounds.put(lower, count); // BUG, must put last bound for cities beyond upper bound, count is 0 at this point
      int queryNum = Integer.parseInt(br.readLine());
      bw.write("Case #" + t + ":");
      for (int i = 0; i < queryNum; i++) {
        Integer city = Integer.parseInt(br.readLine());
        Integer busNum;
        if (city < bounds.firstKey()) busNum = 0; // BUG: forget consider when city is outside lower bound
        else busNum = bounds.get(bounds.floorKey(city));
        bw.write(" " + busNum);
      }
      bw.newLine();
      br.readLine();
    }
    bw.close();
    br.close();
  }

  static class Bound implements Comparable<Bound> {
    boolean isLeft;
    int val;

    Bound(int v, boolean flag) {
      val = v;
      isLeft = flag;
    }
    public int compareTo(Bound that) {
      if (this.val == that.val) {
        if (!(this.isLeft ^ that.isLeft)) return 0; // same
        else if (this.isLeft) return -1; // pop left bound first, BUG: since use min-queue, return -1 to make left small and pop first
        else return 1;
      } else return this.val - that.val;
    }
  }

}