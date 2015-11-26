// not pass test todo

import java.io.*;
import java.util.*;

// undirected graph, x+y=2 means node x node y length of edge is 2
// 1. x+w, w+z, z+y, x+w-w-z+z+y=x+y, if can find a path containing odd number of edges between x and y nodes, can compute x+y
// 2. if no path between x and y, if can find circle with odd number of edges from x to x then can compute x+x, actually x
// same case for y to compute y+y, i.e. y, and thereby compute x+y
public class Addition {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
    BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));
    int T = Integer.parseInt(br.readLine());
    for (int t = 1; t <= T; t++) {
      int N = Integer.parseInt(br.readLine());
      Map<String, Set<Edge>> graph = new HashMap<>();
      Set<String> edges = new HashSet<>();
      for (int i = 0; i < N; i++) {
        String[] q = br.readLine().split("=");
        String[] nodes = q[0].split("\\+");
        String edge1 = q[0];
        String edge2 = nodes[1] + "+" + nodes[0];
        if (!graph.containsKey(nodes[0])) {
          graph.put(nodes[0], new HashSet<Edge>());
        }
        if (!graph.containsKey(nodes[1])) {
          graph.put(nodes[1], new HashSet<Edge>());
        }
        if (!edges.contains(edge1)) {
          graph.get(nodes[0]).add(new Edge(Integer.parseInt(q[1]), nodes[1]));
          if (!nodes[0].equals(nodes[1])) {
            graph.get(nodes[1]).add(new Edge(Integer.parseInt(q[1]), nodes[0]));
          }
          edges.add(edge1);
          edges.add(edge2);
        }
      }

      for (String k : graph.keySet()) {
          for (Edge e : graph.get(k))
            System.out.println(e.vertex + " " + k); // duplicated edges printed
      }

      bw.write("Case #" + t + ":\n");
      N = Integer.parseInt(br.readLine());
      for (int i = 0; i < N; i++) {
        String question = br.readLine();
        String[] key = question.split("\\+");
        List<Integer> pathxy = new ArrayList<Integer>();
        List<Integer> pathxx = new ArrayList<Integer>();
        List<Integer> pathyy = new ArrayList<Integer>();
        if (!graph.containsKey(key[0]) || !graph.containsKey(key[1])) {
          continue;
        }
        dfsXY(graph, key[0], key[0], key[1], new ArrayList<Integer>(), pathxy, new HashSet<String>());
        if (pathxy.size() != 0) {
          int val = 0;
          for (int j = 0; j < pathxy.size(); j++) {
            if (j % 2 == 0) val += pathxy.get(j);
            else val -= pathxy.get(j);
          }
          bw.write(question + "=" + val);
          bw.newLine();
        } else {
          dfsXX(graph, key[0], key[0], new ArrayList<Integer>(), pathxx, new HashSet<String>());
          dfsXX(graph, key[1], key[1], new ArrayList<Integer>(), pathyy, new HashSet<String>());
          if (pathxx.size() != 0 && pathyy.size() != 0) {
            int x = 0;
            for (int j = 0; j < pathxx.size(); j++) {
              if (j % 2 == 0) x += pathxx.get(j);
              else x -= pathxx.get(j);
            }
            int y = 0;
            for (int j = 0; j < pathyy.size(); j++) {
              if (j % 2 == 0) y += pathyy.get(j);
              else y -= pathyy.get(j);
            }
            int val = (x + y) / 2;
            bw.write(question + "=" + val);
            bw.newLine();
          }
        }
      }
    }
    br.close();
    bw.close();
  }

  static void dfsXY(Map<String, Set<Edge>> g, String curr, String x, String y, List<Integer> path, List<Integer> pathxy, Set<String> visited) {
//    System.out.println(path);
    if (pathxy.size() != 0) return;
    visited.add(curr);
    for (Edge e : g.get(curr)) {
      if (e.vertex.equals(y)) { // every time meet y, number of times meet y is at most the indegree of y if path has not found, check if current path is valid
        if (path.size() % 2 == 0) {
          // pathxy = new ArrayList<>(path);   Bug: must use addAll, not reassign
          pathxy.addAll(new ArrayList<>(path));
          pathxy.add(e.len);
          return;
        } else continue;
      }
      if (!visited.contains(e.vertex)) {
        path.add(e.len);
        dfsXY(g, e.vertex, x, y, path, pathxy, visited);
        path.remove(path.size() - 1);
      }
    }
  }

  static void dfsXX(Map<String, Set<Edge>> g, String curr, String x, List<Integer> path, List<Integer> pathxx, Set<String> visited) {
    if (pathxx.size() != 0) return;
    visited.add(curr);
    for (Edge e : g.get(curr)) {
      if (e.vertex.equals(x)) { // self looped edge allowed
        if (path.size() % 2 == 0) {
          // pathxx = new ArrayList<>(path);
          pathxx.addAll(new ArrayList<>(path));
          pathxx.add(e.len);
          return;
        } else continue;
      }
      if (!visited.contains(e.vertex)) {
        path.add(e.len);
        dfsXX(g, e.vertex, x, path, pathxx, visited);
        path.remove(path.size() - 1);
      }
    }
  }


  static class Edge {

    String vertex;
    int len;

    Edge(int l, String v) {
      vertex = v;
      len = l;
    }
  }
}