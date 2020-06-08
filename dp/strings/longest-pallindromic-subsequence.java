/*
https://leetcode.com/problems/longest-palindromic-subsequence/

Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

Example 1:
Input:

"bbbab"
Output:
4
*/

// Generating all subsequence.

class Solution {
    public int longestPalindromeSubseq(String s) {
        return LCS(s, 0, new StringBuilder(), 0);
    }
    
    int LCS(String s, int start, StringBuilder sb, int max) {
        if (isPallindrome(sb.toString())) max = Math.max(max, sb.length());
        for (int i = start; i < s.length(); i++) {
            sb.append(s.charAt(i));
            max = Math.max(max, LCS(s, i + 1, sb, max));
            sb.deleteCharAt(sb.length() - 1);
        }
        return max;
    }
    
    boolean isPallindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) return false;
        }
        return true;
    }
}

// Using DP, by calculating soln of all lengths.

class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] L = new int[n][n];
        for (int i = 0; i < n; i++) L[i][i] = 1; //length 1.
        
        // length 2.
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) L[i][i + 1] = 2;
            else L[i][i + 1] = 1;
        }
        
        // length 3 to n.
        for (int l = 3; l <= n; l++) {
            for (int i = 0; i <= n - l; i++) {
                int j = i + l - 1;
                if (s.charAt(i) == s.charAt(j)) L[i][j] = 2 + L[i + 1][j - 1];
                else L[i][j] = Math.max(L[i][j - 1], L[i + 1][j]);
            }
        }
        return L[0][n - 1];
    }
}

// Using DP, building bottom up.

class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {                
                if (s.charAt(i) == s.charAt(j)) dp[i][j] = 2 + dp[i + 1][j - 1];
                else dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
            }
        }
        return dp[0][n - 1];
    }
}

// Space Optimized DP.

class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[] dp = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = 1;
            int next = 0; // for dp[i + 1][j - 1]
            for (int j = i + 1; j < n; j++) {
                int tmp = dp[j]; // this will act as dp[i + 1][j - 1] for the next value of j.
                if (s.charAt(i) == s.charAt(j)) dp[j] = 2 + next;
                else dp[j] = Math.max(dp[j], dp[j - 1]); // dp[j] is for dp[i + 1][j] and dp[j - 1] is for dp[i][j - 1].
                next = tmp; // for the next value of j, this will act as dp[i + 1][j - 1].
            }
        }
        return dp[n - 1];
    }
}
