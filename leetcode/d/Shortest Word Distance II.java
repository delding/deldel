/**
 * This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and your method will be called repeatedly many times with different parameters. How would you optimize it?

Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “coding”, word2 = “practice”, return 3.
Given word1 = "makes", word2 = "coding", return 1.

Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
* */

public class WordDistance {

    Map<String, List<Integer>> indexes = new HashMap();
    
    public WordDistance(String[] words) {
        for (int i = 0; i< words.length; i++) {
            String word = words[i];
            List<Integer> list = indexes.get(word);
            if (list == null) {
                list = new ArrayList<Integer>();
                indexes.put(word, list);
            }
            list.add(i); // Error: index is automatically from low to high, no need to sort
        }    
    }

    public int shortest(String word1, String word2) {
        List<Integer> list1 = indexes.get(word1); 
        List<Integer> list2 = indexes.get(word2);
        int dist = Integer.MAX_VALUE;
        for (int i=0, j=0; i<list1.size() && j < list2.size();  ) { // Either update i or update j, only one of them
            int idx1 = list1.get(i);
            int idx2 = list2.get(j);
            if (idx1 < idx2) {
                dist = Math.min(dist, idx2 - idx1);
                i++;
            } else {
                dist = Math.min(dist, idx1 - idx2);
                j++;
            }
        }
        
        return dist;
    }
}

// Your WordDistance object will be instantiated and called as such:
// WordDistance wordDistance = new WordDistance(words);
// wordDistance.shortest("word1", "word2");
// wordDistance.shortest("anotherWord1", "anotherWord2");
