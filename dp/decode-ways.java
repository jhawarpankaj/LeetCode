/*

https://leetcode.com/problems/decode-ways/

A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
*/

/*
Again the same DP problem. It feels like should be solved using Backtracking.
But always try to think that we are at index i and we have solution for all smaller subproblems.
How will you now compute for dp[i]? You will get the answer: Depending on the current digit or last digit and using
the solution to smaller subproblems, we can compute dp[i].

Also note another thing that here we are doing straight addition, but combinatorics problems generally means multiplication to get total count.
See Decode Ways II to understand.
*/

class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        if (s.charAt(0) != '0') dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            char curr = s.charAt(i - 1);
            char prev = s.charAt(i - 2);
            if (prev == '1' || (prev == '2' && curr <= '6')) dp[i] += dp[i - 2]; // note that prev cannot be == 0 or > 2.
            if (curr != '0') dp[i] += dp[i - 1];
        }
        return dp[n];
    }
}

/*
https://leetcode.com/problems/decode-ways-ii/

A message containing letters from A-Z is being encoded to numbers using the following mapping way:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Beyond that, now the encoded string can also contain the character '*', which can be treated as one of the numbers from 1 to 9.

Given the encoded message containing digits and the character '*', return the total number of ways to decode it.

Also, since the answer may be very large, you should return the output mod 109 + 7.

Example 1:
Input: "*"
Output: 9
Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".
Example 2:
Input: "1*"
Output: 9 + 9 = 18
Note:
The length of the input string will fit in range [1, 105].
The input string will only contain the character '*' and digits '0' - '9'.
*/

/*
This problem just becomes an implementation problem. Properly modularize the code and then finally write code for all cases.
Also note that combanitorics means multiplication.
Above problem was a special case of this, where the multiplying factor was 1.
*/

class Solution {
    public int numDecodings(String s) {
        final int MOD = 1_000_000_007;
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        if (s.charAt(0) == '*') dp[1] = 9;
        else if (s.charAt(0) != '0') dp[1] = 1; 
        for (int i = 2; i <= n; i++) {
            char curr = s.charAt(i - 1);
            char prev = s.charAt(i - 2);
            dp[i] += (singleDigit(curr) * dp[i - 1]) % MOD;
            dp[i] += (doubleDigit(prev, curr) * dp[i - 2]) % MOD;
        }
        return dp[n];
    }
    
    int singleDigit(char curr) {
        if (curr == '0') return 0;
        else if (curr == '*') return 9;
        else return 1;
    }
    
    int doubleDigit(char prev, char curr) {
        
        // case making an invalid 2 digit number.
        if (prev == '0' || prev > '2') return 0;
        if (prev == '2' && curr > '6') return 0;
        
        if (prev == '*' && curr == '*') return 15; // as 0 is not an available number for encoding, so no 10, 20.
        else if (prev != '*' && curr == '*') {
            if (prev == '1') return 9; // 11-19
            else return 6; // 21 - 26
        } else if (prev == '*' && curr != '*') {
            if (curr >= '0' && curr <= '6') return 2;
            else return 1;
        } else return 1;
    }
}
