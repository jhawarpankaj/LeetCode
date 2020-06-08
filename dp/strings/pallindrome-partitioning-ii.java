/*
https://leetcode.com/problems/palindrome-partitioning-ii/
Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

Example:

Input: "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
*/

// DP O(n^3)
class Solution {
    public int minCut(String s) {
        int n = s.length();
        boolean[][] P = new boolean[n][n];
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            P[i][i] = true;
            dp[i][i] = 0;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (j == i + 1 || P[i + 1][j - 1])) {
                    P[i][j] = true;
                    dp[i][j] = 0;
                } else {
                    int min = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        min = Math.min(min, 1 + dp[i][k] + dp[k + 1][j]);
                    }
                    dp[i][j] = min;
                }
            }
        }
        return dp[0][n - 1];
    }
}

// DP Optimized. O(n^2)
class Solution {
    public int minCut(String s) {
        int n = s.length();
        boolean[][] P = new boolean[n][n];
        int[] dp = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int min = n - 1 - i;
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (j == i || j == i + 1 || P[i + 1][j - 1])) {
                    P[i][j] = true;
                    if (j == n - 1) min = 0; // if the entire string is a pallindrome.
                    // means i..j is a pallindrome and a cut after that + dp[j + 1] (which gives the count of cut for string j+1..n-1)
                    else min = Math.min(min, dp[j + 1] + 1); 
                }
            }
            dp[i] = min;
        }
        return dp[0];
    }
}
