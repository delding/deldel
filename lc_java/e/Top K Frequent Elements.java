/**
 Given a non-empty array of integers, return the k most frequent elements.

 For example,
 Given [1,1,1,2,2,3] and k = 2, return [1,2].

**/

public class Solution {
	public List<Integer> topKFrequent(int[] nums, int k) {
		Map<Integer, Integer> memo = new HashMap<>();
		for (int num : nums) {
			if (!memo.containsKey(num)) {
				memo.put(num, 1);
			} else memo.put(num, memo.get(num) + 1);
		}
		PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(k, (v1, v2) -> v2.getValue() - v1.getValue());
		pq.addAll(memo.entrySet());
		// An O(n) solution is to use bucket sort, each bucket represents a frequency
		List<Integer> ret = new ArrayList<>();
		while(k-- > 0) ret.add(pq.poll().getKey());
		return ret;
	}
}