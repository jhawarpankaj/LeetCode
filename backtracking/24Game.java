/*
https://leetcode.com/problems/24-game/

You have 4 cards each containing a number from 1 to 9. 
You need to judge whether they could operated through *, /, +, -, (, ) to get the value of 24.

Example 1:
Input: [4, 1, 8, 7]
Output: True
Explanation: (8-4) * (7-1) = 24
Example 2:
Input: [1, 2, 1, 2]
Output: False
*/

class Solution {
    public boolean judgePoint24(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        double[] arr = new double[nums.length];
        for(int i = 0; i < nums.length; i++) arr[i] = 1.0 * nums[i];
        return helper(arr, visited, 4);
    }
    boolean helper(double[] nums, boolean[] visited, int rem) {
        if(rem == 1) {
            for(int i = 0; i < nums.length; i++) {
                if(!visited[i]) return Math.abs(nums[i] - 24) < 0.0001 ? true : false;
            }                    
        }
        double prev = 0;
        
        for(int i = 0; i < nums.length; i++) {
            
            // we would be doing an in place update at nums[i], 
            // so we first take a backup of nums[i] to set it back at the end.
            // The nums[i] is then used as a new element produced after all possible (+, -, *, /) operation.
            
            // Also the 2 loops are for generating all possible pairs of numbers.
            
            prev = nums[i];
            
            // Now, we will be using each element one by one for all of the operations.
            // But once we use an element, we wouldn't be using that again and hence mark that as visited.
            
            for(int j = i + 1; j < nums.length; j++) {
                if(visited[j]) continue;                
                visited[j] = true; // marking the current jth elem visited, 
                                   // will unmark it at the end for next iteration.
                
                // add
                nums[i] = nums[j] + prev;
                if(helper(nums, visited, rem - 1)) return true;
                
                // minus (as minus is not commutative)
                nums[i] = prev - nums[j];
                if(helper(nums, visited, rem - 1)) return true;
                
                nums[i] = nums[j] - prev;
                if(helper(nums, visited, rem - 1)) return true;
                
                // divide (as divide is not commutative)
                nums[i] = nums[j] / prev;
                if(helper(nums, visited, rem - 1)) return true;
                
                nums[i] = prev / nums[j];
                if(helper(nums, visited, rem - 1)) return true;
                
                // multiply
                nums[i] = prev * nums[j];
                if(helper(nums, visited, rem - 1)) return true;
                
                visited[j] = false;
            }
            nums[i] = prev;
        }
        return false;
    }
}
