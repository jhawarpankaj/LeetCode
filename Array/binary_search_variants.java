public class BinarySearch {

	public static void main(String[] args) {
		int[] arr = {2, 3, 3, 5, 5, 5, 6, 6};
		int key = 5;
		System.out.println(contains(arr, key));
		System.out.println(first(arr, key));
		System.out.println(last(arr, key));
		int index = leastElementGreaterThanKey(arr, 5);
		if (index == -1) System.out.println("No elem greater than key found.");
		else System.out.println(arr[index]);
		System.out.println(greatestElementLessThanKey(arr, 6));
		System.out.println(leastElementGreaterOrEqToKey(arr, 5));
	}

	private static int contains(int[] arr, int key) {
		int index = -1;
		int l = 0, h = arr.length - 1;
		while (l <= h) {
			int curr = (l + h) >>> 1;
			if (arr[curr] == key) {
				index = curr;
				break;
			} else if (arr[curr] > key) h = curr - 1;
			else l = curr + 1;			
		}
		return index;
	}
	
	private static int first(int[] arr, int key) {
		int index = -1;
		int l = 0, h = arr.length - 1;
		while (l <= h) {
			int curr = (l + h) >>> 1;
			// curr is equal to key. So it is a probable answer. 
			// But there can be other elem eq to key on the left. Look for them.
			if (arr[curr] == key) {
				index = curr;
				h = curr - 1;
			} else if (arr[curr] > key) h = curr - 1;
			else l = curr + 1;
		}
		return index;
	}
	
	private static int last(int[] arr, int key) {
		int index = -1;
		int l = 0, h = arr.length - 1;
		while (l <= h) {
			int curr = (l + h) >>> 1;
			// curr is equal to key. But there can be more elem on the right equal to key.
			// And we need the rightmost one, so look for them on the right.
			if (arr[curr] == key) {
				index = curr;
				l = curr + 1;
			} else if (arr[curr] > key) h = curr - 1;
			else l = curr + 1;
		}
		return index;
	}
	
	private static int greatestElementLessThanKey(int[] arr, int key) {
		int index = -1;
		int l = 0, h = arr.length - 1;
		while (l <= h) {
			int curr = (l + h) >>> 1;
			// curr is less than key, so its a probable answer. 
			// But there may be more element on the right which are less than key. Look for it on the right side.
			if (arr[curr] < key) {
				index = curr;
				l = curr + 1;
			} else if (arr[curr] > key) {
				h = curr - 1;
			} else {
				h = curr - 1;
			}
		}
		return index;
	}

	private static int leastElementGreaterThanKey(int[] arr, int key) {
		int index = -1;
		int l = 0, h = arr.length - 1;
		while (l <= h) {
			int curr = (l + h) >>> 1;
			// curr is greater than key, so it is a probable answer. 
			// But there may be another less than this. Look for it on the left side.
			if (arr[curr] > key) {
				index = curr;
				h = curr - 1;
			} else if (arr[curr] < key) l = curr + 1; 
			else l = curr + 1;
		}
		return index;
	}
	
	// greater or equal to key is same as greater.
	private static int leastElementGreaterOrEqToKey(int[] arr, int key) {
		int index = -1;
		int l = 0, h = arr.length - 1;
		while (l <= h) {
			int curr = (l + h) >>> 1;
			// curr is greater than or eq key, so it is a probable answer. 
			// But there may be another less than this. Look for it on the left side.
			if (arr[curr] >= key) {
				index = curr;
				h = curr - 1;
			} else l = curr + 1;
		}
		return index;
	}
}
