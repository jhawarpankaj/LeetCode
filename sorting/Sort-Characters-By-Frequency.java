/*
https://leetcode.com/problems/sort-characters-by-frequency/

Given a string, sort it in decreasing order based on the frequency of characters.
Example 1:
Input:
"tree"

Output:
"eert"

Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
Example 2:

Input:
"cccaaa"

Output:
"cccaaa"

Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.
Example 3:

Input:
"Aabb"

Output:
"bbAa"

Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.
*/

// Approach 1 (Desi Method, do what the problem says)

class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        TreeMap<Integer, TreeSet<Character>> rMap = new TreeMap<>((a, b) -> {
           return b.compareTo(a);
        });
        for (char key : map.keySet()) {
            rMap.computeIfAbsent(map.get(key), x -> new TreeSet<>()).add(key);
        }
        StringBuilder sb = new StringBuilder();
        for (int key : rMap.keySet()) {
            for (char c : rMap.get(key)) append(sb, c, key);
        }
        return sb.toString();
    }
    
    void append(StringBuilder sb, char c, int count) {
        for (int i = 0; i < count; i++) sb.append(c);
    }
}

// Approach 2, Keep an eye on this: Sorting map's keys based on its values.

class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        List<Character> list = new ArrayList<>(map.keySet());
        list.sort((a, b) -> {
            return map.get(b) - map.get(a);
        });
        StringBuilder sb = new StringBuilder();
        for (char c : list) {
            append(sb, c, map.get(c));
        }
        return sb.toString();
    }
    
    void append(StringBuilder sb, char c, int count) {
        while (count > 0) {
            sb.append(c);
            count--;
        }
    }
}

// Approach 3 (Bucket Sort)

class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int max = Integer.MIN_VALUE;
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
            max = Math.max(max, map.get(c));
        }
        List<List<Character>> buckets = new ArrayList<>();
        for (int i = 0; i <= max; i++) {
            buckets.add(new ArrayList<>());
        }
        for (char key : map.keySet()) {
            buckets.get(map.get(key)).add(key);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = buckets.size() - 1; i >= 0; i--) {
            List<Character> bucket = buckets.get(i);
            if (bucket.isEmpty()) continue;
            for (char c : bucket) append(sb, c, i);
        }
        return sb.toString();
    }
    
    void append(StringBuilder sb, char c, int count) {
        while (count > 0) {
            sb.append(c);
            count--;
        }
    }
}
