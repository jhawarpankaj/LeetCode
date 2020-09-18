/*
https://leetcode.com/problems/minimum-falling-path-sum/

Given a square array of integers A, we want the minimum sum of a falling path through A.
A falling path starts at any element in the first row, and chooses one element from each row.  The next row's 
choice must be in a column that is different from the previous row's column by at most one.

Example 1:
Input: [[1,2,3],[4,5,6],[7,8,9]]
Output: 12
Explanation: 
The possible falling paths are:
[1,4,7], [1,4,8], [1,5,7], [1,5,8], [1,5,9]
[2,4,7], [2,4,8], [2,5,7], [2,5,8], [2,5,9], [2,6,8], [2,6,9]
[3,5,7], [3,5,8], [3,5,9], [3,6,8], [3,6,9]
The falling path with the smallest sum is [1,4,7], so the answer is 12.

Note:
1 <= A.length == A[0].length <= 100
-100 <= A[i][j] <= 100
*/

/*
1. Again note that, we are always told the forward ways to reach (i, j). But think reverse: that you are already at (i, j), then ask, how did you reach here.
2. Always write modular code to avoid clutter in the main flow of the code. (TODOs itentionally left to emphasize that).
*/

class Solution {
    public int minFallingPathSum(int[][] A) {
        int r = A.length, c = A[0].length; // it will be the same as it is a square.        
        for (int i = 1; i < r; i++) {
            A[i][0] += Math.min(A[i - 1][0], A[i - 1][1]); // if it is first cell of each row
            A[i][c - 1] += Math.min(A[i - 1][c - 2], A[i - 1][c - 1]); // if it is last cell of each row
            for (int j = 1; j < c - 1; j++) {
                A[i][j] += min(A[i - 1][j - 1], A[i - 1][j], A[i - 1][j + 1]); // TODO
            }
        }        
        return getMin(A[r - 1]); // TODO        
    }
    
    int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
    
    int getMin(int[] arr) {
        int min = arr[0];
        for (int x : arr) min = Math.min(min, x);
        return min;
    }
    
}
