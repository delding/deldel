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
    List<String> text = new ArrayList<>();
    String row = "";
    for (int i = 0; i <= words.length; i++) {
      if (i == words.length) { // i == words.length to handle last line
        if (!row.isEmpty()) {
          while (row.length() < maxWidth) row = row + " ";
          text.add(row);
        }
      } else if (row.length() + words[i].length() == maxWidth) {
        text.add(row + words[i]);
        row = "";
      } else if (row.length() + words[i].length() < maxWidth) {
        row = row + words[i] + " ";
      } else {
        String[] wds = row.trim().split(" ");
        int wdsLen = row.length() - wds.length;
        int spcLen = maxWidth - wdsLen;
        row = "";
        if (wds.length == 1) {
          row = wds[0];
          for (int j = 0; j < spcLen; j++) row = row + " ";
          text.add(row);
        } else {
          int avgSpc = spcLen / (wds.length - 1);
          int addSpc = spcLen % (wds.length - 1);
          String spc = "";
          while (avgSpc-- > 0) spc = spc + " ";
          for (int j = 0; j < wds.length; j++) {
            row = row + wds[j];
            if (j < wds.length - 1) row = row + spc; // no space after last word
            if (addSpc > 0) {
              row = row + " ";
              addSpc--;
            }
          }
          text.add(row);
        }
        row = ""; // reset to empty
        i--; // not move to next word
      }
    }
    return text;
  }
}