/*

https://leetcode.com/problems/gas-station/

There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). 
You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once in the clockwise direction, 
otherwise return -1.

Note:

If there exists a solution, it is guaranteed to be unique.
Both input arrays are non-empty and have the same length.
Each element in the input arrays is a non-negative integer.
Example 1:

Input: 
gas  = [1,2,3,4,5]
cost = [3,4,5,1,2]

Output: 3

*/

// Brute force O(n^2) solution to start from each element.

class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = cost.length;
        for(int i = 0; i < n; i++) {
            int oil = 0;
            boolean flag = false;
            for(int j = i; j < n; j++) {
                oil += gas[j] - cost[j];
                if(oil < 0) {
                    flag = true;
                    break;
                }
            }
            
            if(!flag) {
                for(int k = 0; k < i; k++) {
                    oil += gas[k] - cost[k];
                    if(oil < 0) {
                        flag = true;
                        break;
                    }
                }    
            }
            
            if(!flag) return i;
        }
        return -1;
    }
}

// O(n) solution.

/*

https://www.youtube.com/watch?v=nTKdYm_5-ZY&list=PLupD_xFct8mETlGFlLVrwbLwcxczbgWRM&index=8&t=0s

1. We start with the first element and see if we can reach the second element (i.e., gas[i] - cost[i] >= 0).
    a. If we can reach: fine.
    b. If we can't (i.e., gas[i] - cost[i] < 0), we cannot start from that index, we will now try to start from the next element (i + 1), so that's our new start point, hence make surplus = 0. But what about the total negative deficit to reach this point, we store that in the deficit variable.

*/

class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int start = 0, surplus = 0, deficit = 0, n = gas.length;
        for(int i = 0; i < n; i++) {
            surplus += (gas[i] - cost[i]);
            if(surplus < 0) {
                start = i + 1;
                deficit += surplus;
                surplus = 0;
            }
        }
        return surplus + deficit >= 0 ? start : -1;
    }
}
