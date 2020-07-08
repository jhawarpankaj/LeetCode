/*
https://leetcode.com/problems/reverse-nodes-in-k-group/

Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should 
remain as it is.

Example:
Given this linked list: 1->2->3->4->5
For k = 2, you should return: 2->1->4->3->5
For k = 3, you should return: 3->2->1->4->5
Note:
Only constant extra memory is allowed.
You may not alter the values in the list's nodes, only nodes itself may be changed.
*/

class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        while (head != null) {
            int count = 1;
            ListNode p = head;
            ListNode slow = p;
            while (p != null && count <= k) {
                slow = p;
                p = p.next;                
                count++;
            }
            if (count == k + 1) {
                slow.next = null; // make the next pointer of the last element of the linked list to be reversed = null.
                prev.next = reverse(head); 
                head.next = p; // head is the last element after reverse.
                prev = head; // for successive reverse.
            }
            head = p;
        }
        return dummy.next;
    }
    
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
}
