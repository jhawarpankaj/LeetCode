/*

https://leetcode.com/problems/edit-distance/

Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character
Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
*/

// DP. 
// We find a solution for dp[i][j] where i is for word1 and j for word2.
// case a) dp[i][j] = dp[i - 1][j - 1] , if char(i) == char(j)
// case b) dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], 1 + Math.min(dp[i - 1][j], dp[i][j  -1])), if char(i) != char(j).
// In case b, we pick the best answer among:
// not selecting both, selecting only i, or selecting only j. Selecting both is not an option.

class Solution {
    public int minDistance(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int i = 0, j = 1; j <= n2; j++) dp[i][j] = j;
        for (int i = 1, j = 0; i <= n1; i++) dp[i][j] = i;
        dp[0][0] = 0;
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1];
                else dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
            }
        }
        return dp[n1][n2];
    }
}

// Space Optimized DP.
// we need values only from the (i - 1)th row. So we will update the same array.
// the already present value in the array represent value of dp[i - 1] row. 
// We will loose the value of dp[i - 1][j - 1] as we update the dp array. So we will store that in a temp variable.
class Solution {
    public int minDistance(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        int[] dp = new int[n2 + 1];
        for (int j = 1; j <= n2; j++) dp[j] = j;
        for (int i = 1; i <= n1; i++) {
            dp[0] = i;
            int last = i - 1;
            for (int j = 1; j <= n2; j++) {
                int temp = dp[j];
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) dp[j] = last;
                else dp[j] = 1 + Math.min(last, Math.min(dp[j], dp[j - 1]));
                last = temp;
            }
        }
        return dp[n2];
    }
}
