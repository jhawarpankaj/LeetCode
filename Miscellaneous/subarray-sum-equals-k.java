/*
https://leetcode.com/problems/subarray-sum-equals-k/

Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2
Note:
The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
*/

/*
Before moving on to this we need understand the concept of RANGE QUERIES or PREFIX SUM.
Watch: https://www.youtube.com/watch?v=pVS3yhlzrlQ

Given an array: [6, 3, -2, 4, -1, 0, -5], how can we efficiently return the results of the subarray sum queries like:
sum(0, 6) or sum(0, 4) or sum(2, 3) or sum(3, 5) and so on.
1. Naive way is to run the loop every time and return the result. But what if there are millions of such queries?
2. We will form a prefix sum out of the given array, where each element i will store the sum from (0, i).
3. So if we now want to get the sums, such as:
    a. sum(0, 5) : just return the value at sum(5);
    b. sum(2, 5): sum[5] - sum[1] and so on.
4. All operations are now O(1).
*/

/*
1. For this problem, lets say we have to count all subarrays with k = 1. 
2. Lets say we are at any arbitrary index i = 4, here sum[4] = 10. 
3. If we can find any j (j < i) whose sum, i.e., sum(0, j) = (sum[4] - k) then the sum of the subarray j + 1 to i will be = k.
i.e., sum(j + 1, i) = k. WHY? Proof:
sum[i] - sum[j] = sum[i] - (sum[i] - k) = k. And sum[i] - sum[j] is the sum of the subarray sum[j+1] to sum[i].
*/

class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, 1); // there is one array whose sum = 0, i.e., [].
        int currSum = 0, count = 0;
        for(int i = 0; i < nums.length; i++) {
            currSum += nums[i];
            if(map.containsKey(currSum - k)) {
                // Lets say the value of map.get(currSum - k) = 3, this means that there are 
                // 3 subarrays (0, j) where j < i and sum(0, j) = currSum - k.
                count += map.get(currSum - k);
            }
            map.put(currSum, map.getOrDefault(currSum, 0) + 1);
        }
        return count;
    }
}
