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
   * @return    The number of characters read
   */

  char[] cbuf = new char[4];
  int offset = 0;
  int buffSize = 0;

  public int read(char[] buf, int n) {
    int bytesRead = 0;
    boolean eof = false;
    while (!eof && bytesRead < n) {
      if (buffSize == 0) {
        if ((buffSize = read4(cbuf)) < 4) eof = true;
        offset = 0;
      }
      while (buffSize > 0 && bytesRead < n) {
        buf[bytesRead++] = cbuf[offset++];
        buffSize--;
      }
    }
    return bytesRead;
  }
}
