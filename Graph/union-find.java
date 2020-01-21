/*

https://www.hackerrank.com/contests/hack-it-to-win-it-paypal/challenges/q4-traveling-is-fun

Sample Input 0
6 # n cities
0 # GCD (g)
4 # Next 4 origin cities
1
4
3
6
4 # Next 4 destination cities
3
6
2
5
Sample Output 0
1
1
1
1

originCities[] = [1, 4, 3, 6]
destinationCities = [3, 6, 2, 5]
Constraint: There exists a path from city X to city Y, if GCD(X, Y) > g.
Output: If there is a path from {1, 3}, {4, 6} and {6, 5} -> [1, 1, 0, 1]

*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static int[] connectedCities(int n, int g, int[] originCities, int[] destinationCities) {
        int[] root = new int[n + 1];
        int[] rank = new int[n + 1];
        for(int i = 1; i <= n; i++){
            root[i] = i;
            rank[i] = 1;
        }
        
        for(int i = g + 1; i <= n; i++){
            for(int j = 2 * i; j <= n; j = j + i){
                union(root, rank, i, j);
            }
        }
        int[] result = new int[originCities.length];
        for(int i = 0; i < originCities.length; i++){
            result[i] = root[originCities[i]] == root[destinationCities[i]] ? 1 : 0;
        }
        return result;
    }
    
    public static void union(int[] root, int[] rank, int a, int b){
        int aRoot = find(root, a);
        int bRoot = find(root, b);
        if(aRoot == bRoot) return;
        
        // Below code used for union-by-rank, the one with smaller rank 
        // attaches to the one with higher rank.
        
        // If A's rank is lesser than B, then A attaches to B,
        // and rank of B increases.
        if(rank[aRoot] < rank[bRoot]){
            root[aRoot] = root[bRoot];
            rank[bRoot] += rank[aRoot];
        }else{
            root[bRoot] = root[aRoot];
            rank[aRoot] += rank[bRoot];
        }
    }
    
    public static int find(int[] root, int i){
        while(root[i] != i){
            root[i] = root[root[i]]; // used for path compression.
            i = root[i];
        }
        return i;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int g = in.nextInt();
        int originCities_cnt = in.nextInt();
        int[] originCities = new int[originCities_cnt];
        for(int originCities_i = 0; originCities_i < originCities_cnt; originCities_i++){
            originCities[originCities_i] = in.nextInt();
        }
        int destinationCities_cnt = in.nextInt();
        int[] destinationCities = new int[destinationCities_cnt];
        for(int destinationCities_i = 0; destinationCities_i < destinationCities_cnt; destinationCities_i++){
            destinationCities[destinationCities_i] = in.nextInt();
        }
        int[] res = connectedCities(n, g, originCities, destinationCities);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + (i != res.length - 1 ? "\n" : ""));
        }
        System.out.println("");


        in.close();
    }
}

// Note the code will work without the 2 optimizations as well but complexity will be higher specially
// in case of a dense graph.

// The above solution doesn't pass all test cases probably because of the find operation.
// Below solution pass all test cases

static int[] connectedCities(int n, int g, int[] originCities, int[] destinationCities) {
        int[] result = new int[originCities.length];
        if(g >= n) return result;
        if(g == 0) {
            Arrays.fill(result, 1);
            return result;
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = g + 1; i <= n; i++) map.put(i, i);
        for(int i = g + 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            List<Integer> temp = new ArrayList<Integer>();
            for(int j = i; j <= n; j = j + i) {
                int current = map.get(j);
                min = Math.min(min, current);
                temp.add(j);
            }
            for(Integer x : temp) map.put(x, min);
        }
        for(int i = 0; i < originCities.length; i++) {
            if(originCities[i] > g && destinationCities[i] > g && map.get(originCities[i]).equals(map.get(destinationCities[i]))) result[i] = 1;
            else result[i] = 0;
        }
        return result;
    }
