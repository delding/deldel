/**
 You are playing the following Nim Game with your friend: There is a heap of stones on the table, each time one of you take turns to
 remove 1 to 3 stones. The one who removes the last stone will be the winner. You will take the first turn to remove the stones.
 Both of you are very clever and have optimal strategies for the game. Write a function to determine whether you can win the game
 given the number of stones in the heap.
 For example, if there are 4 stones in the heap, then you will never win the game: no matter 1, 2, or 3 stones you remove, the last
 stone will always be removed by your friend.
**/

public class Solution {
  public boolean canWinNim(int n) {
    // 1110 1110
    return n % 4 != 0;
  }

  // TLE
  public boolean canWinNim1(int n) {
    boolean win = true;
    if (n < 4) return win;
    boolean win1 = true;
    boolean win2 = true;
    boolean win3 = true;
    for (int i = 4; i <= n; i++) {
      if(win1 && win2 && win3) win = false;
      else win = true;
      win1 = win2;
      win2 = win3;
      win3 = win;
    }
    return win;
  }

  // TLE
  private boolean canWinNim2(int n, Map<Integer, Boolean> memo) {
    if (memo.containsKey(n)) return memo.get(n);
    if (n < 4) return true;
    for (int i = 1; i < 4; i++) {
      if (!canWinNim(n - i)) {
        memo.put(n, true);
        return true;
      }
    }
    memo.put(n, false);
    return false;
  }
}
