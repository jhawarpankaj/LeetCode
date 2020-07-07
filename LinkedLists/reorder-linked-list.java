/*
https://leetcode.com/problems/reorder-list/

Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You may not modify the values in the list's nodes, only nodes itself may be changed.

Example 1:
Given 1->2->3->4, reorder it to 1->4->2->3.

Example 2:
Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
*/

class Solution {
    public void reorderList(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode firstHalfEnd = getMiddle(dummy, dummy);
        firstHalfEnd.next = reverse(firstHalfEnd.next);
        ListNode prev = dummy, q = firstHalfEnd.next, p = head;
        while (q != null) {
            ListNode temp = p.next;
            p.next = q;
            prev.next = p;
            prev = q;
            p = temp;
            q = q.next;
        } 
        // In case the list has odd total no of elements.
        if (p == firstHalfEnd) {
            prev.next = p;
            p.next = null;
        }
        head = dummy.next;        
    }
    
    // template reverse code.
    ListNode reverse(ListNode head) {
        if (head == null) return null;
        ListNode p = null, q = head, r = head.next;
        while (r != null) {
            q.next = p;
            p = q;
            q = r;
            r = r.next;
        }
        q.next = p;
        return q;
    }
    
    // template 2-pointer approach.
    ListNode getMiddle(ListNode slow, ListNode fast) {
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
