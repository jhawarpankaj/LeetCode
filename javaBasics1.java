package Test;

//import javafx.util.Pair;
import java.util.*;


public class HelloWorld {

	public static void main(String[] args) {
////		int[] arr1 = new int[] {1, 2, 4};
////		int[] arr2 = {2, 1, 6};
////		
////		for (int i = 0; i < arr1.length; i++) {
////			System.out.print(arr1[i] + " ");
////		}
////		System.out.println();
////		String s = "abc";
////		char[] cArr = s.toCharArray();
////		for (int i = 0; i < cArr.length; i++) {
////			System.out.print(cArr[i] + " ");
////		}
////		
////		Arrays.sort(arr2);
////		System.out.println(Arrays.toString(arr2));
////		
////		String s2 = "sdf";
////		int len = s2.length();
////		System.out.println(s2.charAt(len - 1));
//////		s2 = null;
//////		System.out.println(s2.charAt(3));
////		for (int i = 0; i < s2.length(); i++) {
////			char c = s2.charAt(i);
////			System.out.print(c + " ");
////		}
//		
//		////////////////////////////////////////////////
//		// Integer [int, 4 byte, +2^31 (0 to 2,147,483,647), -2^31 (-1 to -2,147,483,648)]
//		// Why java has primitive and classes both?
//		int x = 2147483647 + 1;
//		System.out.println(x);
//		Integer y = 1212;
//		Integer z = new Integer(323);
//		// y = x;
//		// Long [long, 8 byte]
//		// Float (float), 
//		// Double [double], 
//		// String ("Asda"),
//		String s = "asdasd";
//		String obj = new String("asd");
//		// Character('a', '%') [char]
		
//		char c = 'a';
//		// Boolean [boolean]
//		
//		////////////////////////////////////////////////
//		
//		// Read more about JAVA HASHSet.
//		HashSet<Integer> set = new HashSet<>();
//		set.add(1);
//		set.add(3);
//		set.add(1);
////		System.out.println(set);
//		for (Integer x2 : set) {
//			System.out.print(x2 + " ");
//		}
//		System.out.println(set.contains(1));
//		
//		////////////////////////////////////////////////
//		
//		HashMap<String, Integer> map = new HashMap<>(); 
//		// {"a" : 1, "b" : 2, "m" : 0}
//		map.put("a", 1);
//		map.put("b", 2);
//		map.put("a", 2);
//		System.out.println(map);
//		System.out.println(map.containsKey("b"));
//		Set<String> set2 = map.keySet();
//		System.out.println(set2);
//		for (String s1 : set2) {
//			System.out.println(s1);
//			System.out.println(map.get(s1);
//		}
		
//		String s1 = "bb";
//		String s2 = "aa";
//		System.out.println(s2.compareTo(s1));
		
		////////////////////////////////////////////////
		
		// Accessing elements from LinkedHashSet, TreeSet, LinkedHashMap, TreeMap.
		// Note that TreeSet/TreeMap provides the first and last methods but LinkedHashSet/LinkedHashMap does not.
		
//		LinkedHashSet<Integer> lSet = new LinkedHashSet<>();
//		lSet.add(2); lSet.add(3); lSet.add(1);
//		System.out.println(lSet.iterator().next()); // 2
//		// no way to get last element. We have to iterate entire set.
//		
//		LinkedHashMap<Integer, Integer> lMap = new LinkedHashMap<>();
//		lMap.put(1, 2); lMap.put(0, 3); lMap.put(6, 8);
//		System.out.println(lMap.keySet().iterator().next()); // 1
//		System.out.println(lMap.values().iterator().next()); // 2
//		
//		TreeSet<Integer> tSet = new TreeSet<>(); // default ascending order
//		tSet.add(2); tSet.add(3); tSet.add(1);
//		System.out.println("TreeSet iterator: " + tSet.iterator().next()); // 1
//		System.out.println("first: " + tSet.first()); // 1
//		System.out.println("last: " + tSet.last()); // 3
//		
//		TreeMap<Integer, Integer> tMap = new TreeMap<>();
//		tMap.put(1, 2); tMap.put(2, 3); tMap.put(0, 8);
//		System.out.println(tMap.keySet().iterator().next()); // 0
//		System.out.println(tMap.firstKey()); // 0
//		System.out.println(tMap.lastKey()); // 2
//		
//		// some more complex operations using iterator.
//		TreeMap<Integer, LinkedHashSet<Integer>> test = new TreeMap<>();
//		test.computeIfAbsent(1, x -> new LinkedHashSet<>()).add(3);
//		test.computeIfAbsent(1, x -> new LinkedHashSet<>()).add(1);
//		test.computeIfAbsent(1, x -> new LinkedHashSet<>()).add(5);
//		test.computeIfAbsent(2, x -> new LinkedHashSet<>()).add(4);
//		System.out.println(test); // {1=[3, 1, 5], 2=[4]}
//		Integer next = test.values().iterator().next().iterator().next();
//		test.values().iterator().next().remove(next); 
//		System.out.println(test); // {1=[1, 5], 2=[4]}		
//		
//		
//		
//		
//		// Note that in many problems (involving numbers) we initialize a variable with Integer.MAX_VALUE or Integer.MIN_VALUE
//		// and then change that in the problem using Math.max or Math.min.
//		// For similar situation, in case of string, if we want lexicographically largest string, we can initialize
//		// the variable with empty String, as:
//		// Note that we cannot get min string this way.
//		
//		String max = ""; // to get the lexicographically smallest string.
//		String[] s1 = new String[] {"za3", "sas1", "asd"};
//		for (String s : s1) {
//			if (max.compareTo(s) < 0) max = s; // for first comparison it will always be valid and then get the max string from array.
//		}
//		System.out.println(max);
//		Pair<Integer, String> pair = new Pair<>();
		
//		String s = "#";
//		String[] split = s.split(",");
//		System.out.println(Arrays.toString(split));
		
//		Random rand = new Random();
//		int nextInt = rand.nextInt(1);
//		System.out.println(nextInt);
//		
//		Random random2 = new Random();
//		random2.setSeed(1);
//		System.out.println("Using Method");
//		System.out.println(random2.nextInt());
//		System.out.println(random2.nextInt());
//		System.out.println(random2.nextInt());
		
//		List<List<Integer>> result = new ArrayList<>();
//		List<List<Integer>> temp = new ArrayList<>();
//		List<Integer> temp1 = new ArrayList<>();
//		temp1.add(1); temp1.add(2); temp1.add(3);
//		temp.add(temp1);
//		for (int i = 0; i < 2; i++) {
//			
//		}
		
//		LinkedHashSet<Integer> set = new LinkedHashSet<>();
//		LinkedHashMap<Integer, Integer> lMap = new LinkedHashMap<>();
//		lMap.put(1, 2); lMap.put(2, 3); lMap.put(3, 4);
//		Set<Integer> tp = lMap.keySet();
//		for (int key : tp) {
//			
//		}
//		
//		
//		
//		set.add(1); set.add(2); set.add(3);
//		for (int x : set) {
//			System.out.print(x + " ");
//		}
//		for (int x : set) {
//			System.out.print(x + " ");
//		}
//		for (int x : set) {
//			System.out.print(x + " ");
//		}		
//		
//		Map<Object,Object> map = new HashMap<>();
//		map.put("String", 1);
//		map.put('c', 2);
//		map.put(1, 2);
//		for (Object key : map.keySet()) {
//			Object value = map.get(key);
//			System.out.println(value.toString());
//		}
//		List<String> str = new ArrayList<>();
////		str.add("1");
////		str.add("2");
//		String ans = String.join(" ", str);
//		List<String> res = new ArrayList<>();
//		res.add(ans);
//		System.out.println(res.size());
		
//		String s = "pacf";
////		char c = s.charAt(3);
////		int[] arr = new int[128];
////		arr[c] = 1;
////		System.out.println(arr[c]);
//		
//		char[] c = s.toCharArray();
//		c[2] = '1';
//		System.out.println(s);
//		s = new String(c);
//		System.out.println(s);
		
//		System.out.println(1L << 31);
//		System.out.println((long) Math.pow(2, 32));		
//		System.out.println(Integer.toBinaryString(x));
//		for (int i = 0; i < 40; i++) {
//			x = x >> 1;
//		System.out.println(Integer.toBinaryString(x));
//		System.out.println(x);
//		}
//		System.out.println(x);
		List<String> list = new ArrayList<>();
		list.add("D"); list.add("B"); list.add("C");
//		list.sort();
		list.size();
		String s = "Add";
		s.length();
		int[] arr = new int[2];
		int n = arr.length;
		System.out.println(list);
		
	}

}
