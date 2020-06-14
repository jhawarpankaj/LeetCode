/*
https://leetcode.com/problems/regular-expression-matching/

Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like . or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input:
s = "aa"
p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
Example 3:

Input:
s = "ab"
p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
Example 4:

Input:
s = "aab"
p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
Example 5:

Input:
s = "mississippi"
p = "mis*is*p*."
Output: false
*/

class Solution {
    public boolean isMatch(String s, String p) {
        int n1 = s.length();
        int n2 = p.length();
        boolean[][] dp = new boolean[n1 + 1][n2 + 1];
        dp[0][0] = true; // no character in both strings, hence match.
        
        // no character in first string.
        for (int i = 0, j = 1; j <= n2; j++) {
            if (p.charAt(j - 1) == '*') dp[i][j] = dp[i][j - 2];
        }
        
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
            // When the current character of j (which we represent by j - 1) is a *, there can be two cases:
            // 1. p.charAt(j - 2) == s.charAt(i):
            //    We can have multiple cases here, for example, a* => {}, {a}, {aa}, {aaa....}. 
            //    These cases are: 
            //    CASE A: {} -> dp[i][j - 2]
            //    CASE B: {a} -> dp[i - 1][j - 2]
            //    CASE C: {aa} -> dp[i - 1][j - 1]
            //    CASE D: {aaa...} -> dp[i - 1][j], as j is a * which means any number of repeatition. But this CASE D actually contains
            //    CASE B and CASE C as well. 
            //    If we don't select any characters, dp[i - 1][j] = dp[i - 1][j - 2]
            //    If we select one character, dp[i - 1][j] = dp[i - 1][j - 1].
            //    And now if we select more than 1 charactere, dp[i - 1][j] = dp[i - 1][j].
            // 2. Else, p.charAt(j - 2) != s.charAt(i - 1):
            //    dp[i][j] = dp[i][j - 2].
                if (p.charAt(j - 1) == '*') {
                    if (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.') dp[i][j] = dp[i - 1][j] || dp[i][j - 2];
                    else dp[i][j] = dp[i][j - 2];
                }
                else if (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1)) dp[i][j] = dp[i - 1][j - 1];
            }
        }        
        return dp[n1][n2];
    }
}

/*
https://leetcode.com/problems/wildcard-matching/

Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like ? or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input:
s = "aa"
p = "*"
Output: true
Explanation: '*' matches any sequence.
Example 3:

Input:
s = "cb"
p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
Example 4:

Input:
s = "adceb"
p = "*a*b"
Output: true
Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
Example 5:

Input:
s = "acdcb"
p = "a*c?b"
Output: false
*/

class Solution {
    public boolean isMatch(String s, String p) {
        int n1 = s.length();
        int n2 = p.length();
        boolean[][] dp = new boolean[n1 + 1][n2 + 1];
        dp[0][0] = true;
        for (int i = 0, j = 1; j <= n2; j++) {
            if (p.charAt(j - 1) == '*') dp[i][j] = dp[i][j - 1];
        }
        
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                // If current char j is a *, there can be 3 cases:
                //  dp[i][j] = dp[i - 1][j - 1] // If we match both i and j, i.e., assuming one char for * -> {a}
                //  dp[i][j] = dp[i][j - 1] // if we dont consider any character for j. * -> {}.
                //  dp[i][j] = dp[i- 1][j] // if we assume multiple character for * -> {aaaa...}
                //  But dp[i - 1][j] will cover for dp[i - 1][j - 1] as well, if we assume {} for the *.
                if (p.charAt(j - 1) == '*') dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                else if (p.charAt(j - 1) == '?' || p.charAt(j - 1) == s.charAt(i - 1)) dp[i][j] = dp[i - 1][j - 1];
            }
        }        
        return dp[n1][n2];
    }
}
