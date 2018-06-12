// Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.
//
// You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.
//
// Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
//
// For the last line of text, it should be left justified and no extra space is inserted between words.
//
// For example,
// words: ["This", "is", "an", "example", "of", "text", "justification."]
// L: 16.
//
// Return the formatted lines as:
// [
//    "This    is    an",
//    "example  of text",
//    "justification.  "
// ]
// Note: Each word is guaranteed not to exceed L in length.


class Solution {
public:
    vector<string> fullJustify(vector<string>& words, int maxWidth) {
        vector<string> res;
        for (size_t i = 0; i < words.size();) {
            auto len = -1;
            auto j = i;
            for (; j < words.size() && len + static_cast<int>(words[j].size()) + 1 <= maxWidth; ++j) {
                len += static_cast<int>(words[j].size()) + 1;
            }
            int space = 1, extra = 0;
            if (j != i + 1 && j != words.size()) {
                space = 1 + (maxWidth - len) / (j - i - 1);
                extra = (maxWidth - len) % (j - i - 1);
            }
            string s = words[i];
            for (size_t k = i + 1; k < j; ++k) {
                s += string(space + (extra-- > 0 ? 1 : 0), ' ') + words[k];
            }
            if (maxWidth - s.size() > 0) s += string(maxWidth - s.size(), ' ');
            res.push_back(s);
            i = j;
        }
        return res;
    }
};
