// wrong solution for large data fail todo
import java.io.*;
import java.util.*;


public class gNumbers {


  static HashSet<Long> prime = new HashSet<>();
  // must use global memoization to pass large dataset
  static Map<Long, Boolean> memo = new HashMap<>();

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
    BufferedWriter bw = new BufferedWriter(new FileWriter(args[1]));
    int T = Integer.parseInt(br.readLine());
    for (int t = 1; t <= T; t++) {

      long num = Long.parseLong(br.readLine());
      setPrimeFactor(num);

      String winner = win(num) ? "Laurence" : "Seymour";
      bw.write("Case #" + t + ": " + winner);
      bw.newLine();


      System.out.println("Case #" + t + ": " + winner);


    }
    br.close();
    bw.close();
  }

  static boolean win(long n) {
    if (memo.containsKey(n)) {
      return memo.get(n);
    }
    if (isgNumber(n)) {
      memo.put(n, false);
      return false;
    }
    for (long p : prime) { // BUG: must choose prime factors
      if (n % p == 0) {
        long tmp = n;
        while (tmp % p == 0) {
          tmp /= p;
        }
        if (!win(tmp)) {
          memo.put(n, true);
          return true;
        }
      }
    }
    memo.put(n, false);
    return false;
  }

  static void setPrimeFactor(long n) {
    prime.clear();
    for (long i = 2; i <= n; i++) {
      if (n % i == 0) {
        prime.add(i);
        while (n % i == 0) {
          n /= i;
        }
      }
    }
  }

  static boolean isgNumber(long n) {
    long m = 0;
    while (n != 0 ) {
      m +=  n % 10;
      n /= 10;
    }
    return m == 1 || isPrime(m);
  }

  static boolean isPrime(long n) {
    if (n==1) return false;
    for (long i = 2; i * i <= n; i++) {
      if (n % i == 0) return false;
    }
    return true;
  }
}