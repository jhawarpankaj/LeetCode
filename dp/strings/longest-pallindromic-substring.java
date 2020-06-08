/*

Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: "cbbd"
Output: "bb"

*/

// DP. Calculate all sized length strings.

class Solution {
    public String longestPalindrome(String s) {
        if(s == null || s.isEmpty()) return "";
        int n = s.length(); 
        int start = 0; int max = -1;
        boolean[][] dp = new boolean[n][n];
        for(int i = 0; i < n; i++) {
            dp[i][i] = true;
            start = i;
            max = 1;
        }
        
        for(int i = 0; i < n - 1; i++) {
            if(s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                start = i;
                max = 2;
            }
        }
        
        for(int len = 3; len <= n; len++) {
            for(int i = 0; i < n - len + 1; i++){
                int j = i + len - 1;
                if(s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {                    
                    dp[i][j] = true;
                    if(j - i + 1 > max) {
                        start = i;
                        max = j - i + 1;
                    }
                }
            }
        }
        
        return s.substring(start, start + max);
    }
}

// DP, building bottom up using recurrence:
// dp[i][j] = true, if (char[i] == char[j] && dp[i + 1][j - 1] == true), len = j - i + 1;
//                  else false.

class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n == 0) return "";
        int max = 1, start = 0; 
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = true;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j == i + 1 || dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                        if (j - i + 1 > max) {
                            max = j - i + 1;
                            start = i;    
                        }   
                    }
                }
            }
        }
        return s.substring(start, start + max);
    }
}

// Space optimize DP.
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) return "";
        int n = s.length();
        int start = 0, max = 1;
        boolean[] dp = new boolean[n];
        
        for (int i = n - 1; i >= 0; i--) {
            boolean next = true;
            dp[i] = true;
            for (int j = i + 1; j < n; j++) {
                boolean temp = dp[j];
                if (s.charAt(i) == s.charAt(j) && next) {
                    dp[j] = true;
                    if (j - i + 1 > max) {
                        max = j - i + 1;
                        start = i;
                    }
                }
                else dp[j] = false;
                next = temp;
            }
        }
        
        return s.substring(start, start + max);
    }
}

/*
We can solve it in linear time by expanding the string around all the characters. There are 2 cases:
i) The string is odd length. So expanding around the center character.
ii) The string is even length. We have to expand around 2 characters.

So, the total expansions will be: n + (n - 1) = 2n - 1. 
Time complexity = O(n ^ 2).
Space = O(1).
*/

class Solution {
    public String longestPalindrome(String s) {
        if(s == null || s.isEmpty()) return "";
        int n = s.length(), max = 0, start = -1;
        for(int i = 0; i < n; i++) {
            int odd = expand(s, i, i);
            int even = expand(s, i, i + 1);
            int len = Math.max(odd, even);
            if(len > max) {
                max = len;
                start = i - (len - 1) / 2;
            }
        }
        return s.substring(start, start + max);
    }
    
    int expand(String s, int i, int j) {
        while(i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--; j++;
        }
        return j - i - 1;
    }
}
