/*
https://leetcode.com/problems/unique-binary-search-trees/

Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?

Example:

Input: 3
Output: 5
Explanation:
Given n = 3, there are a total of 5 unique BST's:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
   
*/

class Solution {
    public int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = 1; G[1] = 1;
        for(int i = 2; i <= n; i++){
            for(int j = 1; j <= i; j++){
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }
}

/*
1. How many trees can be built with 1 no, 2 no, 3 no etc.
2. It doesn't matter what are the nos, i.e., [1, 2, 3] or [4, 5, 6] will produce the same no of tree.
3. For i = 5 (say),
    a. Unique trees are when 1 was root + when 2 was root ... + when 5 will be root.
    b. When 3 will be root (say): It will have 2 elements: [1, 2] as the left children and [4, 5] as the right children.
    c. We already know the count of that, i.e, left has 2 children and right has 2 children, i.e., 
    with 3 as root total 2 * 2 = 4 trees possible.
*/
