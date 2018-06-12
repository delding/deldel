/**
 A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).

 Each LED represents a zero or one, with the least significant bit on the right.


 For example, the above binary watch reads "3:25".

 Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.

 Example:

 Input: n = 1
 Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 Note:
 The order of output does not matter.
 The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
 The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".
 **/

public class Solution {
	public List<String> readBinaryWatch(int num) {
		List<String> res = new ArrayList<>();
		dfs(new boolean[10], 0, num, res);
		return res;
	}

	void dfs(boolean[] clock, int idx, int num, List<String> res) {
		if (num == 0) {
			String t = time(clock);
			if (!t.isEmpty()) res.add(t);
			return;
		}
		for (int i = idx; i < 10; i++) {
			clock[i] = true;
			dfs(clock, i + 1, num - 1, res);
			clock[i] = false;
		}
	}

	String time(boolean[] clock) {
		int hr = 0;
		for (int i = 0; i < 4; i++) {
			if (clock[i]) hr += 1 << (3 - i);
		}
		if (hr > 11) return "";
		int min = 0;
		for (int i = 0; i < 6; i++) {
			if (clock[i + 4]) min += 1 << (5 - i);
		}
		if (min > 59) return "";
		String minStr;
		if (min < 10) minStr = "0" + min;
		else minStr = String.valueOf(min);
		return hr + ":" + minStr;
	}

	// a very neat solution
	public List<String> readBinaryWatch1(int num) {
		List<String> times = new ArrayList<>();
		for (int h=0; h<12; h++)
			for (int m=0; m<60; m++)
				if (Integer.bitCount((h << 6) + m) == num)
					times.add(String.format("%d:%02d", h, m));
		return times;
	}
}