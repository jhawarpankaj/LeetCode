/*
https://leetcode.com/problems/add-two-numbers-ii/

You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain 
a single digit. Add the two numbers and return it as a linked list.
You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:
Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

// Using two stacks this problem can be solved easily. 
// But try to solve it recursively, your expertise of writing a recursive and modular code will be tested.
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return new ListNode(0);
        else if (l1 == null && l2 != null) return l2;
        else if (l1 != null && l2 == null) return l1;
        else return solve(l1, l2);
    }
    
    ListNode solve(ListNode l1, ListNode l2) {
        int count1 = count(l1);
        int count2 = count(l2);
        if (count1 > count2) return helper(l1, count1 - count2, l2);
        else if (count2 > count1) return helper(l2, count2 - count1, l1);
        else return helper(l1, 0, l2);
    }
    
    int count(ListNode l) {
        int count = 0;
        while (l != null) {
            count++;
            l = l.next;
        }
        return count;
    }
    
    ListNode helper(ListNode l1, int count, ListNode l2) {
        Stack<Integer> stack = new Stack<>();
        while (count > 0) {
            stack.push(l1.val);
            count--;
            l1 = l1.next;
        }
        // Fixing the return type from any recursive function is very important. For now, just know that from the recursive method, we need to return a ListNode.
        ListNode result = solveForEqualSizes(l1, l2); 
        while (!stack.isEmpty()) {
            int carry = getCarryAndUpdateNode(result);
            int val = stack.pop() + carry;
            ListNode curr = new ListNode(val);
            curr.next = result;
            result = curr;
        }
        int carry = getCarryAndUpdateNode(result);
        if (carry == 1) {
            ListNode ans = new ListNode(1);
            ans.next = result;
            return ans;
        } else return result;
    }
    
    /* 
       1. Now while writing code for the recursive function, don't get confused if we should return carry or number or the node.
          Always think that what the caller of this function needs, it's a ListNode in this case, so now just think how you can do that.
          Infact, you can always return the type of value you want to from each node of a recursive tree. What will confuse us will be the
          scenarios when it is a null node (left null or right null or leaf node, in case of a Binary Tree). Just handle those cases and you should be good.
       2. Then while writing the logic (body) of this recursive code, assume that your child has returned you the ListNode and then write the main body.
       3. Also, if you need any values from the parent node, use the method arguments for that.
    */
    ListNode solveForEqualSizes(ListNode l1, ListNode l2) {
        if (l1.next == null) return new ListNode(l1.val + l2.val); // we do not want to deal with null node.
        else {
            ListNode curr = new ListNode(0);
            ListNode child = solveForEqualSizes(l1.next, l2.next);
            int carry = getCarryAndUpdateNode(child);
            curr.val += carry + l1.val + l2.val;
            curr.next = child;
            return curr;
        }
    }
    
    int getCarryAndUpdateNode(ListNode node) {
        int carry = node.val >= 10 ? 1 : 0;
        node.val = node.val >= 10 ? node.val % 10 : node.val;      
        return carry;
    }
}
