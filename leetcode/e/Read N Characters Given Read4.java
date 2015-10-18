/**
 * The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
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
        int count = 0;
        char[] buf4 = new char[4];
        int size = 0;
        while ((size = read4(buf4)) == 4) {
            for (int i = 0; i < 4; i++) {
                buf[count++] = buf4[i];
                if (count == n) break;
            }
            if (count == n) break; // ERROR: forget add this break
        }
        if (count < n) {
            for (int i = 0; i < size; i++) {
                buf[count++] = buf4[i];
                if (count == n) break;
            }
        }
        return count;
    }
}
