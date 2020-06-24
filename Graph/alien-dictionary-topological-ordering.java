/*
https://leetcode.com/problems/alien-dictionary/

There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

Example 1:

Input:
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]

Output: "wertf"
Example 2:

Input:
[
  "z",
  "x"
]

Output: "zx"
Example 3:

Input:
[
  "z",
  "x",
  "z"
] 

Output: "" 

Explanation: The order is invalid, so return "".
Note:

You may assume all letters are in lowercase.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.
*/

class Solution {
    
    int WHITE = -1;
    int GRAY = 0;
    int BLACK = 1;
    
    public String alienOrder(String[] words) {        
        Map<Character, ArrayList<Character>> graph = new HashMap<>();
        Map<Character, Integer> color = new HashMap<>();        
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new ArrayList<>());
                color.putIfAbsent(c, WHITE);
            }
        }        
        // Build the graph, a character is a child of another character, if it appears after a character in the Alien dictionary.
        // Once the graph is build, getting the topological ordering will give us the result.
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            int j = 0;
            for (; j < Math.min(word1.length(), word2.length()); j++) {
                if (word1.charAt(j) == word2.charAt(j)) continue;
                graph.get(word1.charAt(j)).add(word2.charAt(j));
                break;
            }
            // word2 is a prefix of word2 and word1 > word2. Should not be possible, wrong dictionary.
            if (j == word2.length() && word1.length() > word2.length()) return "";
        }         
        // Get Topological Ordering.
        Stack<Character> stack = new Stack<>();        
        for (Character c : graph.keySet()) {
            if (color.get(c) == WHITE && isCyclicDFS(graph, c, stack, color)) return "";            
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }
    
    boolean isCyclicDFS(Map<Character, ArrayList<Character>> graph, char c, Stack<Character> stack, Map<Character, Integer> color) {
        color.put(c, GRAY);
        for (char adj : graph.getOrDefault(c, new ArrayList<Character>())) {
            if (color.get(adj) == GRAY || color.get(adj) == WHITE && isCyclicDFS(graph, adj, stack, color)) return true;
        }
        color.put(c, BLACK);
        stack.push(c);
        return false;
    }
}
