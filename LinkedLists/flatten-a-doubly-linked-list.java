/*
https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/

You are given a doubly linked list which in addition to the next and previous pointers, it could have a child pointer, 
which may or may not point to a separate doubly linked list. 
These child lists may have one or more children of their own, and so on, to produce a multilevel data structure.

*/

class Solution {
    public Node flatten(Node head) {
        if (head == null) return null;
        dfs(head);
        return head;
    }
    
    Node dfs(Node head) {
        // a previous pointer will be one step behind the curr node.
        // As the curr pointer will become null in the while loop iteration and the parent call needs the last node
        // and not null, we keeping this lagging previous pointer.
        Node prev = head, curr = head;
        while (curr != null) {
            if (curr.child == null) {
                prev = curr;
                curr = curr.next;
            } else {
                Node tail = dfs(curr.child);
                changePointers(curr, curr.next, curr.child, tail);
                prev = tail;
                curr = tail.next;
            }
        }
        return prev;
    }
    
    void changePointers(Node pHead, Node pNext, Node cHead, Node cNext) {
        pHead.next = cHead;
        cHead.prev = pHead;
        cNext.next = pNext;
        if (pNext != null) pNext.prev = cNext;
        pHead.child = null;
    }
}
