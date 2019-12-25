
class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
        hm.put(0, 1);
        int currSum = 0, res = 0;
        for(int i = 0; i < nums.length; i++){
            currSum+=nums[i];
            if(hm.containsKey(currSum - k)){
                int c = hm.get(currSum - k);
                res = res + c;
            }
            int temp = hm.getOrDefault(currSum, 0);
            hm.put(currSum, temp + 1);
        }
        return res;
    }
}

// The idea is that you will calculate sum prefix of an array
// so my start is always 0 and now my index is 5. currSum will have summation of array from index 0 to index 5 
// let's say that this summation is 50 and I am searching for a summation = 20
// Imagine that the sub array from 0 to 3 = 30.
// this means that summation from 0-> 5 = 50 and summation from 0-> 3 = 30
// so summation from (3 -> 5) = 50-30 = 20 so this sub array should be added to my result too.
// So get how many such subarrays have been seen before with sum = 30 and add that to res.
