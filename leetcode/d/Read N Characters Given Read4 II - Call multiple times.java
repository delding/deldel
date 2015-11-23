/**
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 * The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
 * By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
 */

/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
  /**
   * @param buf Destination buffer
   * @param n   Maximum number of characters to read
   * @return The number of characters read
   */

  private char[] buf4 = new char[4]; // use circular buffer, but always make it empty before read
  private int offset = 0;
  private int bufsize = 0;

  public int read2(char[] buf, int n) {
    int count = 0;
    boolean eof = false;
    while (count < n && bufsize > 0) { // ERROR: must add count < n, otherwise can be wrong if n = 0
      buf[count++] = buf4[offset++];
      offset %= 4;
      bufsize--;
      if (count == n) break;
    }
    while (count < n && !eof) {
      bufsize = read4(buf4);
      offset = 0; // ERROR: set to 0 since buffer is reloaded
      if (bufsize < 4) eof = true;
      while (bufsize > 0) { // ERROR: there could be the case, file is over, buf4 is not full, buf doesn't take them all, so even if offset is not zero, buf4 can be aslo not full
        buf[count++] = buf4[offset++];
        offset %= 4;
        bufsize--; // ERROR: forget update bufsize
        if (count == n) break;
      }
    }
    return count;
  }

  // Refactoring version
  public int read(char[] buf, int n) {
    int count = 0;
    boolean eof = false;
    while (count < n && !eof) {
      if (bufsize == 0) {
        bufsize = read4(buf4);
        if (bufsize < 4) eof = true;
        offset = 0;
      }
      while (bufsize > 0) { // ERROR: there could be the case, file is over, buf4 is not full, buf doesn't take them all, so even if offset is not zero, buf4 can be aslo not full
        buf[count++] = buf4[offset++];
        offset %= 4;
        bufsize--; // ERROR: forget update bufsize
        if (count == n) break;
      }
    }
    return count;
  }
}
