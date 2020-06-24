/*
https://leetcode.com/problems/word-ladder/

127. Word Ladder
Medium

3005

1142

Add to List

Share
Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list.
Note:

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: 0

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
*/

// Using BFS Shortest Distance...
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<String> Q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Q.add(beginWord);
        visited.add(beginWord);
        int level = 0;
        while (!Q.isEmpty()) {
            for (int i = Q.size(); i > 0; i--) {
                String curr = Q.remove();
                if (curr.equals(endWord)) return level + 1;
                for (String adj : getAdjacent(curr, wordList)) {
                    if (!visited.contains(adj)) {
                        Q.add(adj);
                        visited.add(adj);
                    }
                }
            }
            level++;
        }
        return 0;
    }
    
    List<String> getAdjacent(String curr, List<String> wordList) {
        List<String> result = new ArrayList<>();
        for (String word : wordList) {
            if (isOneDistAway(curr, word)) result.add(word);
        }
        return result;
    }
    
    boolean isOneDistAway(String curr, String word) {
        if (curr.length() != word.length()) return false;
        int diff = 0;
        for (int i = 0; i < curr.length(); i++) {
            char char1 = curr.charAt(i);
            char char2 = word.charAt(i);
            if (char1 == char2) continue;
            else diff++;
            if (diff > 1) return false;
        }
        return true;
    }    
}

// Preprocess to find the adjacent words for any word.
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String, ArrayList<String>> graph = new HashMap<>();
        for (String word : wordList) {
            for (int i = 0; i < word.length(); i++) {
                String s = word.substring(0, i) + "*" + word.substring(i + 1);
                graph.computeIfAbsent(s, x -> new ArrayList<String>()).add(word);
            }
        }
        Queue<String> Q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        Q.add(beginWord);
        int dist = 1;
        while (!Q.isEmpty()) {
            for (int len = Q.size(); len > 0; len--) {
                String curr = Q.remove();
                if (curr.equals(endWord)) return dist;
                for (int i = 0; i < curr.length(); i++) {
                    String s = curr.substring(0, i) + "*" + curr.substring(i + 1);
                    for (String adj : graph.getOrDefault(s, new ArrayList<String>())) {
                        if (!visited.contains(adj)) {
                            visited.add(adj);
                            Q.add(adj);
                        }
                    }
                }    
            }
            dist++;
        }
        return 0;
    }    
}
