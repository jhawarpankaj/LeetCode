/*
https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/

Given a string s of '(' , ')' and lowercase English characters. 

Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid 
and return any valid string.

Formally, a parentheses string is valid if and only if:

It is the empty string, contains only lowercase characters, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
 
Example 1:
Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.

Example 2:
Input: s = "a)b(c)d"
Output: "ab(c)d"

Example 3:
Input: s = "))(("
Output: ""
Explanation: An empty string is also valid.

Example 4:
Input: s = "(a(b(c)d)"
Output: "a(b(c)d)"

Constraints:

1 <= s.length <= 10^5
s[i] is one of  '(' , ')' and lowercase English letters.
*/

class Solution {
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')') {
                if (stack.isEmpty()) stack.push(i);
                else if (s.charAt(stack.peek()) == '(') stack.pop();
                else stack.push(i);
            } else if (c == '(') stack.push(i);
        }
        StringBuilder sb = new StringBuilder(s);
        while (!stack.isEmpty()) {
            sb.deleteCharAt(stack.pop());
        }
        return sb.toString();
    }
}

/*
Note that we can avoid using stack.
An important thing to understand related to valid parenthesis:

The parentheses in a string are balanced if and only if these 2 conditions are met:

There are the same number of "(" and ")" in the string.
Scanning through the string from left to right and counting how many "(" and ")" there are so far, there should never be a time where there are more ")" than "(". 
We call count("(") - count(")") the balance of the string..

Here's a simple pseudocode algorithm that checks for these conditions by scanning through the string and incrementing balance each time it 
sees a "(", and decrementing each time it sees ")". If at any point the balance is negative, which can only happen if we've seen more ")" 
than "(", then it returns false. If we get to the end, then it returns true only if the balance is 0, which means we've seen the same number of "(" as ")".

function is_balanced_parentheses(string)
    balance = 0
    for each char in the string
        if char == "("
            balance = balance + 1
        if char == ")"
            balance = balance - 1
        if balance < 0
            return false
    return balance == 0
*/

class Solution {
    public String minRemoveToMakeValid(String s) {
        int balance = 0;
        StringBuilder sb = new StringBuilder();
        char open = '(';
        char close = ')';
        for (char c : s.toCharArray()) {
            if (c == open) balance++;
            else if (c == close) {
                if (balance == 0) continue;
                balance--;
            }
            sb.append(c);
        }
        /*
        Note that below code also works fine but the complexity will be O(n^2) because of the deleteCharAt() method.
        for (int i = sb.length() - 1; i >= 0 && balance > 0; i--) {
            char c = sb.charAt(i);
            if (c == open) {
                sb.deleteCharAt(i);
                balance--;
            }
        }
        */
        StringBuilder res = new StringBuilder();
        for (int i = sb.length() - 1; i >= 0; i--) {
            char c = sb.charAt(i);
            if (c == open && balance > 0) balance--;
            else res.append(c);
        }
        
        return res.reverse().toString();
    }
}
