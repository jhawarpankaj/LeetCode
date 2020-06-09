/*
https://leetcode.com/problems/delete-operation-for-two-strings/

583. Delete Operation for Two Strings
Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same, where in each step you 
can delete one character in either string.

Example 1:
Input: "sea", "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
Note:
The length of given words won't exceed 500.
Characters in given words can only be lower-case letters.
*/

// Finding the longest common substring and then calculating the characters to be deleted.
class Solution {
    public int minDistance(String word1, String word2) {
        int len = LCS(word1, word2);
        return word1.length() - len + word2.length() - len;
    }
    
    int LCS(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) dp[i][j] = 1 + dp[i - 1][j - 1];
                else dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
        }
        return dp[n1][n2];
    }
}

// Using normal DP to build solution bottom up find out the minimum no of characters to be deleted for i..j

class Solution {
    public int minDistance(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int i = 0, j = 1; j <= n2; j++) dp[i][j] = j;
        for (int i = 1, j = 0; i <= n1; i++) dp[i][j] = i;
        dp[0][0] = 0;
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1];
                else dp[i][j] = 1 + Math.min(dp[i][j - 1], dp[i - 1][j]);
            }
        }
        return dp[n1][n2];
    }
}

// Space Optimized DP.
class Solution {
    public int minDistance(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        int[] dp = new int[n2 + 1];
        for (int j = 1; j <= n2; j++) dp[j] = j;
        for (int i = 1; i <= n1; i++) {
            int last = dp[0];
            dp[0] = i;
            for (int j = 1; j <= n2; j++) {
                int backup = dp[j];
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) dp[j] = last;
                else dp[j] = 1 + Math.min(dp[j], dp[j - 1]);
                last = backup;
            }
        }
        return dp[n2];
    }
}
