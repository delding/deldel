/**
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 * <p>
 * The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
 * <p>
 * By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
 **/

/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
  /**
   * @param buf Destination buffer
   * @param n   Maximum number of characters to read
   * @return    The number of characters read
   */
  public int read(char[] buf, int n) {
    char[] cbuf = new char[4];
    int bytesRead = 0;
    boolean eof = false;
    int num;
    while (bytesRead < n && !eof) {
      if ((num = read4(cbuf)) < 4) eof = true;
      for (int i = 0; i < num && bytesRead < n; i++) {
        buf[bytesRead++] = cbuf[i];
      }
    }
    return bytesRead;
  }
}