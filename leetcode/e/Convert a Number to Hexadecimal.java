/**
 Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two’s complement method is used.

 Note:

 All letters in hexadecimal (a-f) must be in lowercase.
 The hexadecimal string must not contain extra leading 0s. If the number is zero, it is represented by a single zero character '0'; otherwise, the first character in the hexadecimal string will not be the zero character.
 The given number is guaranteed to fit within the range of a 32-bit signed integer.
 You must not use any method provided by the library which converts/formats the number to hex directly.
 Example 1:

 Input:
 26

 Output:
 "1a"
 Example 2:

 Input:
 -1

 Output:
 "ffffffff"
 **/

public class Solution {
	public String toHex(int num) {
		String res = "";
		int mask = 0x000f;
		while (num != 0) {
			int d = num & mask;
			res = hex(d % 16) + res;
			num >>>= 4;
		}
		if (res.isEmpty()) res = "0";
		return res;
	}

	String hex(int num) {
		if (num < 10) return String.valueOf(num);
		else return String.valueOf((char)(num - 10 + 'a'));
	}
}