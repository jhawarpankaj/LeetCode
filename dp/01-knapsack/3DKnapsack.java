/*
https://leetcode.com/problems/ones-and-zeroes/

Given an array, strs, with strings consisting of only 0s and 1s. Also two integers m and n.

Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.

Example 1:

Input: strs = ["10","0001","111001","1","0"], m = 5, n = 3
Output: 4
Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are "10","0001","1","0".
Example 2:

Input: strs = ["10","0","1"], m = 1, n = 1
Output: 2
Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".
 

Constraints:

1 <= strs.length <= 600
1 <= strs[i].length <= 100
strs[i] consists only of digits '0' and '1'.
1 <= m, n <= 100

*/

// Brute Force. Generate all subsets and find max.
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int max = 0;
        max = subsets(strs, 0, m, n, max, new ArrayList<String>());
        return max;
    }
    
    public int subsets(String[] strs, int start, int m, int n, int max, List<String> temp) {
        int ones = 0;
        int zero = 0;
        for (String str : temp) {
            for (char c : str.toCharArray()) {
                if (c == '0') zero++;
                else ones++;
            }
        }
        if (ones <= n && zero <= m) max = Math.max(max, temp.size());
        for (int i = start; i < strs.length; i++) {
            temp.add(strs[i]);
            max = Math.max(max, subsets(strs, i + 1, m, n, max, temp));
            temp.remove(temp.size() - 1);
        }
        return max;
    }
    
}

// 0/1 Knapsack.

class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[][][] dp = new int[len][m + 1][n + 1];
        int[] count = getZeroOnes(strs[0]);
        for (int j = count[0]; j <= m; j++) {
            for (int k = count[1]; k <= n; k++) {
                dp[0][j][k] = 1;
            }
        }
        for (int i = 1; i < len; i++) {
            count = getZeroOnes(strs[i]);
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    int without = dp[i - 1][j][k];
                    int with = 0;
                    if (j >= count[0] && k >= count[1]) with = 1 + dp[i - 1][j - count[0]][k - count[1]];
                    dp[i][j][k] = Math.max(with, without);
                }
            }
        }
        return dp[len - 1][m][n];
    }
    
    public int findMaxFormSpaceOptimized(String[] strs, int m, int n) {
        int len = strs.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < len; i++) {            
            int[] count = getZeroOnes(strs[i]);
            for (int j = m; j >= count[0]; j--) {
                for (int k = n; k >= count[1]; k--) {
                    int without = dp[j][k];
                    int with = 1 + dp[j - count[0]][k - count[1]];
                    dp[j][k] = Math.max(with, without);
                }
            }
        }
        return dp[m][n];
    }
    
    int[] getZeroOnes(String s) {
        int[] ans = new int[2];
        for (char c : s.toCharArray()) {
            if (c == '0') ans[0]++;
            else ans[1]++;
        }
        return ans;
    }
}
