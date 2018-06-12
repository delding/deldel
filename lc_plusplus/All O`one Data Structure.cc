// Implement a data structure supporting the following operations:
//
// Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1.
// Key is guaranteed to be a non-empty string.
// Dec(Key) - If Key's value is 1, remove it from the data structure.
// Otherwise decrements an existing key by 1. If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
// GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
// GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
// Challenge: Perform all these in O(1) time complexity.


class AllOne {
public:
    /** Initialize your data structure here. */
    AllOne() {

    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    void inc(string key) {
        if (ht.count(key) == 0) {
            ht[key] = buckets.insert(buckets.begin(), {0, {key}});
        }
        auto curr = ht[key];
        auto next = std::next(curr, 1);
        if (next == buckets.end() || next->val > curr->val + 1) {
            next = buckets.insert(next, {curr->val + 1, {}});
        }
        ht[key] = next;
        next->keys.insert(key);
        curr->keys.erase(key);  // all list/map/set iterators are not affected by insertion
        if (curr->keys.empty()) buckets.erase(curr);
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    void dec(string key) {
        if (ht.count(key) == 0) return;
        auto curr = ht[key];
        auto prev = std::prev(curr, 1);
        ht.erase(key);
        if (curr->val > 1) {
            if (curr == buckets.begin() || prev->val < curr->val - 1) {
                prev = buckets.insert(curr, {curr->val - 1, {}});
            }
            prev->keys.insert(key);
            ht[key] = prev;
        }
        curr->keys.erase(key);
        if (curr->keys.empty()) buckets.erase(curr);
    }

      /** Returns one of the keys with maximal value. */
    string getMaxKey() {
        return buckets.empty() ? "" : *(buckets.rbegin()->keys.begin());
    }

    /** Returns one of the keys with Minimal value. */
    string getMinKey() {
        return buckets.empty() ? "" : *(buckets.begin()->keys.begin());
    }

private:
    struct Bucket {
        int val;
        unordered_set<string> keys;
    };
    list<Bucket> buckets;
    unordered_map<string, list<Bucket>::iterator> ht;
};

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * string param_3 = obj.getMaxKey();
 * string param_4 = obj.getMinKey();
 */


 class AllOne {
 public:
     /** Initialize your data structure here. */
     AllOne() {

     }

     /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
     void inc(string key) {
         if (pos.count(key) == 0) {
             buckets.push_front(Bucket(0, {key}));
             pos[key] = buckets.begin();
         }
         auto it = pos[key];
         it->keys.erase(key);
         auto ne = next(it, 1);
         if (ne == buckets.end() || ne->val != it->val + 1) {
             pos[key] = buckets.insert(ne, Bucket(it->val + 1, {key}));
         } else {
             pos[key] = ne;
             ne->keys.insert(key);
         }
         if (it->keys.empty()) buckets.erase(it);
     }

     /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
     void dec(string key) {
         if (pos.count(key) == 0) return;
         auto it = pos[key];
         it->keys.erase(key);
         pos.erase(key);
         if (it->val > 1) {
             auto pre = prev(it, 1);
             if (it == buckets.begin() || pre->val != it->val - 1) {
                 pos[key] = buckets.insert(it, Bucket(it->val - 1, {key}));
             } else {
                 pre->keys.insert(key);
                 pos[key] = pre;
             }
         }
         if (it->keys.empty()) buckets.erase(it);
     }

     /** Returns one of the keys with maximal value. */
     string getMaxKey() {
         return buckets.empty() ? "" : *(buckets.back().keys.begin());
     }

     /** Returns one of the keys with Minimal value. */
     string getMinKey() {
         return buckets.empty() ? "" : *(buckets.front().keys.begin());
     }

 private:
     struct Bucket {
         int val = 0;
         unordered_set<string> keys;
         Bucket(int v, initializer_list<string> il) : val{v}, keys{move(il)} {}
     };
     list<Bucket> buckets;
     unordered_map<string, list<Bucket>::iterator> pos;
 };

 /**
  * Your AllOne object will be instantiated and called as such:
  * AllOne obj = new AllOne();
  * obj.inc(key);
  * obj.dec(key);
  * string param_3 = obj.getMaxKey();
  * string param_4 = obj.getMinKey();
  */
