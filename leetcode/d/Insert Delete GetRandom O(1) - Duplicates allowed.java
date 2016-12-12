/**
 Design a data structure that supports all following operations in average O(1) time.

 Note: Duplicate elements are allowed.
 insert(val): Inserts an item val to the collection.
 remove(val): Removes an item val from the collection if present.
 getRandom: Returns a random element from current collection of elements. The probability of each element being returned is linearly related to the number of same value the collection contains.
 Example:

 // Init an empty collection.
 RandomizedCollection collection = new RandomizedCollection();

 // Inserts 1 to the collection. Returns true as the collection did not contain 1.
 collection.insert(1);

 // Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
 collection.insert(1);

 // Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
 collection.insert(2);

 // getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
 collection.getRandom();

 // Removes 1 from the collection, returns true. Collection now contains [1,2].
 collection.remove(1);

 // getRandom should return 1 and 2 both equally likely.
 collection.getRandom();
 **/

public class RandomizedCollection {

	ArrayList<Integer> vals;
	HashMap<Integer, LinkedHashSet<Integer>> map;

	public RandomizedCollection() {
		vals = new ArrayList<Integer>();
		map = new HashMap<Integer, LinkedHashSet<Integer>>();
	}

	/** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
	public boolean insert(int val) {
		// Add item to map if it doesn't already exist.
		boolean exist = map.containsKey(val);
		if(!exist) {
			map.put(val, new LinkedHashSet<Integer>());
		}
		map.get(val).add(result.size());
		result.add(val);
		return !exist;
	}

	/** Removes a value from the collection. Returns true if the collection contained the specified element. */
	public boolean remove(int val) {
		if(!map.containsKey(val)) {
			return false;
		}
		// Get arbitary index of the ArrayList that contains val
		LinkedHashSet<Integer> posSet = map.get(val);
		int indexToReplace = posSet.iterator().next();

		// Obtain the set of the number in the last place of the ArrayList
		int numAtLastPlace = vals.get(vals.size() - 1);
		LinkedHashSet<Integer> replaceWith = map.get(numAtLastPlace);

		// Replace val at arbitary index with very last number
		vals.set(indexToReplace, numAtLastPlace);

		// Remove appropriate index
		posSet.remove(indexToReplace);

		// Don't change set if we were replacing the removed item with the same number
		if(indexToReplace != vals.size() - 1) {
			replaceWith.remove(vals.size() - 1);
			replaceWith.add(indexToReplace);
		}
		vals.remove(vals.size() - 1);

		// Remove map entry if set is now empty, then return
		if(posSet.isEmpty()) {
			map.remove(val);
		}
		return true;
	}

	/** Get a random element from the collection. */
	public int getRandom() {
		// Get linearly random item
		return result.get(new Random().nextInt(vals.size()));
	}
}

/** has bug
 public class RandomizedCollection {
 Map<Integer, LinkedHashSet<Integer>> valueToIndices = new HashMap<>();
 List<Integer> values = new ArrayList<>();

 // Initialize your data structure here.
 public RandomizedCollection() {

 }

 // Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
 public boolean insert(int val) {
 boolean res = false;
 if (!valueToIndices.containsKey(val)) {
 res = true;
 valueToIndices.put(val, new LinkedHashSet<>());
 }
 LinkedHashSet<Integer> indices = valueToIndices.get(val);
 indices.add(values.size());
 values.add(val);
 return res;
 }

 // Removes a value from the collection. Returns true if the collection contained the specified element.
 public boolean remove(int val) {
 if (!valueToIndices.containsKey(val)) return false;
 LinkedHashSet<Integer> indices = valueToIndices.get(val);
 int indexToRemove = indices.iterator().next();

 int lastVal = values.get(values.size() - 1);
 LinkedHashSet<Integer> lastValIndices = valueToIndices.get(lastVal);

 if (indexToRemove != values.size() - 1) { // must have this check, otherwise indexToRemove will be removed in case of false condition
 lastValIndices.add(indexToRemove);
 lastValIndices.remove(values.size() - 1);
 }

 values.set(indexToRemove, lastVal);
 values.remove(values.size() - 1);

 indices.remove(indexToRemove); // make remove the last operation otherwise bug when indices == lastValIndices
 if (indices.isEmpty()) {
 valueToIndices.remove(val);
 }
 return true;
 }

 // Get a random element from the collection.
 public int getRandom() {
 int randomIdx = new Random().nextInt(values.size());
 return values.get(randomIdx);
 }
 }

 **/

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */