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

/*
https://leetcode.com/problems/minimum-falling-path-sum-ii/

Given a square grid of integers arr, a falling path with non-zero shifts is a choice of exactly one element from each row of arr, such that no two elements 
chosen in adjacent rows are in the same column.

Return the minimum sum of a falling path with non-zero shifts.

Example 1:
Input: arr = [[1,2,3],[4,5,6],[7,8,9]]
Output: 13
Explanation: 
The possible falling paths are:
[1,5,9], [1,5,7], [1,6,7], [1,6,8],
[2,4,8], [2,4,9], [2,6,7], [2,6,8],
[3,4,8], [3,4,9], [3,5,7], [3,5,9]
The falling path with the smallest sum is [1,5,7], so the answer is 13.
 
Constraints:
1 <= arr.length == arr[i].length <= 200
-99 <= arr[i][j] <= 99
*/

class Solution {
    public int minFallingPathSum(int[][] arr) {
        int n = arr.length;
        if (n == 0) return 0;
        int[] min = getMin(arr[0]); // TODO
        for (int i = 1; i < n; i++) {
            int[] newMin = {Integer.MAX_VALUE, Integer.MAX_VALUE};
            for (int j = 0; j < arr[i].length; j++) {
                if (min[0] == arr[i - 1][j]) arr[i][j] += min[1];
                else arr[i][j] += min[0];
                update(newMin, arr[i][j]); // TODO
            }
            min = newMin;
        }
        return min[0];
    }
    
    int[] getMin(int[] arr) {
        int[] ans = {Integer.MAX_VALUE, Integer.MAX_VALUE};
        for (int x : arr) update(ans, x);
        return ans;
    }
    
    void update(int[] arr, int val) {
        if (val <= arr[0]) {
            arr[1] = arr[0];
            arr[0] = val;
        } else if (val < arr[1]) {
            arr[1] = val;
        }
    }    
}
