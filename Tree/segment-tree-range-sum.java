/*
https://leetcode.com/problems/range-sum-query-mutable/

Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to val.

Example:

Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
Note:

The array is only modifiable by the update function.
You may assume the number of calls to update and sumRange function is distributed evenly.

*/

/*

1. Building the segement tree array:
  a. The idea is that we build an array(tree) from the given input array.
  b. The size of the tree array we choose is 2 * (size of the input array). Note that we actually need a (2n - 1) size array.
     But we won't use the index 0 and starts from index 1 (for simplicity of calculating parents and other stuffs).
  c. The left child thus is: 2i, and right is 2i + 1;
  Steps:
  a. First put all the elements of the original array to the end of the tree array.
  b. Then calculate the value of the parents of each of the element.
  
2. For Update:
  a. Whenever, an update happens, we need to percolate the update upto the root.
  b. We know the delta of the update and that delta is added till the root. (Note that 0 index does not have any element, 
     hence we iterate till 1, i.e., > 0. (Also beware of the edge case, when we use 0 index, it will infinte loop 
     at 0 as 0/2 = 0).
     
3. For Query: The query part is interesting. We are given the 2 index of the original array for the range to query for.
  a. We first convert those ranges to the segement tree indexes.
  b. Now, if the left index is a right children of a node [how to determine that? Simple, as right child is (2*i + 1),
     hence, if it is an odd number that suggests that it's a right child], then we know that the value of parent is not useful
     in this case and we only add the value of that node and move to the current node's parent's parent's right child 
     [WHY??? because that will be the next element after left, i.e. left + 1, AND HOW TO GET THERE??? draw a tree on paper,
     and realize that if we add 1 to the current index and then divide by 2. will reach to that node]
  c. Same logic when the right index is the left child of a node. We decrease right-- and divide by 2.
  
*/


class NumArray {
    
    int n;
    int[] tree;    

    public NumArray(int[] nums) {
        n = nums.length;
        tree = new int[n * 2];
        for(int i = n - 1, j = tree.length - 1; i >= 0; i--, j--){
            tree[j] = nums[i];
        }
        for(int i = tree.length - n - 1; i > 0; i--){
            tree[i] = tree[2 * i] + tree[2 * i + 1];
        }
    }
    
    public void update(int i, int val) {
        int pos = i + n;        
        int diff = val - tree[pos];
        tree[pos] = val;
        pos = pos / 2;
        while(pos > 0){
            tree[pos] = tree[pos] + diff;
            pos = pos / 2;
        }
    }
    
    public int sumRange(int i, int j) {
        int p = i + n;
        int q = j + n;
        int sum = 0;
        while(p <= q){
            // To check if p is right child of a parent
            // then this node value is used and not it's parent's.
            
            // On the contrary if this p was a left child of a parent
            // then go to its parent (which happens by the division at end)
            if(p % 2 == 1){
                sum = sum + tree[p];
                p++;
            }
            
            // to check if q is left child of any parent
            // then this node's value is used and not it's parent.
            if(q % 2 == 0){
                sum = sum + tree[q];
                q--;
            }
            p = p / 2;
            q = q / 2;
        }
        return sum;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
