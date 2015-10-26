/**
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).
 * <p>
 * Buildings  Skyline Contour
 * The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
 * <p>
 * For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
 * <p>
 * The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.
 * <p>
 * For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
 * <p>
 * Notes:
 * <p>
 * The number of buildings in any input list is guaranteed to be in the range [0, 10000].
 * The input list is already sorted in ascending order by the left x position Li.
 * The output list must be sorted by the x position.
 * There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
 */

// Solution 1, maxQueue, has bugs    
// sort by left point (input already did), maintain skyline height and right, if current building's left smaller than skyline right, put current
// building's right with height in max-heap, update current skyline height to be max of two; if next buidling'left bigger than current building's right,
// take top of max-heap until its right bigger than current position.
public class Solution {
  public List<int[]> getSkyline(int[][] buildings) {
    List<int[]> skyline = new ArrayList<int[]>();
    if (buildings.length == 0) return skyline;
    Arrays.sort(buildings, new Comparator<int[]>() {
      public int compare(int[] b1, int[] b2) {
        if (b1[0] == b2[0])
          return b2[2] - b1[2]; // ERROR: if left point is equal must make the highest building comes first
        else return b1[0] - b2[0];
      }
    });
    PriorityQueue<int[]> maxq = new PriorityQueue<int[]>(1, new Comparator<int[]>() {
      public int compare(int[] b1, int[] b2) {
        return b2[2] - b1[2];
      }
    });
    int skylineH = 0;
    int skylineR = 0;
    for (int i = 0; i < buildings.length; i++) {
      int[] b = buildings[i];
      if (skylineR == 0) {
        skyline.add(new int[]{b[0], b[2]});
        skylineR = b[1];
        skylineH = b[2];
        maxq.add(b);
        continue;
      }
      while (skylineR < b[0] && !maxq.isEmpty()) { // all heights in maxq are <= skylineH
        while (!maxq.isEmpty() && maxq.peek()[1] <= skylineR)
          maxq.poll(); // ERROR: take out useless building
        if (maxq.isEmpty()) break;
        int[] bb = maxq.peek(); // ERROR: this is critical!! the building whose right point hasn't expired must stay in maxq
        if (bb[2] < skylineH) { // ERROR: if same height, don't add strip, just update skylineR
          skyline.add(new int[]{skylineR, bb[2]});
          skylineH = bb[2];
        }
        skylineR = bb[1];
      }
      if (skylineR < b[0]) { // at this time maxq is empty, all buildings finish before next buidling comes
        skyline.add(new int[]{skylineR, 0});
        skylineH = 0;
        skylineR = Integer.MAX_VALUE;
      }
      if (skylineH < b[2]) { // this time, skylineR >= b[0]
        skyline.add(new int[]{b[0], b[2]});
        skylineH = b[2];
        skylineR = b[1];
      }
      maxq.add(b);
    }
    while (!maxq.isEmpty()) {
      int[] b = maxq.poll(); // don't add building anymore, so just take out a building each time
      if (b[1] > skylineR) {
        if (b[2] < skylineH) { // ERROR: if same height, don't add strip, just update skylineR
          skyline.add(new int[]{skylineR, b[2]});
          skylineH = b[2];
        }
        skylineR = b[1];
      }
    }
    skyline.add(new int[]{skylineR, 0}); // ERROR: could add [0,0] if input is empty
    return skyline;
  }
}

/* Solution 2, divide and conquer, has bugs

public class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        if (buildings.length == 0) return new ArrayList<int[]>();
        return divideConquer(buildings, 0, buildings.length - 1);
    }   
    
    private List<int[]> divideConquer(int[][] buildings, int lo, int hi) {
        if (lo == hi) {
            return singleBuilding(buildings[lo]);
        }
        int mid = lo + (hi - lo) / 2;
        List<int[]> skyline1 = divideConquer(buildings, lo, mid);
        List<int[]> skyline2 = divideConquer(buildings, mid + 1, hi);
        return mergeSkyline(skyline1, skyline2);
    }
    
    private List<int[]> singleBuilding(int[] b) {
        List<int[]> skyline = new ArrayList<int[]>();
        int[] left = new int[] {b[0], b[2]};
        int[] right = new int[] {b[1], 0};
        skyline.add(left);
        skyline.add(right);
        return skyline;
    }
    
    private List<int[]> mergeSkyline(List<int[]> skyline1, List<int[]> skyline2) {
        List<int[]> merged = new ArrayList<int[]>();
        int i = 0;
        int j = 0;
        // ERROR: this is the tricky part, maintain the current height for both skylines, each time merge a skyline, compare its height
        // with the other and take the max of two, this is when higher skyline cover lower skyline
        int h1 = 0;
        int h2 = 0;
        int h = 0;
        while (i < skyline1.size() || j < skyline2.size()) {
            if (i == skyline1.size()) { // if one skyline is empty, since it's last strip must be 0, the non-empty skyline can not be covered
                merged.add(skyline2.get(j++));
                continue;
            }
            if (j == skyline2.size()) {
                merged.add(skyline1.get(i++));
                continue;
            }
            int currH = 0;
            int l[] = skyline1.get(i);
            int r[] = skyline2.get(j);
            if (l[0] == r[0]) {// Error: although existing skyline will not conflict this strip, the case of same height could occurs
                h1 = l[1];
                h2 = r[1];
                currH = Math.max(h1,h2);
                if (currH != h) {
                    merged.add(new int[] {l[0], currH});  
                    h = currH;
                }
                i++;
                j++;
            } else if (l[0] < r[0]) {
                h1 = l[1];
                currH = Math.max(h1, h2);
                if (currH != h1) {
                    merged.add(new int[] {l[0], currH});
                    h = currH;
                }
                i++;
            } else {
                h2 = r[1];
                currH = Math.max(h1, h2);
                if (currH != h2) {
                    merged.add(new int[] {r[0], currH});
                    h = currH;
                }
                j++;
            }
        }
        return merged;
    }
}
*/
