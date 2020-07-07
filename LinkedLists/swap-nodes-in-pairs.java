/*
https://leetcode.com/problems/swap-nodes-in-pairs/
Given a linked list, swap every two adjacent nodes and return its head.
You may not modify the values in the list's nodes, only nodes itself may be changed.

Example:
Given 1->2->3->4, you should return the list as 2->1->4->3.
*/

class Solution {
    public ListNode swapPairs(ListNode head) {
        // the sentinel node helps to omit the extra care need to be taken after the original head.
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy, p = head;
        // In the list: dummy->1->2->3->4
        while (p != null && p.next != null) {
            ListNode temp = p.next.next;  // points to 3.
            p.next.next = p; // Reverse link 2 -> 1.
            prev.next = p.next; // dummy -> 2.
            p.next = temp; // Make link, 1 -> 3, needed in case only one node left. the loop terminates otherwise.
            prev = p; // prev come to 1, for update in the next iteration.
            p = temp; // p moves to 3 for next iteration.
        }
        return dummy.next;
    }
}

