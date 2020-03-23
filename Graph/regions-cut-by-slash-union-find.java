/*
https://leetcode.com/problems/regions-cut-by-slashes/

In a N x N grid composed of 1 x 1 squares, each 1 x 1 square consists of a /, \, or blank space.  These characters divide the square into contiguous regions.

(Note that backslash characters are escaped, so a \ is represented as "\\".)

Return the number of regions.

Example 1:

Input:
[
  " /",
  "/ "
]
Output: 2
*/

/*
The input grid is like a 2D array where each element in the grid represents each row.
The character at any positon i denotes the value of that grid.

The final regions are identified once all the small grids values are in place.
*/

class Solution {
    int[] root;
    
    public int regionsBySlashes(String[] grid) {
        int N = grid.length;
        int total = N * N * 4;
        
        // Initially, everyone points to themselves.
        root = new int[total];
        for(int i = 0; i < total; i++) root[i] = i;
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                char dir = grid[i].charAt(j);
                int[] curr = getAllFour(i, j, N);
                
                switch(dir) {
                    case ' ':
                        union(curr[0], curr[1]);
                        union(curr[1], curr[2]);
                        union(curr[2], curr[3]);
                        break;
                    case '/':
                        union(curr[0], curr[3]);
                        union(curr[1], curr[2]);
                        break;
                    case '\\':
                        union(curr[0], curr[1]);
                        union(curr[2], curr[3]);
                }
                
                // we have to union all the neighbouring grids of the current grid.
                
                if(j > 0) {
                    int[] left = getAllFour(i, j - 1, N);
                    union(left[1], curr[3]);
                }
                if(j < N - 1) {
                    int[] right = getAllFour(i, j + 1, N);
                    union(curr[1], right[3]);
                }
                if(i > 0) {
                    int[] up = getAllFour(i - 1, j, N);
                    union(up[2], curr[0]);
                }
                if(i < N - 1) {
                    int[] down = getAllFour(i + 1, j, N);
                    union(down[0], curr[2]);
                }
            }
        }
        
        int count = 0;
        for(int i = 0; i < root.length; i++) {
            if (root[i] == i) count++;
        }
        return count;
    }
    
    void union(int a, int b) {
        root[find(a)] = find(b);
    }
    
    int find(int i) {
        while(i != root[i]) {
            i = root[i];
        }
        return i;
    }
    
    // How to get these values?
    // As each cell in the grid is now divided into 4. The values of the first cell in the next row will start
    // after 4 * N * (no of rows before this row). Each column increses the value by 4. So at any cell (i, j) value will be
    // 4 * N * i + 4 * j + {0 or 1 or 2 or 3 for top, right, bottom, left}
        
    int[] getAllFour(int r, int c, int N) {
        int start = r * N * 4 + c * 4;
        return new int[] {start + 0, start + 1, start + 2, start + 3};
    }
}
