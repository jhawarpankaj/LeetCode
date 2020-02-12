/*
    https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/
    
    L[i][j] represents the minimumDeleteSum for s1(0..i) and s2(0..j). We build the soln bottom up.
    Idea: 
    a) if last 2 characters are same, we need not delete them. So, L[i + 1][j + 1] = L[i][j];
    b) if not same: delete either s1's last character or s2's last character and get the min of both.

*/

class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] L = new int[m + 1][n + 1];
        L[0][0] = 0;
        for(int i = 0; i < m; i++) L[i + 1][0] = s1.codePointAt(i) + L[i][0];
        for(int j = 0; j < n; j++) L[0][j + 1] = s2.codePointAt(j) + L[0][j];
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(s1.charAt(i) == s2.charAt(j)) L[i + 1][j + 1] = L[i][j];
                else L[i + 1][j + 1] = Math.min(L[i + 1][j] + s2.codePointAt(j)
                                                , L[i][j + 1] + s1.codePointAt(i));
            }
        }
        return L[m][n];
    }
}
