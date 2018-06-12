// Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.
//
// get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
// put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.
//
// Follow up:
// Could you do both operations in O(1) time complexity?
//
// Example:
//
// LFUCache cache = new LFUCache( 2 /* capacity */ );
//
// cache.put(1, 1);
// cache.put(2, 2);
// cache.get(1);       // returns 1
// cache.put(3, 3);    // evicts key 2
// cache.get(2);       // returns -1 (not found)
// cache.get(3);       // returns 3.
// cache.put(4, 4);    // evicts key 1.
// cache.get(1);       // returns -1 (not found)
// cache.get(3);       // returns 3
// cache.get(4);       // returns 4


class LFUCache {
    unordered_map<int, pair<int, int>> kv;  // key to value_freq pair
    unordered_map<int, list<int>> fk;  // freq to key list
    unordered_map<int, list<int>::iterator> ki; // key to iterator
    int minFreq;
    int cap;
public:
    LFUCache(int capacity) : cap{capacity}, minFreq{0} {

    }

    int get(int key) {
        if (kv.count(key) == 0) return -1;
        auto freq = kv[key].second++;
        fk[freq++].erase(ki[key]);
        if (fk[minFreq].size() == 0) ++minFreq;
        fk[freq].push_back(key);
        ki[key] = --fk[freq].end();
        return kv[key].first;
    }

    void put(int key, int value) {
        if (cap == 0) return;
        if (get(key) != -1) {
            kv[key].first = value;
            return;
        }
        if (kv.size() == cap) {
            kv.erase(fk[minFreq].front());
            ki.erase(fk[minFreq].front());
            fk[minFreq].pop_front();
        }
        kv[key] = {value, 1};
        minFreq = 1;
        fk[1].push_back(key);
        ki[key] = --fk[1].end();
    }
};

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
