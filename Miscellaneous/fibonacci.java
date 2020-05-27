/*
https://leetcode.com/problems/fibonacci-number/

The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number is the sum of the 
two preceding ones, starting from 0 and 1. That is,

F(0) = 0,   F(1) = 1
F(N) = F(N - 1) + F(N - 2), for N > 1.
Given N, calculate F(N).
*/

/*

Proof of this matrix exponentiation method:
We know that, F(n) = F(n - 1) + F(n - 2). This can be written as product of 2 matrices as:

F(n) = [1 1][F(n - 1)]
            [F(n - 2)]
            
This can be rewritten as:

[  F(n)  ] = [1 1] [F(n - 1)]
[F(n - 1)]   [1 0] [F(n - 2)]

Recursively, keep substituting the values on the right, we can come to the equation:

[F(n + 1)] = ([1 1]) ^ n * [F1]
[  F(n)  ]   ([1 0])       [F0]

*/
class Solution {
    public int fib(int N) {
        int[][] M = new int[][] {{1, 1}, {1, 0}};
        if (N == 0) return 0;
        pow(M, N - 1);
        return M[0][0];
    }
    
    void pow(int[][] M, int n) {
        if (n == 0 || n == 1) return;
        pow(M, n / 2);
        multiply(M, M);        
        if (n % 2 == 1) multiply(M, new int[][] {{1, 1}, {1, 0}});
    }
    
    void multiply(int[][] A, int[][] B) {
        int x = A[0][0] * B[0][0] + A[0][1] * B[1][0];
        int y = A[0][0] * B[0][1] + A[0][1] * B[1][1];
        int z = A[1][0] * B[0][0] + A[1][1] * B[1][0];
        int w = A[1][0] * B[0][1] + A[1][1] * B[1][1];

        A[0][0] = x;
        A[0][1] = y;
        A[1][0] = z;
        A[1][1] = w;
    }
}
