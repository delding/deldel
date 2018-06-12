// Given a nested list of integers, implement an iterator to flatten it.
//
// Each element is either an integer, or a list -- whose elements may also be integers or other lists.
//
// Example 1:
// Given the list [[1,1],2,[1,1]],
//
// By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].
//
// Example 2:
// Given the list [1,[4,[6]]],
//
// By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].


/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * class NestedInteger {
 *   public:
 *     // Return true if this NestedInteger holds a single integer, rather than a nested list.
 *     bool isInteger() const;
 *
 *     // Return the single integer that this NestedInteger holds, if it holds a single integer
 *     // The result is undefined if this NestedInteger holds a nested list
 *     int getInteger() const;
 *
 *     // Return the nested list that this NestedInteger holds, if it holds a nested list
 *     // The result is undefined if this NestedInteger holds a single integer
 *     const vector<NestedInteger> &getList() const;
 * };
 */
class NestedIterator {
    using iter_type = vector<NestedInteger>::iterator;
    iter_type nxt;
    iter_type end;
    stack<pair<iter_type, iter_type>> st;
public:
    NestedIterator(vector<NestedInteger> &nestedList) : end{nestedList.end()} {
        st.emplace(nestedList.begin(), nestedList.end());
        advance();
    }

    void advance() {
        nxt = end;
        while (!st.empty() && nxt == end) {
            auto it = st.top().first++;
            if (it == st.top().second) {
                st.pop();
            } else {
                if (it->isInteger()) {
                    nxt = it;
                } else {
                    auto& list = it->getList(); // list must be auto& not a copy!
                    st.emplace(list.begin(), list.end());
                }
            }
        }
    }

    int next() {
        assert(hasNext());
        auto ret = nxt->getInteger();
        advance();
        return ret;
    }

    bool hasNext() {
        return nxt != end;
    }
};


class NestedIterator2 {
    using it_t = vector<NestedInteger>::iterator;
    stack<pair<it_t, it_t>> st;
    it_t ne;
    it_t end;
public:
    NestedIterator(vector<NestedInteger> &nestedList) : st{}, ne{nestedList.end()}, end{nestedList.end()} {
        st.emplace(nestedList.begin(), nestedList.end());
        forward();
    }

    void forward() {
        ne = end;
        while (!st.empty() && ne == end) {
            auto& itpair = st.top(); // must be auto&
            auto cur = itpair.first++, e = itpair.second;
            if (cur == e) st.pop();
            else {
                if (cur->isInteger()) ne = cur;
                else {
                    auto& nlist = cur->getList();
                    st.emplace(nlist.begin(), nlist.end());
                }
            }
        }
    }

    int next() {
        auto val = ne->getInteger();
        forward();
        return val;
    }

    bool hasNext() {
        return ne != end;
    }
};

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i(nestedList);
 * while (i.hasNext()) cout << i.next();
 */
