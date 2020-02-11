/*
Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

Example 1:
Input:

"bbbab"
Output:
4
*/

class Solution {
  public int longestPalindromeSubseq(String s) {
          if(s == null || s.isEmpty()) return 0;
          int n = s.length();
          int[][] L = new int[n][n];
          for(int i = 0; i < n; i++) L[i][i] = 1;
          for(int i = 0; i < n - 1; i++) L[i][i + 1] = (s.charAt(i) == s.charAt(i + 1) ? 2 : 1);
          for(int len = 3; len <= n; len++) {
              for(int i = 0; i < n - len + 1; i++) {
                  int j = i + len - 1;
                  if(s.charAt(i) == s.charAt(j)) L[i][j] = 2 + L[i + 1][j - 1];
                  else L[i][j] = Math.max(L[i + 1][j], L[i][j - 1]);
              }
          }
          return L[0][n - 1];
      }
}


class Solution {
  public int longestPalindromeSubseq(String s) {
          if(s == null || s.isEmpty()) return 0;
          int n = s.length();
          int[] L = new int[n];
          int temp = 0;
          for(int i = n - 1; i >= 0; i--) {
              for(int j = i; j < n; j++) {
                  int prev = L[j];
                  if(i == j) L[j] = 1;
                  else if(s.charAt(i) == s.charAt(j)) L[j] = 2 + temp;
                  else L[j] = Math.max(L[j - 1], L[j]);
                  temp = prev;
              }
          }
          return L[n - 1];
      }
}    
