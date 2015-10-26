import java.util.ArrayList;

/**
 * Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when
 * necessary so that each line has exactly L characters.
 * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between
 * words, the empty slots on the left will be assigned more spaces than the slots on the right.
 * For the last line of text, it should be left justified and no extra space is inserted between words.
 * <p>
 * For example,
 * words: ["This", "is", "an", "example", "of", "text", "justification."]
 * L: 16.
 * <p>
 * Return the formatted lines as:
 * [
 * "This    is    an",
 * "example  of text",
 * "justification.  "
 * ]
 * Note: Each word is guaranteed not to exceed L in length.
 **/

public class Solution {
  public List<String> fullJustify(String[] words, int maxWidth) {
    List<String> ret = new ArrayList<>();
    List<String> line = new ArrayList<>();
    int charNum = 0;
    int wid = 0;
    for (int i = 0; i < words.length; ) {
      if (wid + words[i].length() <= maxWidth) {
        line.add(words[i]);
        charNum += words[i].length();
        wid += words[i].length() + 1;
        if (wid == maxWidth + 1) {
          String tmp = "";
          for (String w : line) {
            tmp += w + " ";
          }
          ret.add(tmp.substring(0, maxWidth));
          wid = 0;
          charNum = 0;
          line.clear();
        } else if (i == words.length - 1) {
          String tmp = "";
          for (String w : line) {
            tmp += w + " ";
          }
          while (tmp.length() < maxWidth) tmp += " ";
          ret.add(tmp);
        }
        i++;
      } else {
        int average = line.size() == 1 ? 0 : (maxWidth - charNum) / (line.size() - 1); // bug if a line contains only one word
        int extra = maxWidth - charNum - average * (line.size() - 1);
        String tmp = "";
        String white = "";
        while (average-- > 0) white += " ";
        for (String w : line) {
          tmp += w + white;
          if (extra-- > 0) tmp += " ";
        }
        while (tmp.length() < maxWidth)
          tmp += " "; // bug if a line contains only one word, padding white space at end
        ret.add(tmp.substring(0, maxWidth));
        wid = 0;
        charNum = 0;
        line.clear();
      }
    }
    return ret;
  }
}