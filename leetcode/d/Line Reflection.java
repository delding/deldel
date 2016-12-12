/**
 Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given points.

 Example 1:
 Given points = [[1,1],[-1,1]], return true.

 Example 2:
 Given points = [[1,1],[-1,-1]], return false.

 Follow up:
 Could you do better than O(n2)?

 Hint:

 Find the smallest and largest x-value for all points.
 If there is a line then it should be at y = (minX + maxX) / 2.
 For each point, make sure that it has a reflected point in the opposite side.
 **/

public class Solution {

	public boolean isReflectedWrong(int[][] points) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int[] point : points) {
			min = Math.min(point[0], min);
			max = Math.max(point[0], max);
		}
		// int mid = min + (max - min) / 2;
		int midDoulbe = min + max;
		Arrays.sort(points, (p1, p2) -> {
			if (p1[0] != p2[0]) return p1[0] - p2[0];
			else if (2 * p1[0] < midDoulbe) return p2[1] - p1[1];
			else if (2 * p1[0] > midDoulbe) return p1[1] - p2[1];
			else return 0;
		});
		int i = 0;
		int j = points.length - 1;
		while (i <= j) {
			if (points[i][0] + points[j][0] != midDoulbe) return false;
			else if (2 * points[i][0] != midDoulbe && points[i][1] != points[j][1]) return false;
			i++;
			j--;
		}
		return true;
	}

	// this problem allows one point being reflective to mutiple points at same position
	public boolean isReflected(int[][] points) {
		HashSet<Integer> pointSet = new HashSet<>();
		int sum;
		int maxX, minX;

		minX = Integer.MAX_VALUE;
		maxX = Integer.MIN_VALUE;
		for(int[] point:points) {
			maxX = Math.max(maxX, point[ 0 ]);
			minX = Math.min(minX, point[ 0 ]);
			pointSet.add(Arrays.hashCode(point));
		}

		sum = maxX+minX;
		for(int[] point:points) {
			if(!pointSet.contains(Arrays.hashCode(new int[]{sum-point[ 0 ], point[ 1 ]}))) {
				return false;
			}
		}
		return true;
	}

}