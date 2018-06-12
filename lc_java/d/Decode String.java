/**
 Given an encoded string, return it's decoded string.

 The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

 You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

 Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

 Examples:

 s = "3[a]2[bc]", return "aaabcbc".
 s = "3[a2[c]]", return "accaccacc".
 s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 **/

public class Solution {
	public String decodeString(String s) {
		ArrayDeque<Integer> stackInt = new ArrayDeque<>();
		ArrayDeque<Character> stackChar = new ArrayDeque<>();
		StringBuilder num = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '[') {
				stackChar.push(c);
				stackInt.push(Integer.parseInt(num.toString()));
				num.delete(0, num.length());
			} else if (c >= '0' && c <= '9') {
				num.append(c);
			} else if (c >= 'a' && c <= 'z') {
				stackChar.push(c);
			} else if (c == ']') {
				int repeat = stackInt.pop();
				String ss = "";
				while (stackChar.peek() != '[') {
					ss = stackChar.pop() + ss;
				}
				stackChar.pop();
				while(repeat-- > 0) {
					for (char cc : ss.toCharArray()) stackChar.push(cc);
				}
			}
		}
		StringBuilder res = new StringBuilder();
		while (!stackChar.isEmpty()) {
			res.append(stackChar.pop());
		}
		return res.reverse().toString();
	}
}