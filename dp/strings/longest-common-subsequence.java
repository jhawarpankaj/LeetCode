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

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for(int i = 0; i < text1.length(); i++) {
            for(int j = 0; j < text2.length(); j++) {
                // dp[i + 1][j + 1] is for char i and j.
                if(text1.charAt(i) == text2.charAt(j)) dp[i + 1][j + 1] = 1 + dp[i][j]; 
                else dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
            }
        }
        return dp[text1.length()][text2.length()];
    }
}

/*
Space efficient approach: In the above implementation, we can see that at every iteration, we only need the values:
dp[i + 1][j] and dp[i][j + 1] OR dp[i][j]

Imagine these values as a row in a 2D matrix, we want to calculate dp[i + 1][j + 1] and the required values are among the 
adjacent value or one row above. So we just need only these values.

For linear space explanation: https://www.youtube.com/watch?time_continue=1556&v=DuikFLPt8WQ&feature=emb_title
*/

class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int[] dp = new int[text2.length() + 1];
        int temp = 0, prev = 0;
        for(int i = 0; i < text1.length(); i++) {
            temp = 0;
            for(int j = 0; j < text2.length(); j++) {
                // this for storing the current value which will be needed in next iteration as this is
                // going to be changed in this iteration.
                prev = dp[j + 1]; 
                if(text1.charAt(i) == text2.charAt(j)) dp[j + 1] = temp + 1;
                else dp[j + 1] = Math.max(dp[j + 1], dp[j]);
                temp = prev;
            }         
        }
        return dp[text2.length()];
    }
}
