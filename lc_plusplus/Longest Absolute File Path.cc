// Suppose we abstract our file system by a string in the following manner:
//
// The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:
//
// dir
//     subdir1
//     subdir2
//         file.ext
// The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.
//
// The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:
//
// dir
//     subdir1
//         file1.ext
//         subsubdir1
//     subdir2
//         subsubdir2
//             file2.ext
// The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and an empty second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.
//
// We are interested in finding the longest (number of characters) absolute path to a file within our file system. For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its length is 32 (not including the double quotes).
//
// Given a string representing the file system in the above format, return the length of the longest absolute path to file in the abstracted file system. If there is no file in the system, return 0.
//
// Note:
// The name of a file contains at least a . and an extension.
// The name of a directory or sub-directory will not contain a ..
// Time complexity required: O(n) where n is the size of the input string.
//
// Notice that a/aa/aaa/file1.txt is not the longest file path, if there is another path aaaaaaaaaaaaaaaaaaaaa/sth.png


class Solution {
public:
    int lengthLongestPath(string input) {
        stack<int> st;
        int longest = 0;
        for (int i = 0; i < input.size();) {
            int dep{0};
            for (;i < input.size() && input[i] == '\t'; ++i) dep++;
            auto j = input.find("\n", i);
            if (j == string::npos) j = input.size();
            while (st.size() > dep) st.pop();
            auto f = input.substr(i, j - i);
            int len = j - i + (st.empty() ? 0 : st.top());
            if (f.find(".") == string::npos) st.push(len);
            else longest = max(longest, len + dep);
            i = j + 1;
        }
        return longest;
    }
};
