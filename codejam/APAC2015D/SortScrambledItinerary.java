// pass
import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SortScrambledItinerary {

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
    BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));
    int T = Integer.parseInt(br.readLine());
    for (int t = 1; t <= T; t++) {
      int N = Integer.parseInt(br.readLine());
      Map<String, String> routes = new HashMap<>();
      Set<String> indgree = new HashSet<>();
      for (int i = 0; i < N; i++) {
        String s = br.readLine();
        String d = br.readLine();
        routes.put(s, d);
        indgree.add(d);
      }
      String start = "";
      for (String city : routes.keySet()) {
        if (!indgree.contains(city)) {
          start = city;
        }
      }
      bw.write("Case #" + t + ":");
      for (int i = 0; i < N; i++) {
        String dest = routes.get(start);
        bw.write(" " + start + "-" + dest);
        start = dest;
      }
      bw.newLine();
    }
    br.close();
    bw.close();
  }
}