/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
 * <p>
 * Only one letter can be changed at a time
 * Each intermediate word must exist in the word list
 * For example,
 * <p>
 * Given:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * Return
 * [
 * ["hit","hot","dot","dog","cog"],
 * ["hit","hot","lot","log","cog"]
 * ]
 **/

// TLE: todo, don't pass that large data set where each word only has two character, need to optimize for this situation
public class Solution {

  // must be able to track multiple edges from different vertex at current level to the same vertex at next level
  public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
    Set<String> visited = new HashSet<String>();
    Set<String> inqueue = new HashSet<String>();
    Queue<String> q = new LinkedList<String>();
    Map<String, List<String>> edges = new HashMap();
    q.add(beginWord);
    while (!edges.containsKey(endWord) && !q.isEmpty()) {
      int size = q.size();
      while (size-- != 0) {
        String word = q.poll();
        visited.add(word);
        for (String neighbor : getNeighbors(wordList, word)) {
          if (!visited.contains(neighbor)) {
            List<String> backvertices = edges.get(neighbor);
            if (backvertices == null) {
              backvertices = new ArrayList<String>();
              edges.put(neighbor, backvertices);
            }
            backvertices.add(word);
            if (!inqueue.contains(neighbor)) {
              q.add(neighbor);
              inqueue.add(neighbor);
            }
          }
        }
      }
    }
    List<List<String>> paths = new ArrayList<List<String>>();
    List<String> path = new ArrayList<String>();
    if (edges.containsKey(endWord)) { // ERROR: need this condition to guard the case when ladder is not found
      dfs(paths, path, edges, endWord, beginWord);
    }
    return paths;
  }

  private void dfs(List<List<String>> paths, List<String> path, Map<String, List<String>> edges, String end, String begin) {
    path.add(end); // ERROR: MUST add here, my path doesn't add first word
    if (end.equals(begin)) {
      List<String> copy = new ArrayList<String>(path);
      Collections.reverse(copy);
      paths.add(copy);
    } else {
      for (String prev : edges.get(end)) {
        dfs(paths, path, edges, prev, begin);
      }
    }
    path.remove(path.size() - 1);
  }

  private Set<String> getNeighbors(Set<String> wordList, String word) {
    Set<String> neighbors = new HashSet<String>();
    char[] arr = word.toCharArray();
    for (int i = 0; i < arr.length; i++) {
      for (char j = 'a'; j <= 'z'; j++) { // iterate over 26 char is more effcient than below which iterate all dictionary
        if (j != arr[i]) {
          char c = arr[i];
          arr[i] = j;
          String w = new String(arr);
          if (wordList.contains(w)) neighbors.add(w);
          arr[i] = c;
        }
      }
    }
    return neighbors;
  }
}
