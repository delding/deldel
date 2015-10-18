/**
 * Compare two version numbers version1 and version2.
If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the . character.
The . character does not represent a decimal point and is used to separate number sequences.
For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

Here is an example of version numbers ordering:

0.1 < 1.1 < 1.2 < 13.37
* */

public class Solution {
    public int compareVersion(String version1, String version2) {
        while(version1.indexOf(".") != -1 && version2.indexOf(".") != -1) {
            int v1 = Integer.parseInt(version1.substring(0, version1.indexOf(".")));
            version1 = version1.substring(version1.indexOf(".") + 1);
            int v2 = Integer.parseInt(version2.substring(0, version2.indexOf(".")));
            version2 = version2.substring(version2.indexOf(".") + 1);
            if (v1 > v2) return 1;
            if (v1 < v2) return -1;
        }
        if (version1.indexOf(".") == -1) {
            int v1 = Integer.parseInt(version1);
            if (version2.indexOf(".") == -1) {
                int v2 = Integer.parseInt(version2);
                if (v1 > v2) return 1;
                else if (v1 < v2) return -1;
                else return 0;
            } else {
                int v2 = Integer.parseInt(version2.substring(0, version2.indexOf("."))); 
                version2 = version2.substring(version2.indexOf(".") + 1);
                if (v1 > v2) return 1;
                else if (v1 < v2) return -1;
                else {
                    for (char c : version2.toCharArray()) {
                        if (c == '0' || c == '.') continue; // ERROR: could be 1 = 1.0
                        else return -1;
                    }    
                    return 0; // .0.0.0
                }
            }
        } else {
            return -1 * compareVersion(version2, version1);
        }
    }
}
