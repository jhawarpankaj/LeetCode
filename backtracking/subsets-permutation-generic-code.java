import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetWithWithoutDuplicates {

	public static void main(String[] args) {
		int[] arr;
		
		// without duplicates...		
		arr = new int[] { 1, 2, 3 };
		
		List<Integer> temp = new ArrayList<Integer>();
		subsetWithoutDuplicates(arr, 0, temp);
		permutationWithoutDuplicates(arr, 0);
		
		// with duplicates...
		arr = new int[] { 2, 1, 2 }; temp.clear();
		
		Arrays.sort(arr);		
		subsetWithDuplicates(arr, 0, temp);
		permutationWithDuplicates(arr, 0);
	}	

	/**
	 * The idea is to backtrack once considering the element and once without considering 
	 * the current element. (And repeat this for every element in the array).
	 * 
	 * Note that we are not calling the backtracking function again after removing the element 
	 * from temp as the for loop will take care of that by adding the next element.
	 *  
	 */
	private static void subsetWithoutDuplicates(int[] arr, int start, List<Integer> temp) {
		for(int i : temp) System.out.print(i + " ");
		System.out.println();
		for(int i = start; i < arr.length; i++) {
			temp.add(arr[i]);
			subsetWithoutDuplicates(arr, i + 1, temp);
			temp.remove(temp.size() - 1);
		}
	}
	
	/**
	 * Permutation for numbers {1, 2, 3}:
	 * [1, 2, 3]
	 * [1, 3, 2]
	 * [2, 1, 3]
	 * [2, 3, 1]
	 * [3, 1, 2]
	 * [3, 2, 1]
	 * 
	 * 1. Generally permutation is thought as every element occurring at each place.
	 * 
	 * 2. We can think of it in another way as:
	 * 	  Every element (one by one) moving to the first place and then solving the 
	 *    same subproblem for the rest of the array. In the above example, observe that
	 *    each element 1, 2, and 3 take the first place and we solve the subproblem for
	 *    the remaining array.
	 * 
	 * 3. The argument start here can be thought of as the position where we want each element
	 * 	  to take place one by one. It can be any position in the array, last, mid, or any.
	 */
	private static void permutationWithoutDuplicates(int[] arr, int start) {
		if(start == arr.length) {
			for(int elem : arr) System.out.print(elem + " ");
			System.out.println();
		} else {
			for(int i = start; i < arr.length; i++) {
				swap(arr, i, start);
				permutationWithoutDuplicates(arr, start + 1);
				swap(arr, i, start);
			}
		}
	}
	
	/**
	 * 1. To not consider the duplicates we first sort the array.
	 * 2. Imagine the case {1, 2, 2, 2}.
	 * 3. Note that adding to temp will be fine and all element will be added first (temp = [1, 2, 2, 2])
	 * 4. Also note that we will get duplicates only after from temp is executed.
	 * 5. Say we remove the last element (index 3) and the recursion returns to its parent call.
	 * 6. Now the parent will remove its last added element (index 2) and the for loop will move ahead to index 3.
	 * 7. Now during this iteration of loop, we have removed the previous 2 (index 2) and about to add this new 2 (index 3).
	 * 8. This will leave the temp in same state as it was before (i.e., temp = [1, 2, 2]) because we just removed one 2 and added 2 again.
	 * 9. This will result in duplicate subsets.
	 * 10. Hence, the if condition inside the loop will save from doing this by advancing i if there are duplicates.
	 */
	private static void subsetWithDuplicates(int[] arr, int start, List<Integer> temp) {
		for(int i : temp) System.out.print(i + " ");
		System.out.println();
		for(int i = start; i < arr.length; i++) {
			if(i > start && arr[i] == arr[i - 1]) continue;
			temp.add(arr[i]);
			subsetWithDuplicates(arr, i + 1, temp);
			temp.remove(temp.size() - 1);
		}
	}
	
	/**
	 * 1. The logic of avoiding duplicates is the same as in case of subsets.
	 * 2. If an element is already considered at the swap position, do not 
	 *    consider the same element again for that position.
	 */
	private static void permutationWithDuplicates(int[] arr, int start) {
		if(start == arr.length) {
			for(int elem : arr) System.out.print(elem + " ");
			System.out.println();
		} else {
			for(int i = start; i < arr.length; i++) {
				if(i > start && arr[i] == arr[i - 1]) continue;
				swap(arr, i, start);
				permutationWithDuplicates(arr, start + 1);
				swap(arr, i, start);
			}
		}
	}
	
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
