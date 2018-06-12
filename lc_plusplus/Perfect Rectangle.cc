// Given N axis-aligned rectangles where N > 0, determine if they all together
// form an exact cover of a rectangular region.
//
// Each rectangle is represented as a bottom-left point and a top-right point.
// For example, a unit square is represented as [1,1,2,2]. (coordinate of
// bottom-left point is (1, 1) and top-right point is (2, 2)).
//
//
// Example 1:
//
// rectangles = [
//   [1,1,3,3],
//   [3,1,4,2],
//   [3,2,4,4],
//   [1,3,2,4],
//   [2,3,3,4]
// ]
//
// Return true. All 5 rectangles together form an exact cover of a rectangular region.
//
// Example 2:
//
// rectangles = [
//   [1,1,2,3],
//   [1,3,2,4],
//   [3,1,4,2],
//   [3,2,4,4]
// ]
//
// Return false. Because there is a gap between the two rectangular regions.
//
// Example 3:
//
// rectangles = [
//   [1,1,3,3],
//   [3,1,4,2],
//   [1,3,2,4],
//   [3,2,4,4]
// ]
//
// Return false. Because there is a gap in the top center.
//
// Example 4:
//
// rectangles = [
//   [1,1,3,3],
//   [3,1,4,2],
//   [1,3,2,4],
//   [2,2,4,4]
// ]
//
// Return false. Because two of the rectangles overlap with each other.


// the large rectangle area should be equal to the sum of small rectangles
// count of all the points should be even, and that of all the four corner points should be one
// the large rectangle area should be equal to the sum of small rectangles
// count of all the points should be even, and that of all the four corner points should be one
class Solution {
public:
    bool isRectangleCover(vector<vector<int>>& rectangles) {
        auto hasher = [](const pair<int, int>& corner){return hash<int>{}(corner.first) ^ hash<int>{}(corner.second);};
        auto equal = [](const pair<int, int>& k1, const pair<int, int>& k2){return k1.first == k2.first && k1.second == k2.second;};
        unordered_set<pair<int, int>, decltype(hasher), decltype(equal)> corners{rectangles.size(), hasher, equal};
        int left{INT_MAX}, right{INT_MIN}, top{INT_MIN}, bot{INT_MAX};
        int area{0};
        for (auto& rect : rectangles) {
            left = min(left, rect[0]);
            bot = min(bot, rect[1]);
            right = max(right, rect[2]);
            top = max(top, rect[3]);
            for (auto& c : vector<pair<int, int>>{{rect[0], rect[1]}, {rect[0], rect[3]}, {rect[2], rect[1]}, {rect[2], rect[3]}}) {
                if (corners.count(c) == 1) corners.erase(c);
                else corners.insert(c);
            }
            area += (rect[3] - rect[1]) * (rect[2] - rect[0]);
        }
        if (corners.count(make_pair(left, top)) != 1 || corners.count(make_pair(left, bot)) != 1 || corners.count(make_pair(right, top)) != 1 || corners.count(make_pair(right, bot)) != 1 || corners.size() != 4) {
            return false;
        }
        return area == (top - bot) * (right - left);
    }
};


// the large rectangle area should be equal to the sum of small rectangles
// count of all the points should be even, and that of all the four corner points should be one
public class Solution {
    public boolean isRectangleCover(int[][] rectangles) {
        if (rectangles.length == 0 || rectangles[0].length == 0) return false;

    int x1 = Integer.MAX_VALUE;
    int x2 = Integer.MIN_VALUE;
    int y1 = Integer.MAX_VALUE;
    int y2 = Integer.MIN_VALUE;

    HashSet<String> set = new HashSet<String>();
    int area = 0;

    for (int[] rect : rectangles) {
        x1 = Math.min(rect[0], x1);
        y1 = Math.min(rect[1], y1);
        x2 = Math.max(rect[2], x2);
        y2 = Math.max(rect[3], y2);

        area += (rect[2] - rect[0]) * (rect[3] - rect[1]);

        String s1 = rect[0] + " " + rect[1];
        String s2 = rect[0] + " " + rect[3];
        String s3 = rect[2] + " " + rect[3];
        String s4 = rect[2] + " " + rect[1];

        if (set.contains(s1)) {
            set.remove(s1);
        } else {
            set.add(s1);
        }
        if (set.contains(s2)) {
            set.remove(s2);
        } else {
            set.add(s2);
        }
        if (set.contains(s3)) {
            set.remove(s3);
        } else {
            set.add(s3);
        }
        if (set.contains(s4)) {
            set.remove(s4);
        } else {
            set.add(s4);
        }
    }

    if (!set.contains(x1 + " " + y1) || !set.contains(x1 + " " + y2) || !set.contains(x2 + " " + y1) || !set.contains(x2 + " " + y2) || set.size() != 4) return false;

    return area == (x2-x1) * (y2-y1);
    }
}
