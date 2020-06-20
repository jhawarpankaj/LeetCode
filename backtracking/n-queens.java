/*
https://leetcode.com/problems/n-queens/

The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen 
and an empty space respectively.

Example:

Input: 4
Output: [
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
*/

/* 
General backtracking principle again like Sudoku:
1. Find all possible position of queen in row.
2. Try each one of them one by one using backtracking.
*/

class Solution {
    
    public List<List<String>> solveNQueens(int n) {           
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < board.length; i++) {
            Arrays.fill(board[i], '.');
        }
        bt(board, result, 0, n);
        return result;
    }
    
    void bt(char[][] board, List<List<String>> result, int row, int n) {
        if (row == n) {
            List<String> temp = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    sb.append(board[i][j]);
                }
                temp.add(sb.toString());
            }
            result.add(temp);
            return;
        }
        List<Character> available = getAvailable(board, row, n);
        for (int j = 0; j < available.size(); j++) {
            if (available.get(j).equals('Q')) {
                board[row][j] = 'Q';
                bt(board, result, row + 1, n);
                board[row][j] = '.';
            }
        }        
    }
    
    List<Character> getAvailable(char[][] board, int row, int n) {
        List<Character> result = new ArrayList<>();        
        for (int j = 0; j < n; j++) {            
            int col = j;
            int rows = row;
            boolean flag = false;
            // these loops can be merged into one, using little maths.
            // not in mood right now tp do the math but see that at every iteration add 1 and subtract 1 or something like that.
            while (!flag && rows > 0) {
                if (board[--rows][col] == 'Q') flag = true;
            }
            rows = row;
            col = j;
            while (!flag && rows > 0 && col > 0) {
                if (board[--rows][--col] == 'Q') flag = true;
            }            
            rows = row;
            col = j;
            while (!flag && rows > 0 && col < n - 1) {
                if (board[--rows][++col] == 'Q') flag = true;
            }
            if (!flag) result.add('Q');
            else result.add('.');
        }
        return result;
        
    }
    
}
