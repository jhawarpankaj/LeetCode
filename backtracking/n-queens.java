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

class Solution {
    
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        int[][] visited = new int[n][n];
        bt(n, 0, visited, temp, res);
        return res;
    }
    
    // General backtrack principle: 1) Do 2) Recurse 3) Undo
    void bt(int n, int row, int[][] visited, List<String> temp, List<List<String>> res) {
        if(row == n) {
            res.add(new ArrayList<String>(temp));
            return;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) sb.append(".");
        for(int i = 0; i < n; i++) {
            if(visited[row][i] != 0) continue;
            sb.setCharAt(i, 'Q');
            mark(visited, n, row, i, 1);
            temp.add(new String(sb));
            bt(n, row + 1, visited, temp, res);
            temp.remove(temp.size() - 1);
            mark(visited, n, row, i, -1);
            sb.setCharAt(i, '.');
        }
    }
    
    
    // Keep a note of only 45, 135 and 90 degree cells from the current position.
    // Note that space complexity can be reduced by using only 3 arrays (one each for 45, 135, and 90 degree cells)
    // instead of n^2.
    void mark(int[][] visited, int n, int i, int j, int val) {
        int p = i, q = j;
        while(p < n) {
            visited[p][q] += val;
            p++;
        }
        p = i;
        while(p < n && q < n) {
            visited[p][q] += val;
            p++; q++;
        }
        p = i; q = j;
        while(p < n && q >= 0) {
            visited[p][q] += val;
            p++; q--;
        }
    }
}
