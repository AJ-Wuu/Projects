/**
 * @author AJWuu
 */

package slidingWindow;

public class SlidingWindow {
	
	//Find the maximum sum of a subarray of size k
	//Time: O(n*k)
	
	public static int maxSum(int arr[], int n, int k) {
		int max_sum = Integer.MIN_VALUE;

		//consider all blocks starting with i
		for (int i=0; i<n-k+1; i++) {
			int current_sum = 0;
			for (int j=0; j<k; j++) {
				current_sum = current_sum + arr[i + j];
			}
			max_sum = Math.max(current_sum, max_sum);
		}

		return max_sum;
	}

	public static void main(String[] args) {
		int array[] = {1,4,2,10,2,3,1,0,20};
		int k = 4;
		int n = array.length;
		System.out.println(maxSum(array, n, k));
	}
}
