// A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
//
// Return a deep copy of the list.


/**
 * Definition for singly-linked list with a random pointer.
 * struct RandomListNode {
 *     int label;
 *     RandomListNode *next, *random;
 *     RandomListNode(int x) : label(x), next(NULL), random(NULL) {}
 * };
 */
class Solution {
public:
    RandomListNode *copyRandomList(RandomListNode *head) {
        unordered_map<RandomListNode*, RandomListNode*> memo;
        auto dum = new RandomListNode(0);
        auto h = dum;
        while (head != nullptr) {
            if (memo.count(head) == 0) memo[head] = new RandomListNode(head->label);
            dum->next = memo[head];
            dum = dum->next;
            if (head->random != nullptr) {
                if (memo.count(head->random) == 0) memo[head->random] = new RandomListNode(head->random->label);
                dum->random = memo[head->random];
            }
            head = head->next;
        }
        return h->next;
    }
};
