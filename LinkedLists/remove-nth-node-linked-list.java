/*
https://leetcode.com/problems/remove-nth-node-from-end-of-list/

Given a linked list, remove the n-th node from the end of list and return its head.
Example:
Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.

Follow up:
Could you do this in one pass?
*/

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pointer2 = dummy; // this pointer will be ahead.
        for (int i = 0; i <= n; i++) pointer2 = pointer2.next;
        ListNode pointer1 = dummy; // lagging pointer.
        while (pointer2 != null) {
            pointer2 = pointer2.next;
            pointer1 = pointer1.next;
        }
        pointer1.next = pointer1.next.next;
        return dummy.next;
    }
}
