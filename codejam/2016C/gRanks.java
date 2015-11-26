import java.io.*;
import java.util.*;

public class gRanks {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
    BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));
    int T = Integer.parseInt(br.readLine());
    for (int t = 1; t <= T; t++) {
      int P = Integer.parseInt(br.readLine());
      String[] line = br.readLine().split(" ");
      int[] scores = new int[P];
      for (int i = 0; i < P; i++) {
        scores[i] = Integer.parseInt(line[i]);
      }
      Map<String, PriorityQueue<Integer>> names = new HashMap<>();
      int N = Integer.parseInt(br.readLine());
      for (int i = 0; i < N; i++) {
        line = br.readLine().split(" ");
        int w = Integer.parseInt(line[0]);
        for (int j = 1; j <= P; j++) {
          PriorityQueue<Integer> points = names.get(line[j]);
          if (points == null) {
            points = new PriorityQueue<>(1, Collections.reverseOrder());
            names.put(line[j], points);
          }
          points.offer(scores[j - 1] * w);
        }
      }
      int M = Integer.parseInt(br.readLine());
      PriorityQueue<Person> pq = new PriorityQueue<>();
      for (String name : names.keySet()) {
        long point = 0;
        for (int i = 0; i < M; i++) {
          if (!names.get(name).isEmpty()) {
            point += names.get(name).poll();
          }
        }
        pq.offer(new Person(point, name));
      }
      bw.write("Case #" + t + ":");
      bw.newLine();
      int r = 0;
      int dup = 1;
      long prevPoint = Long.MAX_VALUE;
      while (!pq.isEmpty()) { // people with same points should have same rank
        Person p = pq.poll();
        if (p.points < prevPoint) {
          r += dup;
          dup = 1;
          prevPoint = p.points;
        } else dup++;
        bw.write(r + ": " + p.name);
        bw.newLine();
      }
    }
    br.close();
    bw.close();
  }

  static class Person implements Comparable<Person> {
    long points;
    String name;

    Person(long p, String s) {
      points = p;
      name = s;
    }

    public int compareTo(Person that) {
      if (this.points == that.points) return this.name.compareTo(that.name);
      else {
        if (this.points < that.points) return 1;
        else return -1;
      }
    }
  }
}