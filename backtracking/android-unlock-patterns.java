/*
Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9, 
count the total number of unlock patterns of the Android lock screen, which consist of minimum of m keys and maximum n keys.

Each pattern must connect at least m keys and at most n keys.
All the keys must be distinct.

If the line connecting two consecutive keys in the pattern passes through any other keys, 
the other keys must have previously selected in the pattern. No jumps through non selected key is allowed.

The order of keys used matters.
*/

/*
The backtracking strategy: Take a node, mark it visited, get its unexplored AND legitimate neighbours, explore from there
now, come-back and mark it unvisited for further explorations.
*/

class Solution {
    int[][] jumps;
    int count = 0;
    public int numberOfPatterns(int m, int n) {
        jumps = new int[10][10];
        jumps[1][3] = jumps[3][1] = 2;
        jumps[4][6] = jumps[6][4] = 5;
        jumps[7][9] = jumps[9][7] = 8;
        jumps[1][7] = jumps[7][1] = 4;
        jumps[2][8] = jumps[8][2] = 5;
        jumps[3][9] = jumps[9][3] = 6;
        jumps[1][9] = jumps[9][1] = jumps[3][7] = jumps[7][3] = 5;
        Set<Integer> set = new HashSet<Integer>();
        set.add(1);
        DFS(1, 1, m, n, set);
        int countA = count;
        set.clear();
        set.add(2);
        DFS(2, 1, m, n, set);
        int countB = count;
        set.clear();
        set.add(5);
        DFS(5, 1, m, n, set);
        int countC = count;
        return countA * 4 + (countB - countA) * 4 + (count - countB);
    }
    
    void DFS(int num, int len, int m, int n, Set<Integer> visited) {
        if(len >= m && len <= n) count++;
        if(len > n) return;
        for(int i = 1; i <= 9; i++) {
            int jump = jumps[num][i];
            if(!visited.contains(i) && (jump == 0 || visited.contains(jump))) {
                visited.add(i);
                len++;
                DFS(i, len, m , n, visited);
                len--;
                visited.remove(i);
            }
        }
    }
}
