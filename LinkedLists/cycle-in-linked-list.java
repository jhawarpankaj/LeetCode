/*
https://leetcode.com/problems/linked-list-cycle-ii/

Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
Note: Do not modify the linked list.
*/

public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        boolean cycle = false;
        // slow moves one step and fast moves 2 steps.
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return getNode(head, slow);
        }
        return null; // cycle not found.
    }
    
    // To find the entrance to the cycle, we have two pointers traverse at
    // the same speed -- one from the front of the list, and the other from
    // the point of intersection.
    ListNode getNode(ListNode head, ListNode slow) {
        while (head != slow) {
            head = head.next;
            slow = slow.next;
        }
        return head;
    }
}
