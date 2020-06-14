/*
https://leetcode.com/problems/interleaving-string/
Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

Example 1:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true
*/

// Recursion 3 dimensional with memoization.
// In all soln, i stands for s1, j for s2.
// dp[2][5][7] will represent first 2 character of s1 and first 5 char of s2 and 7 char of s3.
// Actually the 3rd dimension is not required, because the value of i and j will tell us the value of s3. (i + j - 1). See next soln.

class Solution {
    int[][][] dp;
    public boolean isInterleave(String s1, String s2, String s3) {
        int n1 = s1.length();
        int n2 = s2.length();
        int n3 = s3.length();
        if (n1 + n2 != n3) return false;
        dp = new int[n1 + 1][n2 + 1][n3 + 1];
        for (int i = 0; i <= n1; i++) {
            for (int j = 0; j <= n2; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        dp[0][0][0] = 1;
        return helper(s1, s2, s3, n1, n2, n3);
    }
    
    boolean helper(String s1, String s2, String s3, int i1, int i2, int i3) {
        if (dp[i1][i2][i3] != -1) return dp[i1][i2][i3] == 1 ? true : false;
        if (i1 == 0 && i2 == 0 && i3 == 0) return true;
        if (i1 == 0) {
            if (s2.charAt(i2 - 1) != s3.charAt(i3 - 1)) {
                dp[i1][i2][i3] = 0;
                return false;
            }
            else return helper(s1, s2, s3, i1, i2 - 1, i3 - 1);
        }
        else if (i2 == 0) {
            if (s1.charAt(i1 - 1) != s3.charAt(i3 - 1)) {
                dp[i1][i2][i3] = 0;
                return false;
            }
            else return helper(s1, s2, s3, i1 - 1, i2, i3 - 1);
        }
        else {
            boolean flag1 = false;
            boolean flag2 = false;
            if (s1.charAt(i1 - 1) == s3.charAt(i3 - 1)) flag1 = helper(s1, s2, s3, i1 - 1, i2, i3 - 1);
            if (s2.charAt(i2 - 1) == s3.charAt(i3 - 1)) flag2 = helper(s1, s2, s3, i1, i2 - 1, i3 - 1);
            boolean flag = flag1 || flag2;
            dp[i1][i2][i3] = flag ? 1 : 0;
            return flag;
        }
    }
}

// 2D DP.

class Solution {
    int[][] dp;
    public boolean isInterleave(String s1, String s2, String s3) {
        int n1 = s1.length();
        int n2 = s2.length();
        int n3 = s3.length();
        if (n1 + n2 != n3) return false;
        dp = new int[n1 + 1][n2 + 1];
        for (int i = 0; i <= n1; i++) {
            // for (int j = 0; j <= n2; j++) {
                Arrays.fill(dp[i], -1);
            // }
        }
        dp[0][0] = 1;
        return helper(s1, s2, s3, n1, n2, n3);
    }
    
    boolean helper(String s1, String s2, String s3, int i1, int i2, int i3) {
        if (dp[i1][i2] != -1) return dp[i1][i2] == 1 ? true : false;
        if (i1 == 0 && i2 == 0 && i3 == 0) return true;
        if (i1 == 0) {
            if (s2.charAt(i2 - 1) != s3.charAt(i3 - 1)) {
                dp[i1][i2] = 0;
                return false;
            }
            else return helper(s1, s2, s3, i1, i2 - 1, i3 - 1);
        }
        else if (i2 == 0) {
            if (s1.charAt(i1 - 1) != s3.charAt(i3 - 1)) {
                dp[i1][i2] = 0;
                return false;
            }
            else return helper(s1, s2, s3, i1 - 1, i2, i3 - 1);
        }
        else {
            boolean flag1 = false;
            boolean flag2 = false;
            if (s1.charAt(i1 - 1) == s3.charAt(i3 - 1)) flag1 = helper(s1, s2, s3, i1 - 1, i2, i3 - 1);
            if (s2.charAt(i2 - 1) == s3.charAt(i3 - 1)) flag2 = helper(s1, s2, s3, i1, i2 - 1, i3 - 1);
            boolean flag = flag1 || flag2;
            dp[i1][i2] = flag ? 1 : 0;
            return flag;
        }
    }
}

// We can see that we only need the last array, so space can be further optimized.

class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int n1 = s1.length();
        int n2 = s2.length();
        int n3 = s3.length();
        if (n1 + n2 != n3) return false;
        boolean[] dp = new boolean[n2 + 1];
        dp[0] = true;
        for (int i = 0, j = 1; j <= n2; j++) dp[j] = s2.charAt(j - 1) == s3.charAt(j - 1) && dp[j - 1];
        for (int i = 1; i <= n1; i++) {
            dp[0] = s1.charAt(i - 1) == s3.charAt(i - 1) && dp[0];
            for (int j = 1; j <= n2; j++) {
                if (s1.charAt(i - 1) == s3.charAt(i + j - 1) && dp[j]) dp[j] = true;
                else if (s2.charAt(j - 1) == s3.charAt(i + j - 1) && dp[j - 1]) dp[j] = true;
                else dp[j] = false;
            }
        }
        return dp[n2];
    }
}
