// An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:
//
// a) it                      --> it    (no abbreviation)
//
//      1
// b) d|o|g                   --> d1g
//
//               1    1  1
//      1---5----0----5--8
// c) i|nternationalizatio|n  --> i18n
//
//               1
//      1---5----0
// d) l|ocalizatio|n          --> l10n
// Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.
//
// Example:
// Given dictionary = [ "deer", "door", "cake", "card" ]
//
// isUnique("dear") -> false
// isUnique("cart") -> true
// isUnique("cane") -> false
// isUnique("make") -> true


class ValidWordAbbr {
    unordered_map<string, unordered_set<string>> abbrToWords;
public:
    ValidWordAbbr(vector<string> dictionary) {
        for (auto& w : dictionary) {
          abbrToWords[abbr(w)].insert(w);
        }
    }

    bool isUnique(string word) {
        auto abbr = this->abbr(word);
        return (abbrToWords.count(abbr) == 0 || (abbrToWords[abbr].size() == 1 && abbrToWords[abbr].count(word) == 1));
    }

    string abbr(string w) {
      string abbr;
      if (w.size() < 3) abbr = w;
      else {
        abbr += w[0];
        abbr += to_string(w.size() - 2);
        abbr += w[w.size() - 1];
      }
      return abbr;
    }
};

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr obj = new ValidWordAbbr(dictionary);
 * bool param_1 = obj.isUnique(word);
 */
