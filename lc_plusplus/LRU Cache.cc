// Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
//
// get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
// put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
//
// Follow up:
// Could you do both operations in O(1) time complexity?
//
// Example:
//
// LRUCache cache = new LRUCache( 2 /* capacity */ );
//
// cache.put(1, 1);
// cache.put(2, 2);
// cache.get(1);       // returns 1
// cache.put(3, 3);    // evicts key 2
// cache.get(2);       // returns -1 (not found)
// cache.put(4, 4);    // evicts key 1
// cache.get(1);       // returns -1 (not found)
// cache.get(3);       // returns 3
// cache.get(4);       // returns 4


class LRUCache {
public:
    LRUCache(int capacity) : cap{capacity} {

    }

    int get(int key) {
        if (m.count(key) == 0) return -1;
        auto& p = m[key];
        l.erase(p.second);
        l.push_front(key);
        p.second = l.begin();  // p needs to be a ref
        return p.first;
    }

    void put(int key, int value) {
        if (get(key) != -1) m[key].first = value;
        else {
            if (l.size() == cap) {
                m.erase(l.back());
                l.pop_back();
            }
            l.push_front(key);
            m[key] = {value, l.begin()};
        }
    }

private:
    unordered_map<int, pair<int, list<int>::iterator>> m;
    list<int> l;
    int cap;
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
