/*
https://community.topcoder.com/stat?c=problem_statement&pm=1889&rd=4709

In the city, roads are arranged in a grid pattern. Each point on the grid represents a corner where two blocks meet. 
The points are connected by line segments which represent the various street blocks. Using the cartesian coordinate system, we can
assign a pair of integers to each corner as shown below.

You are standing at the corner with coordinates 0,0. Your destination is at corner width,height. You will return the number of 
distinct paths that lead to your destination. Each path must use exactly width+height blocks. In addition, the city has declared 
certain street blocks untraversable. These blocks may not be a part of any path. You will be given a String[] bad describing which 
blocks are bad. If (quotes for clarity) "a b c d" is an element of bad, it means the block from corner a,b to corner c,d is 
untraversable. For example, let's say
width  = 6
length = 6
bad = {"0 0 0 1","6 6 5 6"}

Definition
    	
Class:	AvoidRoads
Method:	numWays
Parameters:	int, int, String[]
Returns:	long
Method signature:	long numWays(int width, int height, String[] bad)
(be sure your method is public)
    
 
Constraints
-	width will be between 1 and 100 inclusive.
-	height will be between 1 and 100 inclusive.
-	bad will contain between 0 and 50 elements inclusive.
-	Each element of bad will contain between 7 and 14 characters inclusive.
-	Each element of the bad will be in the format "a b c d" where,
a,b,c,d are integers with no extra leading zeros,
a and c are between 0 and width inclusive,
b and d are between 0 and height inclusive,
and a,b is one block away from c,d.
-	The return value will be between 0 and 2^63-1 inclusive.
 
Examples
0)	
    	
6
6
{"0 0 0 1","6 6 5 6"}
Returns: 252
Example from above.
1)	
    	
1
1
{}
Returns: 2
Four blocks aranged in a square. Only 2 paths allowed.
2)	
    	
35
31
{}
Returns: 6406484391866534976
Big number.
3)	
    	
2
2
{"0 0 1 0", "1 2 2 2", "1 1 2 1"}
Returns: 0
*/

import java.util.*;

public class AvoidRoads {
	
	public static void main(String[] args) {
		AvoidRoads obj = new AvoidRoads();
		String[] bad = new String[] {"0 2 0 3", "1 2 1 3", "2 2 2 3", "3 2 3 3", "4 2 4 3", "5 2 5 3", "6 2 6 3", "7 2 7 3", "8 2 8 3", "9 2 9 3"};
		System.out.println("Ways: " + obj.numWaysSpaceEfficient(10, 100, bad));
	}
	
	public long numWays2D(int wd, int ht, String[] bad) {
		
		wd += 1; ht += 1;
		long[][] arr = new long[wd][ht];
		Set<String> set = new HashSet<>();
		for(String s : bad) set.add(s);
		
		for (int i = 0, j = 0; j < ht; j++) {
			if(isBadPath(i, j - 1, i, j, set)) break;
			arr[i][j] = 1;
		}
		
		for (int i = 0, j = 0; i < wd; i++) {
			if(isBadPath(i - 1, j, i, j, set)) break;
			arr[i][j] = 1;
		}
		
		arr[0][0] = 0;
		
		for (int i = 1; i < wd; i++) {
			boolean flag = false;
			for (int j = 1; j < ht; j++) {
				if(isBadPath(i, j - 1, i, j, set) && isBadPath(i - 1, j, i, j, set)) {
					flag = true;
					break;
				}
				if(!isBadPath(i, j - 1, i, j, set)) arr[i][j] += arr[i][j - 1];
				if(!isBadPath(i - 1, j, i, j, set)) arr[i][j] += arr[i - 1][j];				
			}
			if(flag) break;
		}
		print(arr);
		return arr[wd - 1][ht - 1];
	}
	
	public long numWaysSpaceEfficient(int wd, int ht, String[] bad) {
		wd += 1; ht += 1; // only for this problem.
		long[] arr = new long[ht];
		
		Set<String> set = new HashSet<>();
		for(String s : bad) set.add(s);
		
		for(int j = 1, i = 0; j < ht; j++) {
			if(!isBadPath(i, j - 1, i, j, set)) arr[j] = 1; 
			else break;
		}
		arr[0] = 1; // intentionally made 1 for the next row.
		for(int i = 1; i < wd; i++) {
			if(isBadPath(i - 1, 0, i, 0, set)) arr[0] = 0;
			for(int j = 1; j < ht; j++) {
				if(isBadPath(i - 1, j, i, j, set)) arr[j] = 0; // if no path from top cell to current cell.
				if(!isBadPath(i, j - 1, i, j, set)) arr[j] += arr[j - 1]; // if there is a path from left cell to current cell.
																		// if no path, then need not add left cell's value.
			}
		}		
		return arr[ht - 1];
	}

	private boolean isBadPath(int i1, int j1, int i2, int j2, Set<String> set) {
		String from = i1 + " " + j1;
		String to = i2 + " " + j2;
		String badPath1 = from + " " + to;
		String badPath2 = to + " " + from;
//		System.out.println(badPath1 + ", " + badPath2);
		if(set.contains(badPath1) || set.contains(badPath2)) return true;
		return false;
	}

	private void print(long[][] arr) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}		
	}
}
