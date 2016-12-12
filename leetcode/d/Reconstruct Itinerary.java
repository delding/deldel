/**
 Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order.
 All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

 Note:
 If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.
 For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 All airports are represented by three capital letters (IATA code).
 You may assume all tickets form at least one valid itinerary.
 Example 1:
 tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
 Example 2:
 tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
 Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.
 **/

public class Solution {
	public List<String> findItinerary(String[][] tickets) {
		Map<String, List<String>> edges = new HashMap<>();
		for (String[] ticket : tickets) {
			String from = ticket[0];
			String to = ticket[1];
			List<String> tos = edges.get(from);
			if (tos == null) {
				tos = new ArrayList<String>();
				edges.put(from, tos);
			}
			tos.add(to);
		}
		for (List<String> tos : edges.values()) {
			Collections.sort(tos);
		}
		List<String> ret = new ArrayList<>();
		ret.add("JFK");
		dfs(edges, ret, tickets.length);
		return ret;
	}

	boolean dfs(Map<String, List<String>> edges, List<String> ret, int num) {
		if (ret.size() == num + 1) return true;
		String from = ret.get(ret.size() - 1);
		List<String> tos = edges.get(from) == null ? new ArrayList<>() : edges.get(from); // desitination might not have tos list
		for (int i = 0; i < tos.size(); i++) {
			String to = tos.get(i);
			if (to != null) {
				ret.add(to);
				tos.set(i, null); // remove this ticket
				if (dfs(edges, ret, num)) return true;
				ret.remove(ret.size() - 1);
				tos.set(i, to);
			}
		}
		return false;
	}
}