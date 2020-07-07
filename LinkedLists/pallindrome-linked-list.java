/*
https://leetcode.com/problems/palindrome-linked-list/

Given a singly linked list, determine if it is a palindrome.

Example 1:

Input: 1->2
Output: false
Example 2:

Input: 1->2->2->1
Output: true
*/

// Note that there is no way to iterate a linked list from backwards, only when recursion unfolds, we get the child nodes value.
// Only option left is to reverse linked list and validate, but we save some effort by not reversing the entire linked list.

class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);
        boolean result = checkPallindrome(head, secondHalfStart);
        firstHalfEnd.next = reverseList(secondHalfStart); // revert the changes done to original linked list.
        return result;
    }
    
    boolean checkPallindrome(ListNode p, ListNode q) {
        while (p != null && q != null) {
            if (p.val != q.val) return false;
            p = p.next;
            q = q.next;
        }
        return true;
    }
    
    ListNode endOfFirstHalf(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    
    ListNode reverseList(ListNode head) {
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
