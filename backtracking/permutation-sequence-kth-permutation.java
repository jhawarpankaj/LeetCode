/*
Permutation Sequence: https://leetcode.com/problems/permutation-sequence/

The set [1,2,3,...,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order, we get the following sequence for n = 3:

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note:

Given n will be between 1 and 9 inclusive.
Given k will be between 1 and n! inclusive.
Example 1:

Input: n = 3, k = 3
Output: "213"
*/

class Solution {
    public String getPermutation(int n, int k) {
        int fact = 1;
        List<Integer> num = new ArrayList<Integer>();
        for(int i = 1; i <= n; i++) {
            fact = fact * i;
            num.add(i);
        }
        k--; // to handle the index starting at 0.
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            fact = fact / (n - i);
            int index = k / fact;
            sb.append(num.get(index));
            num.remove(index);
            k = k - (index * fact);
        }
        return sb.toString();
    }
}

/*
In case k = 1, it means we need the element in the order they are in the array.
In that case, every time the value of k will be 0, getting the first element always.
*/
