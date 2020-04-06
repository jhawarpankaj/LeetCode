public class MaxSumSubarray {
	public static void main(String[] args) {
		int[] arr = new int[] { 13, -3, -25, 20, -3 };
		System.out.println(dc(arr, 0, arr.length - 1));
	}
	private static int dc(int[] arr, int l, int h) {
		if(l <= h) {
			if(l == h) return arr[l];
			int m = l + (h - l) / 2;

			// Divide and Conquer the subproblems individually.
			int left = dc(arr, l, m);
			int right = dc(arr, m + 1, h);

			// Combining the result of subproblems and solving extra
			// subprobelm (cross-sum) which is not same as the original problem.
			int ans = combine(arr, l, m, h, left, right);
			return ans;
		}
		throw new IllegalArgumentException();
	}
	private static int combine(int[] arr, int l, int m, int h, int leftMax, int rightMax) {
		int left = Integer.MIN_VALUE, right = Integer.MIN_VALUE;
		int p = m, q = m + 1, sum = 0;
		while(p >= l) {
			sum += arr[p];
			left = Math.max(sum, left);
			p--;
		}
		sum = 0;
		while(q <= h) {
			sum += arr[q];
			right = Math.max(sum, right);
			q++;
		}
		int cross = left + right;
		int max = Math.max(left, right);
		int result = Math.max(max, cross);
		return result;
	}
}
