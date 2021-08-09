/**
 * @author AJWuu
 */

package knapsack;

public class Main {

	public static void main(String args[]) {
		int val[] = new int[] { 60, 100, 120 };
		int wt[] = new int[] { 10, 20, 30 };
		int W = 50;
		int n = val.length;
		System.out.println(Recursive.knapSack(W, wt, val, n));
		System.out.println(DP.knapSack(W, wt, val, n));
	}

}
