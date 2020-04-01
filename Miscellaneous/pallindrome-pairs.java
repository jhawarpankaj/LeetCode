/*
https://leetcode.com/problems/palindrome-pairs/

Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:

Input: ["abcd","dcba","lls","s","sssll"]
Output: [[0,1],[1,0],[3,2],[2,4]] 
Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
Example 2:

Input: ["bat","tab","cat"]
Output: [[0,1],[1,0]] 
Explanation: The palindromes are ["battab","tabbat"]
*/

class TrieNode {
    TrieNode[] child = new TrieNode[26];
    int index = -1;
}

class Solution {
    
    public List<List<Integer>> palindromePairs(String[] words) {
        
        TrieNode root = new TrieNode();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        // Build Trie ...
        // Add the word from end and while searching start from beginning.
        // Now, while searching, if we are finding a match, it means words are matching from the beginning and end.
        // There can be 3 scenarios: 
        // 1. There was a word exactly reverse of the current word, meaning combination of these 2 will form a pallindrome.
        // 2. There was a word which was reverse of another word + some extra characters.
        // 3. There was a word which was reverse of another word + that word has some more character.
        // We need to handle these 3 cases.
        
        for(int i = 0; i < words.length; i++) {
            String word = words[i];
            TrieNode curr = root;
            for(int j = word.length() - 1; j >= 0; j--) {
                int index = word.charAt(j) - 'a';
                if(curr.child[index] == null) curr.child[index] = new TrieNode();
                curr = curr.child[index];
            }
            curr.index = i;
        }
        
        // Search ...
        for(int i = 0; i < words.length; i++) {
            String word = words[i];
            TrieNode curr = root;
            for(int j = 0; j < word.length(); j++) {
                
                // The current word has not terminated, but there are other words (having same prefix and suffix) terminating.
                // We need to make sure that the rest of current word is a pallindrome then the combination of the two is a pallindrome.
                
                if(curr.index != -1 && isRemPallindrome(word.substring(j))) {
                    result.add(Arrays.asList(i, curr.index));                    
                }
                int index = word.charAt(j) - 'a';
                curr = curr.child[index];
                if(curr == null) break; // means there was no other word whose reverse being equal to the prefix of the current word.
            }
            if(curr == null) continue;
            
            // The current word has terminated and there are other words whose reverse being equal to the prefix of this word.
            // 1. We need to check if the rest of the string of those word are pallinfdrome.
            // 2 Or if there was a word exactly reverse of this word.
            dfs(curr, new StringBuilder(), result, i);            
        }
        return result;
    }
    
    void dfs(TrieNode curr, StringBuilder sb, List<List<Integer>> result, int ind) {
        
        for(int i = 0; i < curr.child.length; i++) {
            if(curr.child[i] != null) {
                char c = (char) (i + 'a');
                sb.append(c);
                dfs(curr.child[i], sb, result, ind);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        
        if(curr.index != -1 && curr.index != ind && isRemPallindrome(sb.toString())) {
            result.add(Arrays.asList(ind, curr.index));
        }

    }
    
    boolean isRemPallindrome(String s) {
        int i = 0, j = s.length() -1;
        while(i < j) {
            if(s.charAt(i) != s.charAt(j)) return false;
            i++; j--;
        }
        return true;
    }
}
