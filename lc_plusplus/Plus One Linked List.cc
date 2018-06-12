// Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.
//
// You may assume the integer do not contain any leading zero, except the number 0 itself.
//
// The digits are stored such that the most significant digit is at the head of the list.
//
// Example:
// Input:
// 1->2->3
//
// Output:
// 1->2->4


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
    ListNode* plusOne(ListNode* head) {
        auto h = reverse(head);
        auto curr = new ListNode(0);
        curr->next = h;
        int carry = 1;
        while (curr->next != nullptr) {
            curr = curr->next;
            auto sum = curr->val + carry;
            curr->val = sum % 10;
            carry = sum / 10;
        }
        if (carry == 1) curr->next = new ListNode(1);
        return reverse(h);
    }

    ListNode* reverse(ListNode* h) {
        auto d = new ListNode(0);
        d->next = h;
        while (h->next != nullptr) {
            auto n  = h->next;
            h->next = n->next;
            n->next = d->next;
            d->next = n;
        }
        return d->next;
    }

    ListNode* reverse1(ListNode* h) {
        if (h == nullptr || h->next == nullptr) return h;
        auto hh = reverse1(h->next);
        h->next->next = h;
        h->next = nullptr;
        return hh;
    }
};
