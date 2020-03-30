/*
https://leetcode.com/problems/longest-word-in-dictionary/

Given a list of strings words representing an English Dictionary, find the longest word in words that can be built one character at a time by other words in words. If there is more than one possible answer, return the longest word with the smallest lexicographical order.

If there is no answer, return the empty string.
Example 1:
Input: 
words = ["w","wo","wor","worl", "world"]
Output: "world"
Explanation: 
The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
*/

/*
The question asks to find the lexicographically longest word whose all prefixes are present. 
1. We build a trie with each word in the string.
2. While recursing we check if each level is marked isEnd or not? If not, this word cannot be part of answer.
*/
class TrieNode {
    TrieNode[] child;
    boolean isEnd;
    TrieNode() {
        child = new TrieNode[26];
    }
}

class Solution {
    public String longestWord(String[] words) {
        TrieNode root = new TrieNode();
        root.isEnd = true;
        String result = "";
        
        // build Trie
        for(String s : words) {
            TrieNode curr = root;
            for(int i = 0; i < s.length(); i++) {
                int index = s.charAt(i) - 'a';
                if(curr.child[index] == null) curr.child[index] = new TrieNode();
                curr = curr.child[index];
            }
            curr.isEnd = true;
        }
        
        // search
        for(String s : words) {
            if(s.length() < result.length() || 
               (s.length() == result.length() && s.compareTo(result) > 0)) continue;
            TrieNode curr = root;
            boolean flag = false;
            for(char c : s.toCharArray()) {
                int index = c - 'a';
                if(!curr.isEnd) {
                    flag = !false;
                    break;
                }
                curr = curr.child[index];
            }
            if(!flag) result = s;
        }
        return result;
    }
}
