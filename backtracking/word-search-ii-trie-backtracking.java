/*
https://leetcode.com/problems/word-search-ii/

Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

Example:

Input: 
board = [
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','a'],
  ['i','f','l','o']
]
words = ["oath","pea","eat","rain"]

Output: ["eat","oath"]
*/

/*
1. We form a Trie with the given words array. At the leaf of Trie, we store the word.
2. We iterate the 2-D board and if it matches any of the Trie root, we go for a dfs.
3. Once a match happens, we remove the word from the leaf, so that it doesn't get picked again.

Note: An optimization can be performed on the Trie to prune the nodes from leaf to root, once we reach the end. Note that this pruning won't impact any future searches. For example, say we wanted to search 'oae' in the board. The leaves won't be pruned from the first 'o' as it will never reach the leaf from there. When the second 'o' is encountered, then it will be added to result first and then pruned.
*/

class Solution {
    
    class TrieNode {
        TrieNode[] child;
        String word;
        public TrieNode() {
            child = new TrieNode[26];
            word = null;
        }
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        TrieNode root = new TrieNode();
        buildTrie(root, words);
        
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                int index = board[i][j] - 'a';
                if(root.child[index] != null) dfs(board, i, j, root, result);
            }
        }        
        return result;        
    }
    
    void dfs(char[][] board, int i, int j, TrieNode curr, List<String> result) {
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length) return;
        char c = board[i][j];
        if(c == '#') return;
        curr = curr.child[c - 'a'];
        if(curr == null) return;        
        if(curr.word != null) {
            result.add(curr.word);
            curr.word = null;
        }
        board[i][j] = '#';
        dfs(board, i + 1, j, curr, result);
        dfs(board, i - 1, j, curr, result);
        dfs(board, i, j - 1, curr, result);
        dfs(board, i, j + 1, curr, result);
        board[i][j] = c;
    }
    
    void buildTrie(TrieNode root, String[] words) {
        
        for(int i = 0; i < words.length; i++) {
            TrieNode curr = root;
            for(char c : words[i].toCharArray()) {
                if(curr.child[c - 'a'] == null) {
                    curr.child[c - 'a'] = new TrieNode();
                    curr = curr.child[c - 'a'];
                }
                else {
                    curr = curr.child[c - 'a'];
                }
            }
            curr.word = words[i];
        }
    }
}
