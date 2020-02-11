/*
https://leetcode.com/problems/distinct-subsequences/

Given a string S and a string T, count the number of distinct subsequences of S which equals T.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) 
of the characters without disturbing the relative positions of the remaining characters. 
(ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Example 1:

Input: S = "rabbbit", T = "rabbit"
Output: 3
Explanation: https://www.geeksforgeeks.org/count-distinct-occurrences-as-a-subsequence/

*/

class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        if(n > m) return 0;
        
        int[][] L = new int[m + 1][n + 1];
        L[0][0] = 1; // representing both s and t empty.
        for(int i = 0; i < m; i++) L[i + 1][0] = 1;
        for(int i = 0; i < n; i++) L[0][i + 1] = 0;
                
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {                
                if(s.charAt(i) == t.charAt(j)) L[i + 1][j + 1] = L[i][j] + L[i][j + 1];
                else L[i + 1][j + 1] = L[i][j + 1];
            }
        }  
        return L[m][n];        
    }
}
