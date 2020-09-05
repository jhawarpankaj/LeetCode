/*
https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/

Given a string S of '(' and ')' parentheses, we add the minimum number of parentheses ( '(' or ')', and in any positions ) so that the resulting 
parentheses string is valid.

Formally, a parentheses string is valid if and only if:

It is the empty string, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
Given a parentheses string, return the minimum number of parentheses we must add to make the resulting string valid.

Example 1:
Input: "())"
Output: 1

Example 2:
Input: "((("
Output: 3

Example 3:
Input: "()"
Output: 0

Example 4:
Input: "()))(("
Output: 4

Note:
S.length <= 1000
S only consists of '(' and ')' characters.
*/

class Solution {
    public int minAddToMakeValid(String S) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (c == ')') {
                if (stack.isEmpty()) stack.push(c);
                else if (stack.peek() == '(') stack.pop();
                else stack.push(c);
            } else stack.push(c);
        }
        return stack.size();
    }
}

// Without using a stack.
class Solution {
    public int minAddToMakeValid(String S) {
        int balance = 0, count = 0;
        char open = '(', close = ')';
        for (char c : S.toCharArray()) {
            if (c == open) balance++;
            else {
                if (balance == 0) count++; // these are the closed brackets appearing before any open bracket.
                else balance--;
            }
        }
        // the balance now represents those open brackets which could not form a pair.
        return count + balance;
    }
}
