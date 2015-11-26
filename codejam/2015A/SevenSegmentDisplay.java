// not pass test todo

import java.io.*;

public class SevenSegmentDisplay {
  static String[] digits = new String[]{
      "1111110",
      "0110000",
      "1101101",
      "1111001",
      "0110010",
      "1011011",
      "1011111",
      "1110000",
      "1111111",
      "1111011"
  };

  public static void main(String[] strs) throws Exception {
    String in = strs[0];
    String out = strs[1];
    BufferedReader br = new BufferedReader(new FileReader(new File(in)));
    BufferedWriter bw = new BufferedWriter(new FileWriter(out));
    int T = Integer.parseInt(br.readLine());
    for (int t = 1; t <= T; t++) {
      String[] s = br.readLine().split(" ");
      int N = Integer.parseInt(s[0]);
      boolean[] good = new boolean[7];
      int orAll = 0;
      for (int i = 1; i <= N; i++) {
        orAll &= Integer.parseInt(s[i]);
      }
      for (int i = 0; i < 7; i++) {
        if (((orAll >> i) & 1) == 1) {
          good[6 - i] = true;
        }
      }
      int pass = 0;
      String ret = "";
      boolean ambiguous = false;

      for (int start = 0;  start < 10; start++) { // iterate all ten possible starts
        boolean[] bad = new boolean[7];
        boolean[] unsure = new boolean[7];
        int i = 0;
        for (; i < N; i++) {
          String expected = digits[(start - i + 10) % 10];
          String real = s[i + 1];
          boolean notMatch = false;
          for (int j = 0; j < 7; j++) {
            char r = real.charAt(j);
            char e = expected.charAt(j);
            if (r == '1' && e == '0') {
              notMatch = true;
              break;
            } else if (e == '1' && good[j] && r == '0') {
              notMatch = true;
              break;
            } else if (r == '0' && e == '1') bad[j] = true;
          }
          for (int j = 0; j < 7; j++) {
            if (good[j] && bad[j]) notMatch = true;
            if (!good[j] && !bad[j]) unsure[j] = true;
          }
          if (notMatch) break;
        }
        if (i != N) continue;

        int next = (start + 10 - N) % 10;
        StringBuilder sb = new StringBuilder(digits[next]);
        for (int j = 0; j < 7; j++) {
          if (bad[j]) sb.setCharAt(j, '0');
        }
        for (int j = 0; j < 7; j++) {
          if (unsure[j] && sb.charAt(j) == '1')
            ambiguous = true; // every segment that needs to be '1' in result must be true to be good segment, otherwise ambiguous
        }
        if (!ambiguous) {
          if (ret.isEmpty()) ret = sb.toString();
          else if (!ret.equals(sb.toString()))
            ambiguous = true; // it's possible different starts lead to same broken result, but if lead to different results it's ambiguous
        }
        if (ambiguous) {
          break;
        }
      }
      if (!ambiguous && !ret.isEmpty()) {
        bw.write("Case #" + t + ": " + ret + "\n");
      } else {
        bw.write("Case #" + t + ": ERROR!\n");
      }
    }
    br.close();
    bw.close();
  }
}




 // other guy's solution
 class SevenSegmentDisplay2 {
  public static byte[] seg = {
      0X7E,
      0X30,
      0X6D,
      0X79,
      0X33,
      0X5B,
      0X5F,
      0X70,
      0X7F,
      0X7B
  };

  public static void main2(String[] args) {
    try {
      solveSevensegmentDisplay();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void solveSevensegmentDisplay() throws IOException {
    File file = new File("E:\\Page on a-large-practice.in");
    BufferedReader br = new BufferedReader(new FileReader(file));
    BufferedWriter bw = new BufferedWriter(new FileWriter("E:\\A-large-practice.out"));
    int T = Integer.parseInt(br.readLine());
    byte[] history = new byte[100];
    for (int t = 1; t <= T; t++) {
      String[] str = br.readLine().split(" ");
      int N = Integer.parseInt(str[0]);
      int result = 0, preResult = 0;
      byte broken, good, unsure = 0X00;
      boolean match = false;
      boolean err = false;
      for (int i = 1; i <= N; i++) {
        byte display = 0;
        for (int j = 0; j < 7; j++) {
          display <<= 1;
          display |= (str[i].charAt(j) == '1' ? 1 : 0);
        }
        history[i - 1] = display;
        if (i >= 11 && !str[i].equals(str[i - 10])) {
          err = true;
          break;
        }
      }
      if (err == true) {
        bw.write("Case #" + t + ": ERROR!\n");
        continue;
      }
      for (int start = 0; start < 10; start++) {
        broken = 0;
        good = 0;
        boolean fail = false;
        for (int i = 0; i < N && i < 10; i++) {
          good |= history[i];
          byte expect = seg[(start - i + 10) % 10];
          broken |= (expect & ~history[i]);
          unsure = (byte) (~good & ~broken & 0X7F);
          if ((good & broken) != 0 || (history[i] & ~(expect & good)) != 0) {
            fail = true;
            break;
          }

        }
        if (!fail) {
          result = seg[(start - N % 10 + 10) % 10] & ~broken;
          if ((result & unsure) != 0 || (match && (preResult != result))) {
            match = false;
            break;
          } else {
            match = true;
            preResult = result;
          }
        }
      }
      if (match) {
        StringBuilder temp = new StringBuilder();
        for (int i = 6; i >= 0; i--) {
          temp.append((result & 1 << i) != 0 ? 1 : 0);
        }
        bw.write("Case #" + t + ": " + temp + "\n");
      } else {
        bw.write("Case #" + t + ": ERROR!\n");
      }
    }
    br.close();
    bw.close();
  }

}