// Implement an iterator to flatten a 2d vector.
//
// For example,
// Given 2d vector =
//
// [
//   [1,2],
//   [3],
//   [4,5,6]
// ]
// By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].
//


class Vector2D {
    vector<vector<int>>::iterator x, e;
    vector<int>::iterator y;
public:
    Vector2D(vector<vector<int>>& vec2d) : x{vec2d.begin()}, e{vec2d.end()} {
        if (x != e) {
            y = x->begin();
            while (y == x->end()) {
                if (++x == e) break;
                y = x->begin();
            }
        }
    }

    int next() {
        auto pre = *y++;
        while (y == x->end()) {
            if (++x == e) break;
            y = x->begin();
        }
        return pre;
    }

    bool hasNext() {
        return x != e;
    }
};

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i(vec2d);
 * while (i.hasNext()) cout << i.next();
 */
