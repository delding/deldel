/**
 * Design and implement a TwoSum class. It should support the following operations: add and find.

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

For example,
add(1); add(3); add(5);
find(4) -> true
find(7) -> false
* */

public class TwoSum {
    
    Map<Integer, Integer> numberCnt = new HashMap(); //ERROR: need to store the count of each number to make possible if same number can add to the value
    // Add the number to an internal data structure.
	public void add(int number) {
	    Integer cnt = numberCnt.get(number);
	    if (cnt == null) numberCnt.put(number, 1);
	    else numberCnt.put(number, cnt+1);
	}

    // Find if there exists any pair of numbers which sum is equal to the value.
	public boolean find(int value) {
	    for (int num1 : numberCnt.keySet()) {
	        int num2 = value - num1;
	        Integer cnt = numberCnt.get(num2);
	        if (cnt == null) continue; // ERROR: dont return false, continue try next number
	        else if (num2 == num1 && cnt == 1) continue; 
	        else return true; // ERROR: if two numbers are equal, the count of number must at least 2
	    }
	    return false; // ERROR: return false if numberCnt is empty
	}
}


// Your TwoSum object will be instantiated and called as such:
// TwoSum twoSum = new TwoSum();
// twoSum.add(number);
// twoSum.find(value);
