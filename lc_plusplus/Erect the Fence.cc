// There are some trees, where each tree is represented by (x,y) coordinate in a two-dimensional garden. Your job is to fence the entire garden using the minimum length of rope as it is expensive. The garden is well fenced only if all the trees are enclosed. Your task is to help find the coordinates of trees which are exactly located on the fence perimeter.
//
// Example 1:
// Input: [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
// Output: [[1,1],[2,0],[4,2],[3,3],[2,4]]
// Explanation:
//
// Example 2:
// Input: [[1,2],[2,2],[4,2]]
// Output: [[1,2],[2,2],[4,2]]
// Explanation:
//
// Even you only have trees in a line, you need to use rope to enclose them.


/**
 * Definition for a point.
 * struct Point {
 *     int x;
 *     int y;
 *     Point() : x(0), y(0) {}
 *     Point(int a, int b) : x(a), y(b) {}
 * };
 */
class Solution {
public:
    vector<Point> outerTrees(vector<Point>& points) {
        if (points.size() <= 3) return points;
        auto minIt = min_element(points.begin(), points.end(), [](Point& p1, Point& p2){return p1.y < p2.y || (p1.y == p2.y && p1.x < p2.x);});
        swap(*points.begin(), *minIt);
        auto refPoint = *points.begin();
        // [[0,0],[0,1],[0,2],[1,2],[2,2],[3,2],[3,1],[3,0],[2,0],[1,0],[1,1],[3,3]] becomes
        // 0:0 1:0 2:0 3:0 3:1 3:2 1:1 2:2 3:3 1:2 0:2 0:1 so that [2,2] remove [1,1], [3,3] remove [2,2], and [0,2] won't remove [0,1]
        // making the right convex hull [[0,0],[1,0],[2,0],[3,0],[3,1],[3,2],[3,3],[0,2],[0,1]]
        sort(points.begin() + 1, points.end(), [=](Point p1, Point p2){return this->crossProduct(refPoint, p1, p2) > 0 || (this->crossProduct(refPoint, p1, p2) == 0 && this->dist(refPoint, p1) < this->dist(refPoint, p2));});
        auto it = points.end() - 2;
        while (crossProduct(refPoint, points.back(), *it) == 0) --it;
        sort(++it, points.end(), [=](Point p1, Point p2){return this->dist(p1, refPoint) > this->dist(p2, refPoint);});

        vector<Point> hull{points[0], points[1]};
        for (auto i = 2; i < points.size(); ++i) {
            while (hull.size() > 1) {
                auto first = hull[hull.size() - 2];
                auto second = hull.back();
                if (this->crossProduct(first, second, points[i]) < 0) hull.pop_back();  // keep colinear points
                else break;
            }
            hull.push_back(points[i]);
        }
        return hull;
    }


    // cross product of the two vectors P1P2 and P1P3 in 3d space, which is given by the expression
    // If the result is 0, the points are collinear; if it is positive, the three points constitute a "left turn" or counter-clockwise orientation, otherwise a "right turn" or clockwise orientation
    // [[  i,     j,   k],
    //  [x2-x1, y2-y1, 0],
    //  [x3-x1, y3-y1, 0]]
    int crossProduct(Point p1, Point p2, Point p3) {
        return (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x);
    }

    int dist(Point p1, Point p2) {
        return (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y);
    }
};
