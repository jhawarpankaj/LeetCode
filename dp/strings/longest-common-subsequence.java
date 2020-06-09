/*
https://leetcode.com/problems/longest-common-subsequence/

Given two strings text1 and text2, return the length of their longest common subsequence.

A subsequence of a string is a new string generated from the original string with some characters(can be none) deleted 
without changing the relative order of the remaining characters. (eg, "ace" is a subsequence of "abcde" while "aec" is not). 
A common subsequence of two strings is a subsequence that is common to both strings.

If there is no common subsequence, return 0.

Example 1:

Input: text1 = "abcde", text2 = "ace" 
Output: 3  
Explanation: The longest common subsequence is "ace" and its length is 3.

*/

// DP.
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int n1 = text1.length();
        int n2 = text2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int i = 1, j = 0; i <= n1; i++) dp[i][j] = 0;
        for (int i = 0, j = 1; j <= n2; j++) dp[i][j] = 0;        
        dp[0][0] = 0;
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) dp[i][j] = 1 + dp[i - 1][j - 1];
                else dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
        }
        return dp[n1][n2];
    }
}

// Space Optimized DP. As we can see, we always need the last modified array.
// We can update the same array.
// For linear space explanation: https://www.youtube.com/watch?time_continue=1556&v=DuikFLPt8WQ&feature=emb_title
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int n1 = text1.length();
        int n2 = text2.length();
        int[] dp = new int[n2 + 1];
        // we are filling this for the first row when i = 0
        for (int j = 1; j <= n2; j++) dp[j] = 0;
        for (int i = 1; i <= n1; i++) {
            int prev = 0;
            for (int j = 1; j <= n2; j++) {
                int backup = dp[j];
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) dp[j] = 1 + prev;
                // here dp[j] = dp[i - 1][j - 1], as the value of dp[j] has not yet been updated.
                // and dp[j - 1] = dp[i][j - 1]
                else dp[j] = Math.max(dp[j], dp[j - 1]);
                prev = backup;
            }
        }
        return dp[n2];
    }
}
    
    // This prints the LCS.
    // Watch: https://www.youtube.com/watch?time_continue=1556&v=DuikFLPt8WQ&feature=emb_title
    // to understand the backtracking while printing the LCS.
    private static void printingLCS(String x, String y, int m, int n) {
		int[][] dp = new int[m + 1][n + 1];
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(x.charAt(i) == y.charAt(j)) dp[i + 1][j + 1] = 1 + dp[i][j];
				else dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
			}
		}
		int len = dp[m][n];
		char[] c = new char[len];
		int i = m, j = n, ind = len - 1;
		while(i > 0 && j > 0) {
			if(x.charAt(i - 1) == y.charAt(j - 1)) {
				c[ind--] = x.charAt(i - 1);
				i--; j--;
			}
			else if(dp[i][j - 1] > dp[i - 1][j]) j--;
			else i--;
		}
		System.out.print(Arrays.toString(c));
	}
}
