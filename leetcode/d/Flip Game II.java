/**
 * You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.
 * <p>
 * Write a function to determine if the starting player can guarantee a win.
 * <p>
 * For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+".
 * <p>
 * Follow up:
 * Derive your algorithm's runtime complexity.
 **/

public class Solution {
  public boolean canWin(String s) {
    List<String> moves = generatePossibleNextMoves(s);
    if (moves.size() == 0) return false;
    for (String move : moves) {
      if (!canWin(move)) return true;
    }
    return false;
  }

  private List<String> generatePossibleNextMoves(String s) {
    List<String> res = new ArrayList<String>();
    for (int i = 0; i < s.length() - 1; i++) {
      if (s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
        res.add(s.substring(0, i) + "--" + s.substring(i + 2));
      }
    }
    return res;
  }
}