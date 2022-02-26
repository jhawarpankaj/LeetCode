package Test;

import java.util.*;

public class Practise {

	public static void main(String[] args) {
		
		char c = 'A';
		System.out.println(Character.isLetter(c)); // true 
		System.out.println(Character.isUpperCase(c)); // true
		System.out.println(Character.isDigit(c)); // false
		System.out.println(Character.isAlphabetic(c)); // true
		
				
		// substring method substring(start, end): remember that it goes one less than end. 
//		String s = "pankaj";
//		System.out.println(s.substring(1, 3)); // an
//		System.out.println(s.substring(1, 1)); // ""
////		System.out.println(s.substring(1, 0)); // error
//		System.out.println(s.substring(1, 6)); // ankaj	
////		System.out.println(s.substring(1, 7)); // error
//		System.out.println(s.substring(2)); // nkaj
//		
//		// compareTo will return the diff in ASCII. str1.compareTo(str2) = str1 - str2.
//		String s1 = "panjha";
//		String s2 = "panaha";
//		System.out.println(s1.compareTo(s2)); // +ve
//		String str1 = "Sas";
//		String str2 = "sas";
//		System.out.println(str1.compareTo(str2)); // -ve, note that ASCII value of all Uppercase are less than Lowercase.
//		
//		List<Integer> list = new ArrayList<>();
//		list.add(3); list.add(1); list.add(2);
//		list.sort((a, b) -> {
//			if (a > b) return -1;
//			else if (a == b) return 0;
//			else return 1;
//		}); 
//		System.out.println(list); // outputs 3, 2, 1
		
		
		
//		int[] arr = new int[3];
//		arr[0] = 2; arr[1] = 1; arr[2] = 3;
//		Arrays.sort(arr); // 1, 2, 3
		
		List<List<Integer>> list = new ArrayList<>();
		List<Integer> temp = new ArrayList<>();
		temp.add(2); temp.add(1);
		list.add(temp);
		
		List<Integer> temp2 = new ArrayList<>();
		temp2.add(3); temp2.add(2);
		list.add(temp2);
		
		list.sort((a, b) -> {
			if (a.get(0) > b.get(0)) return -1;
			else if (a.get(0) == b.get(0)) return 0;
			else return 1;
		}); // [[3, 2], [2, 1]]
		
		
		
		System.out.println(list);
		
		
		int[][] arr = {{2, 1}, {1, 3}};
		Arrays.sort(arr, (int[] a, int[] b) -> {
			if (a[1] > b[1]) return -1;
			else if (a[1] == b[1]) return 0;
			else return 1;
		}); // sorted descending on 1st element: [[1, 3], [2, 1]]
		
		
		for (int[] a : arr) System.out.println(Arrays.toString(a));
		
		LinkedList<Integer> doublyLinkedList = new LinkedList<>();
		doublyLinkedList.add(2);
		doublyLinkedList.add(1);
		for (int x : doublyLinkedList) System.out.print(x + " ");
		doublyLinkedList.getFirst();
		doublyLinkedList.getLast();
		doublyLinkedList.removeFirst();
		doublyLinkedList.removeLast();
		doublyLinkedList.peekFirst();
		doublyLinkedList.peekLast();
		
		
		
//		PriorityQueue<Integer> PQ = new PriorityQueue<>((a, b) -> {
//			if (a > b) return -1;
//			else if (a == b) return 0;
//			else return 1;
//		}); // descending order
//		PQ.add(1); PQ.add(3); PQ.add(2);
//		while(!PQ.isEmpty()) System.out.print(PQ.remove() + " "); // outputs 3, 2, 1
		
		
//		
//		int[][] a = 	
//		
//		Integer a = 1000;
//		Integer b = 1000;
//		System.out.println(a.compareTo(b));
	}

}
