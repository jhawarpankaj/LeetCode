/*
https://leetcode.com/problems/sudoku-solver/

Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
Empty cells are indicated by the character '.'.
*/

class Solution {
    public void solveSudoku(char[][] board) {
        bt(board, 0, 0);
    }
    
    private static boolean bt(char[][] arr, int row, int col) {
        
        int j = col;
        for(int i = row; i < arr.length; i++) {
            for(; j < arr.length; j++) {
                if(arr[i][j] != '.') continue;
                Set<Character> set = getAvailable(arr, i, j);
                for(char c : set) {
                    arr[i][j] = c;
                    if(bt(arr, i, j)) return true;
                    arr[i][j] = '.';
                }
                // If we reach here, it tells:
                // 1. The current cell was a '.'
                // 2. We exhausted all the available numbers for this cell (or there were no numbers 
                //    for this location, i.e., set was empty)
                // 3. The code should never reach here, if we are here it means there is no solution.
                return false;
            }
            j = 0; // if last element of a row, then move to the beginning of next row.
        }        
        return true;
    }
    
    private static Set<Character> getAvailable(char[][] arr, int row, int col) {
        Set<Character> set = new HashSet<Character>();
        for(int i = 0; i < arr.length; i++) {
            set.add(arr[row][i]);
            set.add(arr[i][col]);
        }
        
        // The 9X9 grid is encoded to numbers from 0 to 8, one number for each 3 X 3 cell.
        // That will help to know the start of each small 3 X 3 cell (from there we know that we have to iterate for the 3 X 3 cell).
        // 1. As we want to give incremental numbering, we know that whenever we move one row down, that will a (+3) for each row.
        // Hence multiply the row by 3.
        // 2. For every column, we know that it's plus 1 (to the old row value).
        
        int encode = (row / 3) * 3 + col / 3;
        for(int i = encode - (encode % 3), k = 0; k < 3; k++) {
            for(int j = (encode % 3) * 3, l = 0; l < 3; l++) {
                set.add(arr[i + k][j + l]);
            }
        }
        set.remove('.');
        
        for(int i = 1; i < 10; i++) {
            char c = (char) ('0' + i);
            if(set.contains(c)) set.remove(c);
            else set.add(c);
        }
        return set;
    }
}
