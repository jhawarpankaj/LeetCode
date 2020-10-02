/*
https://leetcode.com/problems/h-index-ii/
Given an array of citations sorted in ascending order (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's 
h-index.
According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other 
N − h papers have no more than h citations each."

Example:
Input: citations = [0,1,3,5,6]
Output: 3 
Explanation: [0,1,3,5,6] means the researcher has 5 papers in total and each of them had 
             received 0, 1, 3, 5, 6 citations respectively. 
             Since the researcher has 3 papers with at least 3 citations each and the remaining 
             two with no more than 3 citations each, her h-index is 3.
Note:

If there are several possible values for h, the maximum one is taken as the h-index.
Follow up:
This is a follow up problem to H-Index, where citations is now guaranteed to be sorted in ascending order.Could you solve it in logarithmic time complexity?
*/

class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int l = 0, h = n - 1;
        int ans = 0;
        while (l <= h) {
            int m = (l + h) >>> 1;
            if (citations[m] >= n - m) { // understand what n - m tells? it gives no of papers having citations >= citations[m]
                // this is a probable answer but there can be better answers which can be found by moving h to m - 1;
                // understand why moving to left can find us better answers?
                ans = n - m; 
                h = m - 1;
            } else l = m + 1;
        }
        return ans;
    }
}

/*
https://leetcode.com/problems/h-index/

Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.
According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other 
N − h papers have no more than h citations each."

Example:

Input: citations = [3,0,6,1,5]
Output: 3 
Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had 
             received 3, 0, 6, 1, 5 citations respectively. 
             Since the researcher has 3 papers with at least 3 citations each and the remaining 
             two with no more than 3 citations each, her h-index is 3.
Note: If there are several possible values for h, the maximum one is taken as the h-index.
*/
// Solution1: Sort the given array and apply binary search as above.
// Solution2: Use Bucket Sort.

class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] buckets = new int[n + 1];
        for (int c : citations) {
            if (c >= n) buckets[n]++;
            else buckets[c]++;
        }
        int count = 0;
        for (int i = n; i >= 0; i--) {
            count += buckets[i];
            if (count >= i) return i;
        }
        return 0;
    }
}
