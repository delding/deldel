// A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).
//
//  Buildings  Skyline Contour
// The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
//
// For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
//
// The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.
//
// For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
//
// Notes:
//
// The number of buildings in any input list is guaranteed to be in the range [0, 10000].
// The input list is already sorted in ascending order by the left x position Li.
// The output list must be sorted by the x position.
// There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]


class Solution {
    struct Point {
        int x;
        int h;
        bool left;
        Point(int x_, int h_, bool l) : x{x_}, h{h_}, left{l} {}
    };
public:
    vector<pair<int, int>> getSkyline(vector<vector<int>>& buildings) {
        vector<Point> ps;
        for (auto& b : buildings) {
            ps.emplace_back(b[0], b[2], true);
            ps.emplace_back(b[1], b[2], false);
        }
        sort(ps.begin(), ps.end(), [](Point& p1, Point& p2){
            if (p1.x == p2.x) {
                if (p1.left && p2.left) return p1.h > p2.h;
                if (!p1.left && !p2.left) return p1.h < p2.h;
                return p1.left;
            }
            return p1.x < p2.x;
        });
        int H = 0;
        multiset<int> Hs{H};
        vector<pair<int, int>> ret;
        for (auto& p : ps) {
            if (p.left) {
                Hs.insert(p.h);
                if (p.h > H) {
                    H = p.h;
                    ret.emplace_back(p.x, H);
                }
            } else {
                Hs.erase(Hs.find(p.h));
                if (Hs.count(H) == 0) {
                    H = *Hs.rbegin();
                    ret.emplace_back(p.x, H);
                }
            }
        }
        return ret;
    }
};


class Solution {
public:
    vector<pair<int, int>> getSkyline(vector<vector<int>>& buildings) {
        vector<tuple<int, int, bool>> pts;  // x, y, isLeft
        for (auto& b : buildings) {
            pts.emplace_back(b[0], b[2], true);
            pts.emplace_back(b[1], b[2], false);
        }
        sort(pts.begin(), pts.end(), [](tuple<int, int, bool>& p1, tuple<int, int, bool>& p2){
            if (get<0>(p1) != get<0>(p2)) return get<0>(p1) < get<0>(p2);
            else if (get<2>(p1) && get<2>(p2)) return get<1>(p1) > get<1>(p2);  // both left higher first
            else if (!get<2>(p1) && !get<2>(p2)) return get<1>(p1) < get<1>(p2);  // both right lower first
            else return get<2>(p1);  // left first
        });
        vector<pair<int, int>> skylines;
        multiset<int> heights;
        int h{0};
        heights.insert(h);
        for (auto& p : pts) {
            if (get<2>(p)) {
                heights.insert(get<1>(p));
                if (get<1>(p) > h) {
                    h = get<1>(p);
                    skylines.emplace_back(get<0>(p), h);
                }
            } else {
                heights.erase(heights.find(get<1>(p)));
                if (heights.find(get<1>(p)) == heights.end() && get<1>(p) == h) {
                    h = *heights.rbegin();
                    skylines.emplace_back(get<0>(p), h);
                }
            }
        }
        return skylines;
    }
};
