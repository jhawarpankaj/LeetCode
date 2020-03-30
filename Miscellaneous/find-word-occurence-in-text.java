/*
https://leetcode.com/problems/index-pairs-of-a-string/

Given a text string and words (a list of strings), return all index pairs [i, j] so that the substring text[i]...text[j] is in the list of words.

Example 1:

Input: text = "thestoryofleetcodeandme", words = ["story","fleet","leetcode"]
Output: [[3,7],[9,13],[10,17]]
Example 2:

Input: text = "ababa", words = ["aba","ab"]
Output: [[0,1],[0,2],[2,3],[2,4]]
Explanation: 
Notice that matches can overlap, see "aba" is found in [0,2] and [2,4].
*/

class TrieNode {
    TrieNode[] child;
    boolean isEnd;
    TrieNode() {
        child = new TrieNode[26];        
    }
}

class Solution {
    
    public int[][] indexPairs(String text, String[] words) {
        
        TrieNode root = new TrieNode();
        
        // build Trie...
        for(String s : words) {
            TrieNode curr = root;
            for(char c : s.toCharArray()) {
                int index = c - 'a';
                if(curr.child[index] == null) curr.child[index] = new TrieNode();
                curr = curr.child[index];
            }
            curr.isEnd = true;
        }        
        List<int[]> result = new ArrayList();
        
        // search...
        for(int i = 0; i < text.length(); i++) {
            int index = text.charAt(i) - 'a';
            if(root.child[index] == null) continue;
            search(root, text, i, text.length() - 1, result);            
        }
        
        // result logistics...
        int[][] ans = new int[result.size()][2];
        for(int i = 0; i < ans.length; i++) {
            ans[i] = result.get(i);
        }
        return ans;
    }
    
    void search(TrieNode curr, String text, int start, int end, List<int[]> result) {
        int i = start;     
        while(i <= end && curr.child[text.charAt(i) - 'a'] != null) {
            curr = curr.child[text.charAt(i) - 'a'];
            if(curr.isEnd) result.add(new int[]{start, i});
            i++;
        }     
    }
}
