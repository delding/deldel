/**
 * This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words
 * and your method will be called repeatedly many times with different parameters. How would you optimize it?
 * <p>
 * Design a class which receives a list of words in the constructor, and implements a method that takes two
 * words word1 and word2 and return the shortest distance between these two words in the list.
 * <p>
 * For example,
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 * <p>
 * Given word1 = “coding”, word2 = “practice”, return 3.
 * Given word1 = "makes", word2 = "coding", return 1.
 * <p>
 * Note:
 * You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 */

public class WordDistance {
  Map<String, List<Integer>> positions = new HashMap<>();

  public WordDistance(String[] words) {
    for (int i = 0; i < words.length; i++) {
      if (!positions.containsKey(words[i])) {
        positions.put(words[i], new ArrayList<>());
      }
      positions.get(words[i]).add(i);
    }
  }

  public int shortest(String word1, String word2) {
    int d = Integer.MAX_VALUE;
    List<Integer> p1 = positions.get(word1);
    List<Integer> p2 = positions.get(word2);
    for (int i1 = 0, i2 = 0; i1 < p1.size() && i2 < p2.size();) {
      d = Math.min(d, Math.abs(p1.get(i1) - p2.get(i2)));
      if (p1.get(i1) > p2.get(i2)) i2++;
      else i1++;
    }
    return d;
  }
}

// Your WordDistance object will be instantiated and called as such:
// WordDistance wordDistance = new WordDistance(words);
// wordDistance.shortest("word1", "word2");
// wordDistance.shortest("anotherWord1", "anotherWord2");