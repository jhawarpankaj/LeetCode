/*

Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences.  
If multiple answers exist, you may return any of them.

(A string S is a subsequence of string T if deleting some number of characters from T 
(possibly 0, and the characters are chosen anywhere from T) results in the string S.) 

Example 1:

Input: str1 = "abac", str2 = "cab"
Output: "cabac"
Explanation: 
str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
The answer provided is the shortest such string that satisfies these properties.
*/

class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
        int n1 = str1.length();
        int n2 = str2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];
        // dp[0][5] means no character of string str1 and 5 characters of str2.
        for (int i = 1; i <= n1; i++) dp[i][0] = i;
        for (int j = 1; j <= n2; j++) dp[0][j] = j;
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) dp[i][j] = 1 + dp[i - 1][j - 1];
                // if the current characters are not same, as we need the min supersequence, we pick the minimum of 
                // dp[i][j - 1]  and dp[i + 1][j], and also have to add 1 for the current character left.
                else dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        
        // Building the solution back from the DP table.
        int i = n1, j = n2;
        StringBuilder sb = new StringBuilder();
        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                sb.insert(0, str1.charAt(i - 1)); 
                i--; j--;                                               
            } else if (dp[i][j - 1] < dp[i - 1][j]) {
                sb.insert(0, str2.charAt(j - 1));
                j--;                                
            } else {
                sb.insert(0, str1.charAt(i - 1));
                i--;                
            }
        }
        
        // Add the remaining characters as is.
        while (i > 0) sb.insert(0, str1.charAt(--i));
        while (j > 0) sb.insert(0, str2.charAt(--j));
        return sb.toString();
    }
}
