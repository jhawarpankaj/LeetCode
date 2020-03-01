// Hackerrank: https://www.hackerrank.com/challenges/components-in-graph/problem

// Idea is to call DFS for all the vertices of the graph. Ech DFS call will cover one connected component. 
// So the no of connected components is equal to the number of times DFS is called.  

// Also note that connected component is used in terms of disconnected graph while strongly connected component in case of a
// directed graph.

// Problem is attached as PDF.

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    /*
     * Complete the componentsInGraph function below.
     */
    static int[] componentsInGraph(int[][] gb) {
        Map<Integer, ArrayList<Integer>> adj = new HashMap<Integer, ArrayList<Integer>>();
        for(int i = 0; i < gb.length; i++) {
            ArrayList<Integer> temp1 = adj.getOrDefault(gb[i][0], new ArrayList<Integer>());
            ArrayList<Integer> temp2 = adj.getOrDefault(gb[i][1], new ArrayList<Integer>());
            temp1.add(gb[i][1]);
            temp2.add(gb[i][0]);
            adj.put(gb[i][0], temp1);
            adj.put(gb[i][1], temp2);
        }
        Set<Integer> visited = new HashSet<Integer>();
        List<Integer> vertices = new ArrayList<Integer>(adj.keySet());
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for(Integer v : vertices) {
            int comp = 0;
            if(!visited.contains(v)) {
                stack.push(v);
                visited.add(v);
                while(!stack.isEmpty()) {
                    int top = stack.pop();
                    comp++;
                    for(Integer neigh : adj.get(top)) {
                       if(!visited.contains(neigh)) {
                           visited.add(neigh);
                           stack.push(neigh);                           
                       }
                    }
                }
                // int comp = DFS(v, visited, adj);
                min = Math.min(min, comp);
                max = Math.max(max, comp);
            }
        }
        int[] result = new int[2];
        result[0] = min;
        result[1] = max;
        return result;

    }

    static int DFS(Integer node, Set<Integer> visited, Map<Integer, ArrayList<Integer>> adj) {
        visited.add(node);
        int count = 0;
        for(Integer neigh : adj.get(node)) {            
            if(!visited.contains(neigh)) {
                // System.out.println("Neigh: " + neigh);
                count += 1 + DFS(neigh, visited, adj);
            }
        }
        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(scanner.nextLine().trim());

        int[][] gb = new int[n][2];

        for (int gbRowItr = 0; gbRowItr < n; gbRowItr++) {
            String[] gbRowItems = scanner.nextLine().split(" ");

            for (int gbColumnItr = 0; gbColumnItr < 2; gbColumnItr++) {
                int gbItem = Integer.parseInt(gbRowItems[gbColumnItr].trim());
                gb[gbRowItr][gbColumnItr] = gbItem;
            }
        }

        for(int i = 0; i < gb.length; i++) {
            for(int j = 0; j < gb[0].length; j++){
                System.out.print(gb[i][j]);
            }
            System.out.println();
        }

        int[] SPACE = componentsInGraph(gb);
        
        for (int SPACEItr = 0; SPACEItr < SPACE.length; SPACEItr++) {
            bufferedWriter.write(String.valueOf(SPACE[SPACEItr]));

            if (SPACEItr != SPACE.length - 1) {
                bufferedWriter.write(" ");
            }
        }

        bufferedWriter.newLine();
        bufferedWriter.close();
    }
}
