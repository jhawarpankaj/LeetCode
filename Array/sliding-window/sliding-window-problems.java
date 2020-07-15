/*
Sliding-Window Problems. The problems on sliding-window follow the same approach.

i) Both l and h start from the same position. 
ii) h is the leading pointer and l is the lagging one. h always moves ahead satisfying the given constraint of the problem. And whenever the constraint breaks, h stops for a while, 
and l will move to the right place (and that would be < h) where the constraint is satisfied again.

Optimizations: Now the only place where these problems can be optimized is by moving l efficiently using additional space in form of Queue, HashMap, Set etc.
*/

/*
Problem 1: https://leetcode.com/problems/max-consecutive-ones-iii/
Given an array A of 0s and 1s, we may change up to K values from 0 to 1.
Return the length of the longest (contiguous) subarray that contains only 1s. 

Example 1:
Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
Output: 6
Explanation: 
[1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.

Example 2:
Input: A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
Output: 10
Explanation: 
[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.

Note:
1 <= A.length <= 20000
0 <= K <= A.length
A[i] is 0 or 1 
*/

// Iteratively moving left pointer to maintain the constraint of K.
class Solution {
    public int longestOnes(int[] A, int K) {
        int max = 0, count = 0;
        for (int l = 0, h = 0; h < A.length; h++) {
            if (A[h] == 0) count++;
            while (count > K) {
                if (A[l++] == 0) count--;                
            }
            max = Math.max(max, h - l + 1);
        }
        return max;
    }
}

// Efficiently jumping the left pointer to maintain the constraint of K.
class Solution {
    public int longestOnes(int[] A, int K) {
        int max = 0;
        Queue<Integer> Q = new LinkedList<>();
        for (int l = 0, h = 0; h < A.length; h++) {
            if (A[h] == 0) Q.add(h);            
            if (Q.size() > K) l = Q.remove() + 1;
            max = Math.max(max, h - l + 1);
        }
        return max;
    }
}

/*
Problem 2: https://leetcode.com/problems/longest-substring-without-repeating-characters/
Given a string, find the length of the longest substring without repeating characters.

Example 1:
Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. 
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
*/
// Iteratively moving left pointer.
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null) return 0;
        int max = 0;
        Set<Character> set = new HashSet<>();
        for (int l = 0, h = 0; h < s.length(); h++) {
            char c = s.charAt(h);
            while (set.contains(c)) {
                set.remove(s.charAt(l++));
            }
            set.add(c);
            max = Math.max(max, h - l + 1);
        }
        return max;
    }
}

// Jumping left directly.
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null) return 0;
        int max = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int l = 0, h = 0; h < s.length(); h++) {
            char c = s.charAt(h);
            if (map.containsKey(c)) l = Math.max(l, map.get(c) + 1);
            map.put(c, h);
            max = Math.max(max, h - l + 1);
        }
        return max;
    }
}

/*
Problem 3: https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/

Given a string, find the length of the longest substring T that contains at most k distinct characters.
Example 1:
Input: s = "eceba", k = 2
Output: 3
Explanation: T is "ece" which its length is 3.

Example 2:
Input: s = "aa", k = 1
Output: 2
Explanation: T is "aa" which its length is 2.
*/

// Iteratively remove all left elements.
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null) return 0;
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        for (int l = 0, h = 0; h < s.length(); h++) {
            char c1 = s.charAt(h);
            map.put(c1, map.getOrDefault(c1, 0) + 1);
            // keep removing elements until map.size() == k
            while (map.size() > k) {
                char c2 = s.charAt(l++);
                map.put(c2, map.get(c2) - 1);
                if (map.get(c2) == 0) map.remove(c2);
            }
            max = Math.max(max, h - l + 1);
        }
        return max;
    }
}

// Efficiently jump left pointer, using LinkedHashMap.
// Idea is when map.size() > k, getting the minimum index from all elements in the map and removing that will maintain the constraint again.
// A LinkedHashMap is used to efficiently jump the left pointer, as it will maintain the order of insertion.
// The first element will be the minimum, as we remove the element in case of duplicate and insert it again.
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null) return 0;
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        int max = 0;        
        for (int l = 0, h = 0; h < s.length(); h++) {
            char c = s.charAt(h);
            if (map.containsKey(c)) map.remove(c);
            map.put(c, h);
            if (map.size() > k) {
                char leftMin = map.keySet().iterator().next();
                l = map.get(leftMin) + 1;
                map.remove(leftMin);
            }
            max = Math.max(max, h - l + 1);
        }
        return max;
    }
}

/*
Problem 4: https://leetcode.com/problems/container-with-most-water/

Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at 
(i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.

Example:
Input: [1,8,6,2,5,4,8,3,7]
Output: 49
*/

class Solution {
    public int maxArea(int[] height) {
        int max = 0;
        for (int l = 0, h = height.length - 1; l < h; ) {
            // Moving the smaller one forward may find a maximum area, moving the bigger one will
            // always be bottlenecked by the smaller one.
            if (height[l] < height[h]) {
                max = Math.max(max, height[l] * (h - l));
                l++;
            } else {
                max = Math.max(max, height[h] * (h - l));
                h--;
            }
        }
        return max;
    }
}
