/*
https://leetcode.com/problems/implement-strstr/

Implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Example 1:

Input: haystack = "hello", needle = "ll"
Output: 2

*/

// Brute force

class Solution {
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.isEmpty()) return 0;
        for(int i = 0; i <= haystack.length() - needle.length(); i++){
            if(haystack.charAt(i) == needle.charAt(0)){
                int j = 0;
                for(; j < needle.length(); j++){
                    if(haystack.charAt(i + j) != needle.charAt(j)) break;                    
                }
                if(j == needle.length()) return i;
            }
        }
        return -1;
    }
}


// Using KMP algorithm
class Solution {
    public int strStr(String haystack, String needle) {
        int m = haystack.length(), n = needle.length();        
        if(n == 0) return 0;
        
        // Calculating the failure function using KMP algorithm.
        int[] f = new int[n]; // note that f[0] = 0 by default.
        System.out.print(f[0] + " ");
        
        for(int k = 1; k < n; k++) {
            int x = f[k - 1];
            while(x > 0 && needle.charAt(x) != needle.charAt(k)) {
                x = f[x - 1];
            }
            if(needle.charAt(x) == needle.charAt(k)) f[k] = x + 1;
            else f[k] = 0;
        }
        
        for(int i = 0; i < n; i++) {
            System.out.print(f[i] + " ");
        }
        
        // Iterating the 2 strings. 
        // Note that the haystack string never goes back after it advances.
        
        for(int i = 0, j = 0; i < m;) {
            int temp = i - j;
            while(i < m && j < n && haystack.charAt(i) == needle.charAt(j)) {
                i++; j++;
            }
            if(j == n) return temp;
            if(j == 0) i++;
            if(j > 0) j = f[j - 1];            
        }        
        return -1;
    }
}
