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
        if (head == null) return null;
        // a previous pointer will be one step behind the curr node.
        // As the curr pointer will become null in the while loop iteration and the parent call needs the last node
        // and not null, we keeping this lagging previous pointer.
        Node prev = head; 
        Node curr = head;
        while (curr != null) {
            if (curr.child != null) {
                Node last = dfs(curr.child);
                Node temp = curr.next;
                curr.next = curr.child;
                curr.next.prev = curr;
                curr.child = null;
                last.next = temp;
                if (temp != null) temp.prev = last;
                curr = last;
            }
            prev = curr;
            curr = curr.next;
        }
        return prev;
    }
}
