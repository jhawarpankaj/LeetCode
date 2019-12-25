
class Solution {
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    int ans = 0;
    public int pathSum(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            map.put(nums[i] / 10, nums[i]);
        }
        getSum(nums[0], 0);
        return ans;
    }
    
    void getSum(int num, int sum){
        int level = num / 100, pos = (num / 10) % 10, digit = num % 10;
        int left = (level + 1) * 10 + 2 * pos - 1;
        int right = left + 1;
        sum = sum + digit;
        if(!map.containsKey(left) && !map.containsKey(right)) ans = ans + sum;
        else{
            if(map.containsKey(left)) getSum(map.get(left), sum);
            if(map.containsKey(right)) getSum(map.get(right), sum);
        }
    }
}

// Think how to traverse each full path in the tree in the same traversal.
// Once you are done with a node and retracting back to the parent, i.e., backtracking: think!!
// In this problem varaible sum knows till each level and global ans
// adds up once a full path has been explored.
