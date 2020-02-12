/*

1. Think of the problem if there are only 1 - 1 character in both strings (two cases: they can be same or different)
2. Even before thinking 1, we should also think what if 1 word is empty and the other contains some characters.
3. Now slowly thinks that what happens when we add one more to any word and so on ...

As our base solutions build are correct and optimal and our higher soution will be based on smaller problems solution, which are optimal 
and correct, the higher solution will also be optimal and correct.

*/


class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] L = new int[m + 1][n + 1];
        L[0][0] = 0;
        for(int i = 0; i < m; i++) L[i + 1][0] = i + 1;
        for(int j = 0; j < n; j++) L[0][j + 1] = j + 1;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(word1.charAt(i) == word2.charAt(j)) L[i + 1][j + 1] = L[i][j];
                else L[i + 1][j + 1] = 1 + Math.min(L[i + 1][j], L[i][j + 1]);
            }
        }
        return L[m][n];
    }
}
