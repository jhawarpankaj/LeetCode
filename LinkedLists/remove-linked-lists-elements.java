/*
https://leetcode.com/problems/remove-linked-list-elements/

Remove all elements from a linked list of integers that have value val.
Example:
Input:  1->2->6->3->4->5->6, val = 6
Output: 1->2->3->4->5
*/

// Two pointer approach, a lagging pointer and an advanced pointer.
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p = dummy, q = dummy.next;
        while (q != null) {
            if (q.val == val) p.next = q.next;
            else p = p.next;
            q = q.next;
        }
        return dummy.next;
    }
}

