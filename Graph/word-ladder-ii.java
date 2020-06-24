/*
https://leetcode.com/problems/word-ladder-ii/

Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

Only one letter can be changed at a time
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return an empty list if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output:
[
  ["hit","hot","dot","dog","cog"],
  ["hit","hot","lot","log","cog"]
]
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: []
Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
*/

// First, we need to find the shortest distance to the goal node.
// Second, as in BFS we do not return back to the original caller, we cannot get the path when we reach the goal. We use a parent pointer for each node to
// construct the path back once we find goal. Note that a node may have more than one parent.
// Third, we can observe that we can DFS the parent map from endWord to search for beginWord and with this DFS we can construct the path in reverse.
// Lastly, as we need to construct all paths, we need to BACKTRACK instead of DFS in above step.

// However, this is not an optimal solution and result in TLE. See Leetcode Discussion for some optimal solutions. 
// But this solution is very important to understand DFS, BFS and BACTRACKING.

// First, we need to find the shortest distance to the goal node.
// Second, as in BFS we do not return back to the original caller, we cannot get the path when we reach the goal. We use a parent pointer for each node to
// construct the path back once we find goal. Note that a node may have more than one parent.
// Third, we can observe that we can DFS the parent map from endWord to search for beginWord and with this DFS we can construct the path in reverse.
// Lastly, as we need to construct all paths, we need to BACKTRACK instead of DFS in above step.

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        Map<String, ArrayList<String>> graph = new HashMap<>();
        for (String word : wordList) {
            for (int i = 0; i < word.length(); i++) {
                String s = word.substring(0, i) + "*" + word.substring(i + 1);
                graph.computeIfAbsent(s, x -> new ArrayList<String>()).add(word);
            }
        }
        Map<String, ArrayList<String>> parent = new HashMap<>();
        Set<String> visited = new HashSet<>();
        Queue<String> Q = new LinkedList<>();
        Q.add(beginWord);
        parent.computeIfAbsent(beginWord, x -> new ArrayList<String>()).add(beginWord);
        visited.add(beginWord);
        boolean flag = false;
        int dist = 1;
        while (!Q.isEmpty()) {
            for (int len = Q.size(); len > 0; len--) {
                String curr = Q.remove();
                if (curr.equals(endWord)) {
                    flag = true;
                    break;
                }
                for (int i = 0; i < curr.length(); i++) {
                    String s = curr.substring(0, i) + "*" + curr.substring(i + 1);
                    for (String adj : graph.getOrDefault(s, new ArrayList<String>())) {
                        if (!visited.contains(adj)) {
                            Q.add(adj);
                            visited.add(adj);
                        }
                        parent.computeIfAbsent(adj, x -> new ArrayList<String>()).add(curr);
                    }
                }
            }            
            if (flag) break;
            dist++;
        }
        if (!flag) return result;
        visited.clear();
        
        DFS(parent, beginWord, endWord, result, visited, new ArrayList<String>(), dist);        
        return result;
    }
    
    void DFS(Map<String, ArrayList<String>> parent, String beginWord, String currWord, List<List<String>> result, Set<String> visited, List<String> temp, int shortestDist) {
        if (currWord.equals(beginWord)) {            
            List<String> sub = new ArrayList<>();
            sub.add(beginWord);
            for (int i = temp.size() - 1; i >= 0; i--) sub.add(temp.get(i));
            result.add(sub);
            return;
        }
        // -1 as first elem is the beginWord.
        if (temp.size() >= shortestDist - 1) return;
        temp.add(currWord);
        visited.add(currWord);
        for (String adj : parent.get(currWord)) {
            if (!visited.contains(adj)) DFS(parent, beginWord, adj, result, visited, temp, shortestDist);
        }
        temp.remove(temp.size() - 1);
        visited.remove(currWord);        
    }
}
