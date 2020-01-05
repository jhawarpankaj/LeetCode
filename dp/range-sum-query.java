/*
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

Example:
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
Note:
You may assume that the array does not change.
There are many calls to sumRange function.
*/

class NumArray {    
    int[] arr;
    public NumArray(int[] nums) {        
        for(int i = 1; i < nums.length; i++){
            nums[i] = nums[i - 1] + nums[i];
        }
        arr = nums;
    }    
    public int sumRange(int i, int j) {
        // see the solution for this optimization.
        
        if(i == 0) return arr[j];
        else return arr[j] - arr[i - 1];
    }
}

// Minor Optimization. To save the if else condition in sumRange.

private int[] sum;

public NumArray(int[] nums) {
    sum = new int[nums.length + 1];
    for (int i = 0; i < nums.length; i++) {
        sum[i + 1] = sum[i] + nums[i];
    }
}

public int sumRange(int i, int j) {
    return sum[j + 1] - sum[i];
}

// The idea is to precalculate the cumulative sum and return the query in O(1) time:
// sumRange(i,j)=sum[j+1]−sum[i]
