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

/*

Idea is if:

a) Last 2 characters are same there can be 3 scenarios:
    i) We don't need any conversion for that last character and hence L[i + 1][j + 1] = L[i][j];
    ii) Search for min edit distance in L[i + 1][j] (i.e., one less character of word2, which we can insert later).
    iii) Search for min edit distance in L[i][j + 1] (i.e., one less character of word1, which we can delete later).
    
    Since, we need to find the min edit distance, we take a min of all three.
    
b) Similar is the idea in case when the last character is not same, i.e., 
   we can either replace, insert or delete the last character and then find the min.

*/


class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] L = new int[m + 1][n + 1];
        L[0][0] = 0;
        for(int i = 0; i < m; i++) L[i + 1][0] = i + 1;
        for(int i = 0; i < n; i++) L[0][i + 1] = i + 1;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(word1.charAt(i) == word2.charAt(j)) L[i + 1][j + 1] = Math.min(L[i][j], 1 + Math.min(L[i + 1][j], L[i][j + 1]));
                else L[i + 1][j + 1] = 1 + Math.min(L[i][j], Math.min(L[i + 1][j], L[i][j + 1]));
            }
        }
        return L[m][n];
    }
}
