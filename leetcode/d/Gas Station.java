/**
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1).
 * You begin the journey with an empty tank at one of the gas stations.
 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 */

public class Solution {
  public int canCompleteCircuit(int[] gas, int[] cost) {
    int len = gas.length;
    int start = 0;
    int curr = 0;
    int currgas = 0;
    while (start < len) { // try each station as a start
      do {
        currgas += gas[curr];
        currgas -= cost[curr];
        curr = (curr + 1) % len;
      } while (currgas >= 0 && curr != start);
      if (currgas >= 0 && curr == start) return start;
      while (currgas < 0 && start < len) { // ERROR: must add start < len, because if return -1, start can get to len at here
        currgas = currgas - gas[start] + cost[start];
        start++;
      }
    }
    return -1;
  }
}
