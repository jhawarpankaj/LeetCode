/*
https://leetcode.com/problems/sudoku-solver/

Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
Empty cells are indicated by the character '.'.
*/

/*
General backtracking principle: 
1. Find all possible values for a cell.
2. Try each one of them one by one.
*/


class Solution {
    public void solveSudoku(char[][] board) {
        bt(board, 0, 0);
    }
    
    public boolean bt(char[][] board, int row, int col) {
        
        // whenever a row moves on to then next row, then only we should make col j = 0;
        for (int i = row, j = col; i < board.length; i++, j = 0) {
            for (; j < board[i].length; j++) {                
                if (board[i][j] != '.') continue;
                for (char c : getAvailable(board, i, j)) {
                    board[i][j] = c;
                    if(bt(board, i, j + 1)) return true;
                    board[i][j] = '.';
                }
                // If we reach here, means that the current cell was a '.' and
                // 1. We exhausted all the available numbers for this cell (or there were no numbers 
                //    for this location, i.e., set was empty)
                // 2. The code should never reach here, if we are here it means there is no solution.
                return false;
            }
        }
        return true;
    }
    
    public Set<Character> getAvailable(char[][] board, int row, int col) {
        Set<Character> set = new HashSet<>();
        // all elements in row.
        for (int i = row, j = 0; j < board[0].length; j++) set.add(board[i][j]);
        // all elements in col.
        for (int i = 0, j = col; i < board.length; i++) set.add(board[i][j]);
        // all elements in the small square. 
        // Given original (i, j), if we can find the starting (i, j) of the small square, we can iterate easily.
        // We can see that the entire sudoku is divided in small blocks.
        // Beginning of each row can be in BLOCK 1, BLOCK 2, or BLOCK 3. Same for column.
        // (row + 1) / 3 will give the BLOCK of row but that will be BLOCK 1, 2, 3. We need in terms of 0 indexing.
        // So, we can just subtract 1. And again, multiply by 3 will give exact row of small square beginning.
        int scaledRow = (int)(Math.ceil((row + 1) / 3.0) - 1) * 3;
        int scaledCol = (int)(Math.ceil((col + 1) / 3.0) - 1) * 3;        
        for (int i = scaledRow, j = scaledCol, k = 1; k <= 3; k++, i++) {
            set.add(board[i][j]);
            set.add(board[i][j + 1]);
            set.add(board[i][j + 2]);
        }
        set.remove('.');
        for (char c = '1'; c <= '9'; c++) {
            if (!set.contains(c)) set.add(c);
            else set.remove(c);
        }
        return set;
    }
}
