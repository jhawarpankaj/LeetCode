/*
https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/

Given a string S of lowercase letters, a duplicate removal consists of choosing two adjacent and equal letters, and removing them.
We repeatedly make duplicate removals on S until we no longer can.
Return the final string after all such duplicate removals have been made.  It is guaranteed the answer is unique.

Example 1:
Input: "abbaca"
Output: "ca"
Explanation: 
For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  
The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
 
Note:
1 <= S.length <= 20000
S consists only of English lowercase letters.
*/

// Here the stringbuilder acts as stack.
class Solution {
    public String removeDuplicates(String S) {
        StringBuilder sb = new StringBuilder();
        for (char c : S.toCharArray()) {
            int last = sb.length() - 1;
            if (last >= 0 && c == sb.charAt(last)) sb.deleteCharAt(last);
            else sb.append(c);
        }
        return sb.toString();
    }
}

/*
https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/

Given a string s, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them causing the left and the right side of 
the deleted substring to concatenate together.
We repeatedly make k duplicate removals on s until we no longer can.
Return the final string after all such duplicate removals have been made.
It is guaranteed that the answer is unique.

Example 1:
Input: s = "abcd", k = 2
Output: "abcd"
Explanation: There's nothing to delete.

Example 2:
Input: s = "deeedbbcccbdaa", k = 3
Output: "aa"
Explanation: 
First delete "eee" and "ccc", get "ddbbbdaa"
Then delete "bbb", get "dddaa"
Finally delete "ddd", get "aa"

Example 3:
Input: s = "pbbcggttciiippooaais", k = 2
Output: "ps"

Constraints:
1 <= s.length <= 10^5
2 <= k <= 10^4
s only contains lower case English letters.
*/

class Pair {
    char ch;
    int count;
    Pair(char ch, int count) {
        this.ch = ch;
        this.count = count;
    }
}

class Solution {
    public String removeDuplicates(String s, int k) {
        Stack<Pair> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (!stack.isEmpty() && s.charAt(i) == stack.peek().ch) {
                Pair top = stack.pop();
                top.count++;
                if (top.count == k) continue;
                else stack.push(top);
            } else stack.push(new Pair(s.charAt(i), 1));
        }
        StringBuilder ans = new StringBuilder();
        while (!stack.isEmpty()) {
            Pair top = stack.pop();
            char c = top.ch;
            int count = top.count;
            for (int i = 0; i < count; i++) ans.append(c);
        }
        return ans.reverse().toString();
    }
}
