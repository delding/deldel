/**
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

For example,
Given the following words in dictionary,

[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".

Note:
You may assume all letters are in lowercase.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.
* */

public class Solution {
    public String alienOrder(String[] words) {
        Map<Character, HashSet<Character>> adj = new HashMap<Character, HashSet<Character>>();
        HashMap<Character, Boolean> visited = new HashMap();
        HashMap<Character, Boolean> visiting = new HashMap();
        // Error: don't put non existent node in the adjacent list, which makes the node become an zero edge isolated node
        for (String w : words) {
			for (char c : w.toCharArray()) {
				if (!adj.containsKey(c)) {
					adj.put(c, new HashSet<Character>());  
					visited.put(c, false);
					visiting.put(c, false);
				}
			}
		}
		
		// ERROR: must be an O(n^2) operation, compare word with all the words after it, each comparison add an edge		
        for (int i =0; i < words.length - 1; i++) {
            for (int j = i+1; j < words.length; j++){
                addEdge(words[i], words[j], adj);
            }
        }
        
        String rst = topoSort(adj, visited, visiting);
        return rst;
    }
    
    private void addEdge(String w1, String w2, Map<Character, HashSet<Character>> adj) {
        int len = Math.min(w1.length(), w2.length());
        for (int i = 0; i < len; i++) {
            char c1 = w1.charAt(i);
            char c2 = w2.charAt(i);
            if (c1 != c2) {
                adj.get(c1).add(c2);
                return; // Error: must only add the first different char pair as an edge
            }
        }

    }
    
    private String topoSort(Map<Character, HashSet<Character>> adj, HashMap<Character, Boolean> visited, HashMap<Character, Boolean> visiting) {
        StringBuilder postorder = new StringBuilder();

        for (char c : adj.keySet()){ // ERROR: only go through key in the hashmap, don't iterate through chars not occured in the input
            if (!visited.get(c)) {
                if(hasCycle(adj, c, visited, visiting, postorder)) return ""; // if has cycle return empty string
            }
        }
        return postorder.reverse().toString();
    }
    
    // dfs while check cycle
    private boolean hasCycle(Map<Character, HashSet<Character>> adj, char c, HashMap<Character, Boolean> visited, HashMap<Character, Boolean> visiting, StringBuilder postorder) {
        visiting.put(c, true);
        for (char child : adj.get(c)) {
            if (!visited.get(child)) {
                if (visiting.get(child)) return true;
                else {
                    if (hasCycle(adj, child, visited, visiting, postorder)) return true;
                }
            }
        }
        postorder.append(c);
                visiting.put(c, false);

        visited.put(c, true);
        return false;
    }
    
}
