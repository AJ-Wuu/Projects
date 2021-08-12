/**
 * @author AJWuu
 */

package knapsack;

public class Greedy {

	public static int knapSack(int W, int wt[], int val[], int n) {
		//Base Case
		if (n == 0 || W == 0) {
			return 0;
		}

		//If weight of the nth item is more than Knapsack capacity W,
		//then this item cannot be included in the optimal solution
		if (wt[n-1] > W) {
			return knapSack(W, wt, val, n-1);
		}
		else { //Return the maximum of two cases: nth item included OR not included
			return Math.max(val[n-1] + knapSack(W - wt[n-1], wt, val, n-1), knapSack(W, wt, val, n-1));
		}
	}

}
