/**
 You are given two jugs with capacities x and y litres. There is an infinite amount of water supply available. You need to determine whether it is possible to measure exactly z litres using these two jugs.

 If z liters of water is measurable, you must have z liters of water contained within one or both buckets by the end.

 Operations allowed:

 Fill any of the jugs completely with water.
 Empty any of the jugs.
 Pour water from one jug into another till the other jug is completely full or the first jug itself is empty.
 Example 1: (From the famous "Die Hard" example)

 Input: x = 3, y = 5, z = 4
 Output: True
 Example 2:

 Input: x = 2, y = 6, z = 5
 Output: False
 **/

public class Solution {

/*
ax + by is a multiple of the greatest common divisor d.
If a or b is negative this means we are emptying a jug of x or y gallons respectively.
Similarly if a or b is positive this means we are filling a jug of x or y gallons respectively.
x = 4, y = 6, z = 8.
GCD(4, 6) = 2, 8 is multiple of 2 so this input is valid and we have: -1 * 4 + 6 * 2 = 8
In this case, there is a solution obtained by filling the 6 gallon jug twice and emptying the 4 gallon jug once. (Solution. Fill the 6 gallon jug and empty 4 gallons to the 4 gallon jug. Empty the 4 gallon jug. Now empty the remaining two gallons from the 6 gallon jug to the 4 gallon jug. Next refill the 6 gallon jug. This gives 8 gallons in the end)
 */
    public boolean canMeasureWater(int x, int y, int z) {
		if (z == 0) return true;
		if (x == 0 || y == 0) return x + y == z;
		if (x + y < z) return false;
		int gcd = gcd(Math.max(x,y), Math.min(x,y));
		return z % gcd == 0;
	}

	int gcd(int x, int y) {
		if (y == 0) return x;
		return gcd(y, x % y);
	}

	// bfs
	public class Solution {
		public boolean canMeasureWater(int x, int y, int z) {
			Set<Pair> visited = new HashSet<>();
			Queue<Pair> q = new ArrayDeque<>();
			q.add(new Pair(0, 0));
			visited.add(q.peek());
			while (!q.isEmpty()) {
				Pair p = q.poll();
				if (p.x == z || p.y == z || p.x + p.y == z) return true;
				List<Pair> nextStates = new ArrayList<>();
				nextStates.add(new Pair(0, p.y)); // empty x
				nextStates.add(new Pair(p.x, 0)); // empty y
				nextStates.add(new Pair(x, p.y)); // fill x
				nextStates.add(new Pair(p.x, y)); // flll y
				nextStates.add(new Pair(Math.max(0, p.x - (y - p.y)), Math.min(y, p.x + p.y))); // pour x to y
				nextStates.add(new Pair(Math.min(x, p.x + p.y), Math.max(0, p.y - (x - p.x)))); // pour y to x
				for (Pair state : nextStates) {
					if (!visited.contains(state)) {
						visited.add(state);
						q.add(state);
					}
				}
			}
			return false;
		}

		class Pair {
			int x;
			int y;

			Pair(int x, int y) {
				this.x = x;
				this.y = y;
			}

			public int hashCode() {
				return x ^ y;
			}

			public boolean equals(Object that) {
				Pair t = (Pair) that;
				return this.x == t.x && this.y == t.y;
			}
		}
	}


	// dfs cause some test case stack overflow
	public boolean canMeasureWaterDFS(int x, int y, int z) {
		Map<Integer, Set<Integer>> memo = new HashMap<>(); // on stack memo for detect cycle
		Map<Integer, Set<Integer>> globalMemo = new HashMap<>(); // to mark node as visited
		return search(x, y, z, 0, 0, memo, globalMemo);
	}

	boolean search(int xCap, int yCap, int z, int x, int y, Map<Integer, Set<Integer>> memo, Map<Integer, Set<Integer>> globalMemo) {
		if (x + y == z) return true;
		if (globalMemo.containsKey(x) && globalMemo.get(x).contains(y)) return false;
		if (memo.containsKey(x) && memo.get(x).contains(y)) return false;
		addToMemo(x, y, memo);
		if (x < xCap && search(xCap, yCap, z, xCap, y, memo, globalMemo)) { // fill x
			return true;
		}
		if (x > 0 && search(xCap, yCap, z, 0, y, memo, globalMemo)) { // empty x
			return true;
		}
		if (y < yCap && search(xCap, yCap, z, x, yCap, memo, globalMemo)) { // fill y
			return true;
		}
		if (y > 0 && search(xCap, yCap, z, x, 0, memo, globalMemo)) { // empty y
			return true;
		}
		if (x != xCap && y != 0 && search(xCap, yCap, z, Math.min(x + y, xCap), Math.max(0, y - (xCap - x)), memo, globalMemo)) { // pour from y to x
			return true;
		}
		if (x != 0 && y != yCap && search(xCap, yCap, z, Math.max(0, x - (yCap - y)), Math.min(x + y, yCap), memo, globalMemo)) { // pour from x to y
			return true;
		}
		removeFromMemo(x, y, memo);
		addToMemo(x, y, globalMemo);
		return false;
	}

	void addToMemo(int x, int y, Map<Integer, Set<Integer>> memo) {
		Set<Integer> ys = memo.get(x);
		if (ys == null) {
			ys = new HashSet<>();
			memo.put(x, ys);
		}
		ys.add(y);
	}

	void removeFromMemo(int x, int y, Map<Integer, Set<Integer>> memo) {
		memo.get(x).remove(y);
	}
}
