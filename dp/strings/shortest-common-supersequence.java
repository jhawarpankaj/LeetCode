/*

Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences.  
If multiple answers exist, you may return any of them.

(A string S is a subsequence of string T if deleting some number of characters from T 
(possibly 0, and the characters are chosen anywhere from T) results in the string S.) 

Example 1:

Input: str1 = "abac", str2 = "cab"
Output: "cabac"
Explanation: 
str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
The answer provided is the shortest such string that satisfies these properties.
*/

class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        if(m == 0) return str2;
        if(n == 0) return str1;
        int[][] L = new int[m + 1][n + 1];
        
        // L[i + 1][j + 1] will store for the string's char str(i) & str(j). So, what about
        // L[0][0] = it will store for the string str1(-1) and str2(-1), which is empty string = 0;
        // L[1][0] for str1(0) and str2(-1) which will be governed by i in L[i][j]
        // i.e., L[i][0] = i; and L[0][j] = 0;
        
        // But why we need these? Because The shortest common supersequence of str1 = "" and str2 = "ABBC" is always str2.
        
        
        for(int i = 0; i < m; i++) L[i][0] = i;
        for(int j = 0; j < n; j++) L[0][j] = j;
        
        /*
            The recurrence relation is:
                L[i][j] = 1 + L[i - 1][j - 1], if str1(i) == str2(j)
          else, L[i][j] = 1 + Math.min(L[i - 1][j], L[i][j - 1]);
          
            Why, 1 + min()? 
            Consider the example: s1 = BC and s2 = DB;
            i) If the last characters are same, then that character has to be in supersequence. (handled by 1st recurrence relation)
            ii) If last characters are not same, then we can have two cases: [s1 = B, s2 = DB OR s1 = BC, s2 = D] but in both the cases
                as both the characters are different 1 has to be added atleast (for any one of the above cases, but which one we don't know yet).
                After that one addition, whichever of the remaining 2 cases will get us the supersequence in the least number of characters is the candidate
                for least supersequence.
                
                In this case: 
                    s1 = B, s2 = CB will get us the supersequence in 2 characters = CB.
                    s1 = BC, s2 = D will get us the supersequence in 3 characters = BCD.
                    
                    So, 1 + min(2, 3) = 3 will be the answer, i.e., DBC.                
        */
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {                                
                if(str1.charAt(i) == str2.charAt(j)) L[i + 1][j + 1] = 1 + L[i][j];                
                else L[i + 1][j + 1] = 1 + Math.min(L[i + 1][j], L[i][j + 1]);
            }
        }
        
        int i = m, j = n;
        StringBuilder sb = new StringBuilder();
        /*
            Building the solution back is just backtracking the same way we build the soln.
            Every time, when the characters matches we do 1 + diagonal
            otherwise, min of left or top neighbour. Same way we trace back.
            
            Watch this video: https://www.youtube.com/watch?time_continue=1556&v=DuikFLPt8WQ&feature=emb_title
        */
        while(i > 0 && j > 0) {
            if(str1.charAt(i - 1) == str2.charAt(j - 1)) {
                sb.insert(0, str1.charAt(i - 1));
                i--; j--;
            }
            else if(L[i][j - 1] < L[i - 1][j]) {
                j--;
                sb.insert(0, str2.charAt(j));
            }
            else {
                i--;
                sb.insert(0, str1.charAt(i));
            }
        }
        
        while(i > 0) sb.insert(0, str1.charAt(--i));
        while(j > 0) sb.insert(0, str2.charAt(--j));
        
        return sb.toString();
    }
}
