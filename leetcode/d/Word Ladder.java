/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
 * <p>
 * Only one letter can be changed at a time
 * Each intermediate word must exist in the word list
 * For example,
 * <p>
 * Given:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * <p>
 * Note:
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 */

public class Solution {
  // each word is a vertex, those one char different words are its edges
  public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
    int len = 0;
    Queue<String> q = new LinkedList<String>();
    Set<String> visited = new HashSet<String>();
    q.add(beginWord);
    visited.add(beginWord);
    // wordList.remove(beginWord);
    while (!q.isEmpty()) {
      len++;
      int size = q.size();
      while (size-- != 0) {
        String word = q.poll();
        if (word.equals(endWord)) return len;
        for (String neighbor : getNeighbors(wordList, word)) {
          if (!visited.contains(neighbor)) {
            visited.add(neighbor);
            q.add(neighbor);
          }
          // q.add(neighbor);
          // wordList.remove(neighbor);
        }
      }
    }
    return 0;
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

  // TLE
  private Set<String> getNeighborsSlow(Set<String> wordList, String word) {
    Set<String> neighbors = new HashSet<String>();
    for (String w : wordList) { // ERROR: this is too slow if dictionary is very big
      if (!w.equals(word) && w.length() == word.length()) {
        int count = 0;
        for (int i = 0; i < w.length(); i++) {
          if (w.charAt(i) != word.charAt(i)) count++;
        }
        if (count == 1) neighbors.add(w);
      }
    }
    return neighbors;
  }
}
