/*
https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/

Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.

Example 1:
Input:
s = "aaabb", k = 3
Output:
3
The longest substring is "aaa", as 'a' is repeated 3 times.

Example 2:
Input:
s = "ababbc", k = 2
Output:
5

The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
*/

/*
Note that unlike other sliding-window problems in this problem we cannot move h to the right and also we cannot know when to move l from left because the 
constraint of the sliding window is not linear.
So we first tried to impose a constraint of the window by maintaining a unique size all the time
*/

class Solution {
    public int longestSubstring(String s, int k) {
        int n = s.length();
        int max = 0;
        for (int i = 1; i <= 26; i++) {
            int l = 0, h = 0;
            int unique = 0, countK = 0;
            Map<Character, Integer> map = new HashMap<>();
            while (h < n) {
                char c = s.charAt(h++);
                map.put(c, map.getOrDefault(c, 0) + 1);
                if (map.get(c) == 1) unique++;
                if (map.get(c) == k) countK++;
                while (unique > i) { // when constraint breaks.
                    char c2 = s.charAt(l++);
                    map.put(c2, map.get(c2) - 1);
                    if (map.get(c2) == 0) unique--;
                    if (map.get(c2) == k - 1) countK--;
                }
                if (unique == i && countK == i) { // if constraint satisfied or restored.
                    max = Math.max(max, h - l);
                }
            }
        }
        return max;
    }
}
