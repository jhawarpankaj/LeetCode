/*
https://leetcode.com/problems/shortest-palindrome/

Given a string s, you are allowed to convert it to a palindrome by adding characters in front of it. 
Find and return the shortest palindrome you can find by performing this transformation.

Example 1:

Input: "aacecaaa"
Output: "aaacecaaa"
Example 2:

Input: "abcd"
Output: "dcbabcd"
*/

// Idea is to find the longest pallindrome starting from 0. The rest of the characters after that are the ones which need to be 
// added to the front.

class Solution {
    public String shortestPalindrome(String s) {
        int n = s.length();
        int i = 0, j = n - 1;
        while(i < j) {
            if (isPallindrome(s, i, j)) break;
            j--;
        }
        String sub = s.substring(j + 1, n);
        String rev = "";
        for (char c : sub.toCharArray()) rev = c + rev;
        return rev + s;
    }
    
    boolean isPallindrome(String s, int i, int j) {
        for (int len = (j - i) / 2; i <= len; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) return false;
        }
        return true;
    }
}

// Using KMP.

class Solution {
    public String shortestPalindrome(String s) {
        // if we find the length of the longest prefix which is also a suffix, we know that the remaining characters are the one to 
        // be added to the front.
        String KMP = s + "#" + new StringBuilder(s).reverse().toString();
        int n = KMP.length();
        
        // building KMP failure function.
        int[] f = new int[n];
        f[0] = 0;
        for (int k = 1; k < n; k++) {
            int x = f[k - 1];
            while (x > 0 && KMP.charAt(x) != KMP.charAt(k)) {
                x = f[x - 1];
            }
            if (KMP.charAt(x) == KMP.charAt(k)) f[k] = x + 1;
        }
        return new StringBuilder(s.substring(f[n - 1], s.length())).reverse().toString() + s;
    }
}
