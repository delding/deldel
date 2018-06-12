// Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    ListNode* mergeKLists(vector<ListNode*>& lists) {
        auto greater = [](ListNode* l1, ListNode* l2) {return l1->val > l2->val;};
        priority_queue<ListNode*, vector<ListNode*>, decltype(greater)> minheap{greater};
        for (auto l : lists) {
            if (l != nullptr) minheap.push(l);
        }
        auto dum = new ListNode(0);
        auto h = dum;
        while (!minheap.empty()) {
            h->next = minheap.top();
            minheap.pop();
            h = h->next;
            if (h->next != nullptr) minheap.push(h->next);
        }
        return dum->next;
    }
};
