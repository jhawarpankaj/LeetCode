/*
https://leetcode.com/problems/reverse-linked-list/

Reverse a singly linked list.

Example:

Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL
Follow up:

A linked list can be reversed either iteratively or recursively. Could you implement both?
*/

// Recursive.
// Try to observe that a Linked List is nothing but a directed graph and with a guarantee that it will have a single path always.
// So both DFS and BFS will perform the same operation in case of a linked list.

class Solution {
    ListNode newHead;
    public ListNode reverseList(ListNode head) {
        newHead = null;
        helper(null, head);
        return newHead;
    }
    
    void helper(ListNode parent, ListNode curr) {
        if (curr == null) {
            newHead = parent;
            return;
        }
        helper(curr, curr.next);
        curr.next = parent;
    }
}

// Iterative.
// It's a classic problem and needs 3 pointers. Update each one of them on every iteration.

class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode p = null, q = head, r = head.next; // each one of them one step ahead of each other.
        while (r != null) {
            q.next = p;
            p = q;
            q = r;
            r = r.next; // only r goes ahead, so that becomes the terminating condition of while loop (it should not be null).
        }
        q.next = p; // The while loop terminated without reversing this link.
        return q; // New head of the reversed linked list.
    }
}
