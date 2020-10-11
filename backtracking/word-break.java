/*
https://leetcode.com/problems/word-break/

Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of 
one or more dictionary words.

Note:
The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.

Example 1:
Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".

Example 2:
Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.
             
Example 3:
Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false
*/

// Backtracking.
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        return bt(s, 0, set);
    }    
    boolean bt(String s, int ind, Set<String> set) {
        if (ind == s.length()) return true;
        for (int i = ind; i < s.length(); i++) {
            String word = s.substring(ind, i + 1);
            if (!set.contains(word)) continue;
            if (bt(s, i + 1, set)) return true;             
        }
        return false;
    }
}

// Using Dynamic Programming.
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        int n = s.length();
        Boolean[] dp = new Boolean[n];
        return bt(s, 0, set, dp);
    }    
    boolean bt(String s, int start, Set<String> set, Boolean[] dp) {
        if (start == s.length()) return true;
        if (dp[start] != null) return dp[start];
        for (int i = start; i < s.length(); i++) {
            if (set.contains(s.substring(start, i + 1)) && bt(s, i + 1, set, dp)) {
                return dp[i] = true; // note that this true is never used. When this happens chain of trues are returned.
            }
        }
        return dp[start] = false;
    }
}

// Using BFS. Idea is similar to above, it's sort of converting the recursion to iterative version.
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        Queue<Integer> Q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        Q.add(0);
        visited.add(0);
        while (!Q.isEmpty()) {
            int ind = Q.remove();
            if (ind == s.length()) return true;
            for (int i = ind; i < s.length(); i++) {
                if (!visited.contains(i + 1) && set.contains(s.substring(ind, i + 1))) {
                    visited.add(i + 1);
                    Q.add(i + 1);
                }
            }
        }
        return false;
    }
}

/*
https://leetcode.com/problems/word-break-ii/
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a 
valid dictionary word. Return all such possible sentences.

Note:
The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.

Example 1:
Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]

Example 2:
Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.

Example 3:
Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]
*/

// Finding all solution. Recall that it just follows the recursion + DP strategy. Fix the return type and then what to expect from top when you find a word in 
// dict. 
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        return dfs(s, 0, set, new HashMap<Integer, List<String>>());
    }
    
    List<String> dfs(String s, int ind, Set<String> wordDict, HashMap<Integer, List<String>> map) {
        if (ind == s.length()) return Arrays.asList("");
        if (map.containsKey(ind)) return map.get(ind);
        List<String> here = new ArrayList<>();
        for (int i = ind; i < s.length(); i++) {
            String word = s.substring(ind, i + 1);
            if (!wordDict.contains(word)) continue;
            List<String> upper = dfs(s, i + 1, wordDict, map);            
            for (String str : upper) {
                if (str.isEmpty()) here.add(word);
                else here.add(word + " " + str);
            }
        }
        map.put(ind, here);
        return here;
    }
}
