/*
https://leetcode.com/problems/word-search/

Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
*/

/*
Note that we avoided using extra space by doing an in place update in the 2-D array (using #) and then undoing the change while bactracking, a common paradigm of the backtracking problems.
Also, its important to understand how recursion (or backtracking) works. On each recursion call, if we have added the # in during the recursion, that gets removed always, and no call leaves the # in the 2-D board, because the 2 statements will always get executed. So that's a good way to check if a node has already been visited or not.
*/

class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        // boolean[][] visited = new boolean[m][n];
        char c = word.charAt(0);
        for(int i = 0; i < m; i++) {            
            for(int j = 0; j < n; j++) {
                if(board[i][j] == c) {
                    if(dfs(board, i, j, word, 0)) return true;
                    // visited = new boolean[m][n];
                }
            }
        }
        return false;
    }
    
    boolean dfs(char[][] board, int i, int j, String word, int ind) {
        if(word.length() == ind) return true;
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || word.charAt(ind) != board[i][j] || board[i][j] == '#') return false;        
        // if(visited[i][j]) return false;
        // visited[i][j] = true;
        board[i][j] ='#';
      
        // Note that below are short circuit calls, if any of these gets true, the successive calls are not made.
        boolean flag = dfs(board, i + 1, j, word, ind + 1) || 
            dfs(board, i - 1, j, word, ind + 1) ||
            dfs(board, i, j - 1, word, ind + 1)||
            dfs(board, i, j + 1, word, ind + 1);
        // visited[i][j] = false;
        board[i][j] = word.charAt(ind);
        return flag;
    }
    
}
