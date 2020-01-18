/*

There is a fence with n posts, each post can be painted with one of the k colors.

You have to paint all the posts such that no more than two adjacent fence posts have the same color.

Return the total number of ways you can paint the fence.

Note:
n and k are non-negative integers.

Example:

Input: n = 3, k = 2
Output: 6
Explanation: Take c1 as color 1, c2 as color 2. All possible ways are:

            post1  post2  post3      
 -----      -----  -----  -----       
   1         c1     c1     c2 
   2         c1     c2     c1 
   3         c1     c2     c2 
   4         c2     c1     c1  
   5         c2     c1     c2
   6         c2     c2     c1
   
*/

/*   
Interesting problem ...
To deal with such problems, best is to come up with the generic recusrive equation first and then take care of the 
base cases and we are done.

Assuming we want to paint fence i, two cases:
i) it's color is different than the previous fence color.
ii) it's color is same as the previous color. (but here we need to make sure that last 2 colors are not same.

i.e., f[i] = different(f[i - 1]) + same(f[i - 1])
different(f[i - 1]) = f[i - 1] * (k - 1).
same(f[i - 1]) = (only the cases where last 2 are different) * 1, and

the cases where last 2 are different = f[i - 2] * (k - 1)

Therefore, f[i] = f[i - 1] * (k - 1) + f[i - 2] * (k - 1) 
                = (f[i - 1] + f[i - 2]) * (k - 1).

Note: Whenever in the recurrence relation we see that the current depends only on last 2 or three, we don't really need
to maintain a full array. Only those many variables are enough. See the second solution below.
*/

class Solution {
    public int numWays(int n, int k) {
        if(n == 0) return 0;
        if(n == 1) return k;
        if(n == 2) return k * k;
        int[] f = new int[n + 1];
        f[1] = k; f[2] = k * k;
        for(int i = 3; i <= n; i++) f[i] = (f[i - 2] + f[i - 1]) * (k - 1);
        return f[n];
    }
}

// With last 2 variables only ...

class Solution {
    public int numWays(int n, int k) {
        if(n == 0) return 0;
        if(n == 1) return k;
        if(n == 2) return k * k;        
        int prevPrev = k, prev = k * k;
        int current = 0;
        for(int i = 3; i <= n; i++) {
            current = (prevPrev + prev) * (k - 1);
            prevPrev = prev;
            prev = current;
        } 
        return current;
    }
}
   
   
   
