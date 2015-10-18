/**
 * Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

For example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
Note:
You may assume that all words are consist of lowercase letters a-z.
* */

public class WordDictionary {
    
    private TrieNode root = new TrieNode();
    
    // Adds a word into the data structure.
    public void addWord(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (curr.edges[idx] == null) curr.edges[idx] = new TrieNode();
            curr = curr.edges[idx];
        }
        curr.isWord = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return search(word, root);
    }
    
    private boolean search(String word, TrieNode node) {
        if (word.length() == 0) return node.isWord;
        char first = word.charAt(0);
        if (first == '.') {
            for (TrieNode child : node.edges) {
                if (child != null) {
                    if (search(word.substring(1), child)) return true;
                }
            }
        } else {
            int idx = first - 'a';
            if (node.edges[idx] != null) {
                if (search(word.substring(1), node.edges[idx])) return true;
            }
        }
        return false;
    }
    
    class TrieNode {
        boolean isWord;
        TrieNode[] edges;
        
        TrieNode() {
            isWord = false;
            edges = new TrieNode[26];
        }
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");
