/*
 * An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:

a) it                      --> it    (no abbreviation)

     1
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
d) l|ocalizatio|n          --> l10n
Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.

Example: 
Given dictionary = [ "deer", "door", "cake", "card" ]

isUnique("dear") -> false
isUnique("cart") -> true
isUnique("cane") -> false
isUnique("make") -> true
* /
public class ValidWordAbbr {

    Map<String, Set<String>> abbrToWords = new HashMap(); 

    public ValidWordAbbr(String[] dictionary) {
        for (String word : dictionary) {
            String abbr = abbr(word);
            Set<String> words = abbrToWords.get(abbr);
            if (words == null) {
                words = new HashSet<String>();
                abbrToWords.put(abbr, words);
            }
            words.add(word);
        }
    }
    

    public boolean isUnique(String word) {
        Set<String> set = abbrToWords.get(abbr(word));
        if (set == null) return true; // ERROR: no abbr meas true
        if (set.size() != 1 || !set.contains(word)) return false; // ERROR: even if abbr exist and size = 1, need to check if query word is the word in dict
        return true;
    }
    
    private String abbr(String word) {
        if (word.length() <= 2) return word;
        int len = word.length() - 2;
        return "" + word.charAt(0) + len + word.charAt(word.length() -1);
    }
}


// Your ValidWordAbbr object will be instantiated and called as such:
// ValidWordAbbr vwa = new ValidWordAbbr(dictionary);
// vwa.isUnique("Word");
// vwa.isUnique("anotherWord");
