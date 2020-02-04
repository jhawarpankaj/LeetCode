/*

A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).

*/


class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        
        // Note that dp[n] actually represent value for string index from 0 to n - 1;
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        
        for(int i = 2; i <= n; i++) {
            // first actually represent the current character.
            int first = Integer.parseInt(s.substring(i - 1, i));
            // second represent current and previous character. 
            int second = Integer.parseInt(s.substring(i - 2, i));
            // if current char is 0, this cannot be taken as a single step.
            if(first >= 1 && first <= 9) dp[i] += dp[i - 1]; 
            // if last 2 char(including current one) has a leading, then we cannot take a 2 step jump.
            if(second >= 10 && second <= 26) dp[i] += dp[i - 2];
        }
        return dp[n];
    }
}
