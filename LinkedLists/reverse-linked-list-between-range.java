/*
https://leetcode.com/problems/reverse-linked-list-ii/

Reverse a linked list from position m to n. Do it in one-pass.
Note: 1 ≤ m ≤ n ≤ length of list.

Example:
Input: 1->2->3->4->5->NULL, m = 2, n = 4
Output: 1->4->3->2->5->NULL
*/

class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int count = 1;
        ListNode slow = dummy, fast = head, prev = null, next = null;
        while (head != null) {
            if (count == m) prev = slow;
            if (count == n) {
                next = head.next;
                head.next = null;
                ListNode temp = prev.next;
                prev.next = reverse(temp);
                temp.next = next;
                break;
            }
            slow = head;
            head = head.next;
            count++;
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
