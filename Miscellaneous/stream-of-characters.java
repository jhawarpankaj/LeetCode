/*
https://leetcode.com/problems/stream-of-characters/

Implement the StreamChecker class as follows:

StreamChecker(words): Constructor, init the data structure with the given words.
query(letter): returns true if and only if for some k >= 1, the last k characters queried (in order from oldest to newest, 
including this letter just queried) spell one of the words in the given list.
 
Example:

StreamChecker streamChecker = new StreamChecker(["cd","f","kl"]); // init the dictionary.
streamChecker.query('a');          // return false
streamChecker.query('b');          // return false
streamChecker.query('c');          // return false
streamChecker.query('d');          // return true, because 'cd' is in the wordlist
streamChecker.query('e');          // return false
streamChecker.query('f');          // return true, because 'f' is in the wordlist
streamChecker.query('g');          // return false
streamChecker.query('h');          // return false
streamChecker.query('i');          // return false
streamChecker.query('j');          // return false
streamChecker.query('k');          // return false
streamChecker.query('l');          // return true, because 'kl' is in the wordlist
*/

/*
1. The trick is to enter the words in reverse order. 
2. While searching append the characters to a string, and then search from reverse.
*/

class StreamChecker {
    
    TrieNode root;
    int max;
    StringBuilder sb;
    
    class TrieNode {
        TrieNode[] child = new TrieNode[26];
        boolean isEnd;
    }
    
    public StreamChecker(String[] words) {
        root = new TrieNode();
        max = 0;
        sb = new StringBuilder();
        for(String word : words) {
            addToTrie(root, new StringBuilder(word).reverse().toString());
            max = Math.max(max, word.length());
        }
    }
    
    void addToTrie(TrieNode curr, String word) {
        for(char c : word.toCharArray()) {
            int index = c - 'a';
            if(curr.child[index] == null) curr.child[index] = new TrieNode();
            curr = curr.child[index];
        }
        curr.isEnd = true;
    }
    
    public boolean query(char letter) {
        sb.append(letter);
        if(sb.length() > max) sb.deleteCharAt(0);
        TrieNode curr = root;
        char[] cArr = sb.toString().toCharArray();
        for(int i = cArr.length - 1; i >= 0; i--) {
            if(curr.isEnd) return true;
            int index = cArr[i] - 'a';
            if(curr.child[index] == null) return false;
            curr = curr.child[index];
        }
        return curr.isEnd ? true : false;
    }
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */
